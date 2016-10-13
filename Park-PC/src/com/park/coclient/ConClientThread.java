package com.park.coclient;

/*
 * 服务器接收与发送的线程
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;

import com.park.db.DBControlCommand;
import com.park.db.InsertThreadInParkStatus;
import com.park.db.QueryInParkStatus;
import com.park.db.UpdateThreadInParkInfo;
import com.park.db.UpdateThreadParkStatusBlank;
import com.park.tools.ThreadManage;
import com.park.util.TimeMinus;
import com.park.view.Park;


public class ConClientThread extends Thread {

	Socket s;
	
	private String name;
	
	DBControlCommand db=new DBControlCommand();
	
	TimeMinus timeMinus=new TimeMinus();
	PrintWriter printWriter=null;
    Timestamp timestamp;
	String[] Id=new String[]{"1","2","3"};
	public ConClientThread(Socket socket,String name){
		
		this.s=socket;
		this.name = name;
	}

	public void SenderMessages(String s){
	
		try {
//			printWriter = new PrintWriter(this.s.getOutputStream());
//			printWriter.println(s);
			// Park.park.getDailyPanel().getText().append(new Date()+"发送控制信息成功\n");
//			printWriter.flush();
			
			OutputStream os = this.s.getOutputStream();
			os.write(s.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
	}
		
	public void run(){
		
		boolean b = true;
		BufferedReader br;
		while (b){
		try {
			br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			
			String RevData = br.readLine();
			System.out.println("接收数据："+RevData);
			String[] str = RevData.split(" ");
			if(str[1]!=null)
			switch(str[1]){
			
			case "hcsr":
				String i = new QueryInParkStatus(Integer.valueOf(str[2])).call();
				//存在--改变状态
				if(i.equals("1")) {
					new UpdateThreadParkStatusBlank(Integer.valueOf(str[2]), Integer.valueOf(str[3])).start();
					if(new QueryInParkStatus(Integer.valueOf(str[2])).callParkStatus().getOrdered()==1) {
						new UpdateThreadInParkInfo().UpdateThreadInParkStatus(Integer.valueOf(str[2]), 1);
					}
				}
				//不存在---添加记录
				else {
					new InsertThreadInParkStatus(Integer.valueOf(str[2]), 0, 0, Integer.valueOf(str[3])).start();
				}
				break;
				
			case "relay":
				//主闸
				if(str[2].equals("main")) {
					// TODO
				}
				else {
					String ii = new QueryInParkStatus(Integer.valueOf(str[2])).call();
					//存在--改变状态
					if(ii.equals("1")) {
				//		new UpdateThreadParkStatusBlank(Integer.valueOf(str[2]), Integer.valueOf(str[3])).start();
					}
					//不存在---添加记录
					else {
						new InsertThreadInParkStatus(Integer.valueOf(str[2]), 0, 0, Integer.valueOf(str[3])).start();
					}
					break;
				}
				break;
			}
			
//			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(s.getInputStream()));
//			String string=null;
//			System.out.println("开始读取");
//			while((string=bufferedReader.readLine())!=null){
//				//解析开始
//				String[] string2=string.split(" ");
//				for(int i=0;i<string2.length;i++){
//					switch (string2[i]) {
//					case "hcsr":
//						 for (String string3 : Id) {//查找编号
//							if(string2[i+1].equals(string3)){
//						      if(string2[i+2].equals("0")){//hcsr编号状态
//						    	  QueryThreadInParkStatus queryThread=new QueryThreadInParkStatus(Integer.parseInt(string2[i+1]));
//						    	  
//						    		if(queryThread.call().equals("0")){//parkstatus表查询结果为空
//						    		}else{//否则删除
//						    			DeleteThreadInParkStatus deleteThread=
//						    					new DeleteThreadInParkStatus(Integer.parseInt(string2[i+1]));
//						    			deleteThread.start();
//						    		}
//						      }else if (string2[i+2].equals("1")) {//状态为1，有车进入，插入表
//								InsertThreadInParkStatus insertThread=new 
//										InsertThreadInParkStatus(Integer.parseInt(string2[i+1]),1, 0, 1);
//								insertThread.start();
//							}
//						}
//					}
//						
//						break;
//					case "relay":
//						for (String string3 : Id) {
//							if(string2[i+1].equals(string3)){
//								if(string2[i+2].equals("0")){//继电器状态为0
//									QueryThreadInParkInfo queryThread=new QueryThreadInParkInfo(Integer.parseInt(string2[i+1]));
//									if(queryThread.call().equals("0")){//若parkinfo表信息为空,则没有停车，更新Locked状态为0
//										UpdateInParkStatusLockedOnly updateThread=new 
//											UpdateInParkStatusLockedOnly(Integer.parseInt(string2[i+1]), 0);
//									updateThread.start();
//									}else{//若parkinfo显示有车位信息，则删除车位信息，获取时间戳，更新locked状态为0
//										QueryThreadTimestampOnly queryThread1=new QueryThreadTimestampOnly(Integer.parseInt(string2[i+1]));
//										timestamp=queryThread1.call();
//										//计算时间
//										long minutes=timeMinus.minus(timestamp);
//										System.out.println("分钟为为"+minutes);
//										DeleteThreadInParkInfo deleteThread=new 
//											DeleteThreadInParkInfo(Integer.parseInt(string2[i+1]));
//										deleteThread.start();
//										UpdateInParkStatusLockedOnly updateThread=new 
//												UpdateInParkStatusLockedOnly(Integer.parseInt(string2[i+1]), 0);
//										updateThread.start();
//									}
//									
//								}else 
//									if(string2[i+2].equals("1")){//若有车进入，插入车位信息表，更新locked状态为1
//										InsertThreadInParkInfo insertThread=new 
//												InsertThreadInParkInfo(Integer.parseInt(string2[i+1]), "null", "null", 1);
//										insertThread.start();
//										UpdateInParkStatusLockedOnly updateThread=new 
//												UpdateInParkStatusLockedOnly(Integer.parseInt(string2[i+1]), 1);
//										updateThread.start();
//									}
//									
//								
//							}
//						}
//						
//						if(string2[i+1].equals("main")){
//							System.out.println("收到闸信息，状态："+string2[i+2]);
//						}
//						
//                        break;
//                        
//                    default:
//						break;
//					}
//				}
//			}
		
		} catch (Exception e) {
			ConClientThread c=ThreadManage.ClientThread.get(name);
			ThreadManage.ClientThread.remove(name);
			c.interrupt();
			b = false;
			e.printStackTrace();
		}
	}
}
}
