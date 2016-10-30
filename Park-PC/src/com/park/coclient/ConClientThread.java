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
import com.park.db.UpdateThreadParkStatusLock;
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
	//String[] Id=new String[]{"1","2","3"};
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
						new UpdateThreadParkStatusLock(Integer.valueOf(str[2]), Integer.valueOf(str[3])).start();
					}
					//不存在---添加记录
					else {
						new InsertThreadInParkStatus(Integer.valueOf(str[2]), 0, 0, Integer.valueOf(str[3])).start();
					}
					break;
				}
				break;
			}
		
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
