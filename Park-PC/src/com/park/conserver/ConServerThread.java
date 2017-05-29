package com.park.conserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.park.coclient.ConClientThread;
import com.park.data.Data;
import com.park.data.ParkData;
import com.park.db.DeleteThreadInParkInfo;
import com.park.db.InsertThreadInParkInfo;
import com.park.db.QueryInParkInfo;
import com.park.db.QueryInParkStatus;
import com.park.db.QueryTimestampOnly;
import com.park.db.UpdateInParkStatusLockedOnly;
import com.park.db.UpdateThreadParkStatusOrder;
import com.park.dto.Message;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
import com.park.util.ParkControlUtil;
import com.park.util.ParkUtil;
import com.park.util.TimeMinus;
import com.park.view.Park;

public class ConServerThread extends Thread {
	
	Socket socket;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Message info=null;
	
	public ConServerThread(Socket socket) {
		System.out.println("创建线程");
		this.socket = socket;
		try {
			this.oos = new ObjectOutputStream(this.socket.getOutputStream());
			ois = new ObjectInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SendMessage(Message message){
		try {
			this.oos.writeObject(message);
			this.oos.flush();
			System.out.println("发送信息成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		boolean b = true;
		while(b) {
			System.out.println("线程启动");
			try {
				Message message = (Message) this.ois.readObject();
				switch(message.getMessageType()) {
				
				//获取车位信息处理
				case Data.GetParkInfo:
					List<ParkStatus> l = new QueryInParkStatus().callAll();
					Message m = new Message();
					m.setStatu(true);
					m.setMessageType(Data.Answer);
					m.setParkList(l);
					SendMessage(m);
					break;
					
				//结束停车
				case Data.EndParkInfo:
					Message message2 = new Message();
					if((new QueryInParkStatus().callParkStatus()).getBlank()==1) {
						message2.setStatu(false);
						SendMessage(message2);
					}
					else {
						
						double payNum = ParkUtil.endPark(message.getParkId());
						message2.setStatu(true);
						message2.setMessageType(Data.Answer);
						message2.setPayNum(payNum);
						
						SendMessage(message2);
					}
					break;
				//处理预约信息
				case Data.OrderInfo:
					Message message3 = new Message();
					if(ParkUtil.orderSpace(message.getPalte(), message.getParkId(), message.getTelephone())==false) {
						message3.setMessageType(Data.Answer);
						message3.setStatu(false);
					} else {
						message3.setMessageType(Data.Answer);
						message3.setStatu(true);
					}
					SendMessage(message3);
					break;
					
				//处理获取预约信息
				case Data.GetOrderInfoPC:
					Message message4 = new Message();
					message4.setMessageType(Data.Answer);
					message4.setParkId(new QueryInParkInfo().getParkIdCall(message.getTelephone()));
					message4.setParkName(ParkData.ParkName);
					System.out.println("停车场ming:"+ParkData.ParkName+"   jijji："+new QueryInParkInfo().getParkIdCall(message.getTelephone()));
					message4.setStatu(true);
					SendMessage(message4);
					break;
					
				//锁
				case Data.Lock:
					Message message5 = new Message();
					if(ParkControlUtil.lockSpace(message.getParkId())==true) {
						message5.setMessageType(Data.Answer);
						message5.setStatu(true);
					} else {
						message5.setMessageType(Data.Answer);
						message5.setStatu(false);
					}
					SendMessage(message5);
					break;
					
				//解锁
				case Data.Unlock:
					Message message6 = new Message();
					if(ParkControlUtil.unlockSpace(message.getParkId())==true) {
						message6.setMessageType(Data.Answer);
						message6.setStatu(true);
					} else {
						message6.setMessageType(Data.Answer);
						message6.setStatu(false);
					}
					SendMessage(message6);
					break;
					
					//LED
				case Data.LEDOn: 
					Message message7 = new Message();
					if(ParkControlUtil.onLED(message.getParkId())==true) {
						message7.setMessageType(Data.Answer);
						message7.setStatu(true);
						new Runnable() {
							@Override
							public void run() {
								try {
									Thread.sleep(360000);
									ParkControlUtil.offLED(message.getParkId());
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}.run();
					} else {
						message7.setMessageType(Data.Answer);
						message7.setStatu(false);
					}
					SendMessage(message7);
					break;
					
				case Data.LEDOff: 
					Message message8 = new Message();
					if(ParkControlUtil.offLED(message.getParkId())==true) {
						message8.setMessageType(Data.Answer);
						message8.setStatu(true);
					} else {
						message8.setMessageType(Data.Answer);
						message8.setStatu(false);
					}
					SendMessage(message8);
					break;
				}
				
			} catch (Exception e) {
				
				try {
					b = false;
					System.out.println("断开服务器");
					ThreadManage.ServerThread.remove("server");
					Park.park.getDisplayLabel().setText("断开服务器");
					//打印日志
					Park.park.getDailyPanel().getText().append(new Date()+":断开服务器\n");
					e.printStackTrace();
					this.socket.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO: handle exception
			}
		}
		
	}

}
