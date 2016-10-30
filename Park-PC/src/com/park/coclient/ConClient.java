/*
 * 服务器连接客户端线程
 */
package com.park.coclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.park.data.ParkData;
import com.park.tools.ThreadManage;
import com.park.view.Park;

/**
 * 链接硬件
 * @author ansore
 *
 */

public class ConClient extends Thread{
	ServerSocket serverSocket;
    public ConClient(){
    	  try {
			serverSocket=new ServerSocket(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void run(){
    	while(true){
    	try {
    		Park.park.getDailyPanel().getText().append(new Date().toLocaleString()+": 等待设备链接\n");
			Socket socket=serverSocket.accept();
			System.out.println("有链接");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String name = br.readLine().trim();
			System.out.println("第一次收到数据为："+name);
			Park.park.getDailyPanel().getText().append(new Date()+":设备 "+name+" 已经链接\n");
			ConClientThread clientThread=new ConClientThread(socket,name);
			//TODO
			ParkData.HardWare = name;
			
			ThreadManage.ClientThread.put(name, clientThread);
			clientThread.start();
		
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	}
    }
  

	
}
