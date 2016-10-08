package com.park.conserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;

import com.park.data.Data;
import com.park.data.ParkData;
import com.park.db.DeleteThreadInParkInfo;
import com.park.db.InsertThreadInParkInfo;
import com.park.db.QueryInParkInfo;
import com.park.db.QueryInParkStatus;
import com.park.db.QueryTimestampOnly;
import com.park.dto.Message;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
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
			// TODO: handle exception
		}
	}
	
	public void SendMessage(Message message){
		try {
			this.oos.writeObject(message);
			this.oos.flush();
			System.out.println("发送信息成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void SendMessageWait(Message message){
//			try {
//				oos.writeObject(message);
//				System.out.println("发送信息成功");
//				oos.flush();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			int i=0;
//			while(i<10) {
//				System.out.println("循环读取");
//				if(info!=null){
//				if(info.getMessageType()==Data.Answer) {
//					System.out.println("跳出循环："+info.getMessageType());
//					break;
//				}}
//				i++;
//				try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			System.out.println("接收到信息了："+info.getMessageType());
//	}
	
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
					m.setMessageType(Data.Answer);
					m.setParkList(l);
					SendMessage(m);
					break;
				//结束停车
				case Data.EndParkInfo:
					Message message2 = new Message();
					if(new QueryInParkStatus().callParkStatus().getBlank()==1) {
						message2.setStatu(false);
					}
					else {
						QueryTimestampOnly queryThread1=new QueryTimestampOnly(1);
					 	Timestamp timestamp=queryThread1.call();
					 	
					 	if(timestamp!=null){
					 	System.out.println(timestamp);}
					 	//删除停车信息表信息
					 	new DeleteThreadInParkInfo(message.getParkId());
					 	
						//计算时间
						long minutes=new TimeMinus().minus(timestamp);
						message2.setStatu(true);
						message2.setMessageType(Data.Answer);
						System.out.println("停车时间为："+minutes);
						message2.setPayNum((double)(minutes*1.0/60)*ParkData.ParkNum);
						System.out.println("结束停车,费用为："+(double)(minutes*1.0/60)*ParkData.ParkNum);
						SendMessage(message2);
					}
					break;

				//处理预约信息
				case Data.OrderInfo:
					Message message3 = new Message();
					new InsertThreadInParkInfo(message.getParkId(), message.getPalte(), message.getTelephone(), 0).start();
					message3.setMessageType(Data.Answer);
					message3.setStatu(true);
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
				}
				
			} catch (Exception e) {
				
				try {
					b = false;
					System.out.println("断开服务器");
					ThreadManage.ServerThread.remove("server");
					Park.park.getDisplayLabel().setText("断开服务器");
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
