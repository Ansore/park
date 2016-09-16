package com.park.socket;


import com.park.data.Data;
import com.park.dto.Message;
import com.park.enity.ParkInfo;
import com.park.exception.ParkException;
import com.park.service.ParkService;
import com.park.service.impl.ParkServiceImpl;
import com.park.socketmanage.SocketThreadManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ansore on 16-9-12.
 */
public class ServerSocketThread extends Thread {


//    ParkService parkService = (ParkService) org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext().getBean("ParkService");

    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring/spring.xml");
    ParkService parkService = (ParkService) applicationContext.getBean("parkServiceImpl");

    private ServerSocket serverSocket;

    public ServerSocketThread() {
        try {
            if(null == serverSocket) {
                serverSocket = new ServerSocket(10000);
                //TODO
                System.out.println("线程已经创建");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void destroyedServerSocket(){
        try {
            if(null != this.serverSocket && !this.isInterrupted()) {
                this.serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

            try {
                while (true) {
                    System.out.println("正在监听10000端口");
                    Socket s = this.serverSocket.accept();
                    System.out.println("有链接");
                    //接收客户端发来的信息
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    Message m = (Message) ois.readObject();
                    //判断登录/注册信息
                    if(m.getMessageType().equals(Data.RegInfo)){
                        ParkInfo p = new ParkInfo();
                        p.setName(m.getParkName());
                        p.setPassword(m.getPassword());
                        p.setParkid(m.getParkId());
                        p.setTelephone(m.getTelephone());
                        try {
                            if(parkService.parkRegister(p)==1) {
                                m = new Message();
                                m.setStatu(true);
                                oos.writeObject(m);
                            }
                            else {
                                m = new Message();
                                m.setStatu(false);
                                oos.writeObject(m);
                            }
                        } catch (ParkException pe) {
                            m = new Message();
                            m.setStatu(false);
                            oos.writeObject(m);
                        }
                        oos.close();
                        ois.close();
                        s.close();
                    }
                    else {
                        //登录处理
                        if (m.getMessageType().equals(Data.Login)) {
                            try {
                                if(parkService.parkLogin(m.getParkId(),m.getPassword())==1) {
                                    Message message = new Message();
                                    message.setStatu(true);
                                    oos.writeObject(message);
                                    SocketThread socketThread = new SocketThread(s,m.getParkId());
                                    SocketThreadManage.socketThread.put(m.getParkId(),socketThread);
                                    socketThread.start();
                                }
                                else {
                                    System.out.println("else 关闭链接");
                                    m = new Message();
                                    m.setStatu(false);
                                    oos.writeObject(m);
                                    oos.close();
                                    ois.close();
                                    s.close();
                                }
                            } catch (ParkException pe) {
                                System.out.println("catch1关闭链接");
                                m = new Message();
                                m.setStatu(false);
                                oos.writeObject(m);
                                oos.close();
                                ois.close();
                                s.close();
                            }
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("catch2关闭链接");
                e.printStackTrace();

            }

    }
}
