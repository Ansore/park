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
    	
    	System.out.println("监听10000端口");
    	try {
    		Park.park.getDailyPanel().getText().append(new Date()+":端口开放，等待硬件链接\n");
			Socket socket=serverSocket.accept();
			System.out.println("有链接");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String name = br.readLine().trim();
			System.out.println("第一次收到数据为："+name);
			
			ConClientThread clientThread=new ConClientThread(socket,name);
			ThreadManage.ClientThread.put(name, clientThread);
			clientThread.start();
			
//		    ConClient.sleep(50);
//			HashMapData.conClient.get("k").SenderMessages("control relay 1 0");
//			ConClient.sleep(50);
//			HashMapData.conClient.get("k").SenderMessages("control relay 2 0");
//			ConClient.sleep(50);
//		    HashMapData.conClient.get("k").SenderMessages("control relay 3 0");
//		    ConClient.sleep(50);
//			HashMapData.conClient.get("k").SenderMessages("control relay main 0");
//			HashMapData.conClient.get("k").SenderMessages("getinfo");
		
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	}
    }
  

	
}
