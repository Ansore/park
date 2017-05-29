package com.park.conserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import com.park.data.Data;
import com.park.data.ParkData;
import com.park.dto.Message;
import com.park.tools.ThreadManage;
import com.park.view.Park;

public class ConServer {
	
	private Socket socket;
	
	//首次链接服务器
	//传入登录/注册信息
	/**
	 * 注册 true 注册成功 false 注册失败  断开链接
	 * 登录 true 登录成功 false 登录失败 保持链接
	 * @param message
	 * @return
	 */
	public boolean conToServer(Message message) {
		
		boolean result = false;
		
		try {
			socket = new Socket("115.29.55.106", 10000);
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
			//注册处理
			if(message.getMessageType()==Data.RegInfo){
				oos.writeObject(message);
				//接收返回消息
				message = (Message) ois.readObject();
				if(message.getStatu()==true) {
					result = true;
				}
				else {
					result = false;
				}
				//断开链接
				if(oos!=null) oos.close();
				if(ois!=null) ois.close();
				if(socket!=null) socket.close();
			}
			else
			//登录处理
			if(message.getMessageType()==Data.Login) {
				oos.writeObject(message);
				message = (Message) ois.readObject();
				if(message.getStatu()==true) {
					//登录成功保持链接
					result = true;
					System.out.println("登录成功");
					Park.park.getDailyPanel().getText().append(new Date().getTime()+"  登陆成功\n");
					ParkData.ParkName = message.getParkName();
					ParkData.parkAddress = message.getAddress();
					ParkData.parkRemark = message.getRemark();
					ConServerThread conServerThread = new ConServerThread(socket);
					conServerThread.start();
					ThreadManage.ServerThread.put("server", conServerThread);
				}
				else {
					System.out.println("登录失败");
					Park.park.getDailyPanel().getText().append(new Date().getTime()+"  登陆失败\n");
					result = false;
					//断开链接
					if(oos!=null) oos.close();
					if(ois!=null) ois.close();
					if(socket!=null) socket.close();
				}
			}
			
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

}
