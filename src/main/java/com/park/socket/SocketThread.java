package com.park.socket;

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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        System.out.println("服务器一个线程启动");
        boolean b = true;

        while(b) {
            try {
                System.out.println("SOCKET IS "+socket==null);

                System.out.println("ObjectInputStream IS "+ois==null);
                    Message m = (Message) this.ois.readObject();
                    System.out.println("收到客户端传来对象");
            } catch (Exception e) {
                try {
                    b = false;
                    System.out.println("捕获异常1");
                    //结束线程
                    SocketThreadManage.socketThread.get(this.key).interrupt();
                    SocketThreadManage.socketThread.remove(this.key);
                    this.socket.close();
                } catch (Exception e2) {
                    System.out.println("捕获异常2");
                    e2.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
