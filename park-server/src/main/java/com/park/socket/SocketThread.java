package com.park.socket;

import com.park.data.Data;
import com.park.dto.Message;
import com.park.socketmanage.SocketThreadManage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ansore on 16-9-12.
 */

public class SocketThread extends Thread {

    private Socket socket;
    //链接键值
    private int key;

    private Message info;

    //输入输出流
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public SocketThread(Socket s, int k) {

        this.key = k;
        this.socket = s;

        try{
            this.ois = new ObjectInputStream(s.getInputStream());
            this.oos = new ObjectOutputStream(s.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMessage(Message message){
        try {
            this.oos.writeObject(message);
            this.oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message sendMessageWait(Message message) {
        Message message1 =  new Message();
        message1.setStatu(false);
        try {
            this.oos.writeObject(message);
            this.oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i=0;
        while(i<10) {
            if(info!=null){
                if(info.getMessageType()==Data.Answer) {
                    return info;
                }}
            i++;
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return message1;
    }

    public void freeMessage() {
        this.info = null;
    }

    @Override
    public void run() {

        System.out.println("服务器一个线程启动");
        boolean b = true;

        while(b) {
            System.out.println("读取消息");
            try {
                Message m = (Message) this.ois.readObject();

                switch (m.getMessageType()) {
                    case Data.Answer:
                        this.info = m;
                        break;
                }

            } catch (Exception e) {
                try {
                    b = false;
                    System.out.println("捕获异常1");
                    //结束线程
                    SocketThreadManage.socketThread.get(this.key).interrupt();
                    SocketThreadManage.socketThread.remove(this.key);
                    this.socket.close();
                } catch (Exception e2) {
                    b = false;
                    System.out.println("捕获异常2");
                    e2.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
