package com.park.listener;

import com.park.socket.ServerSocketThread;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听类 启动socket 线程
 * Created by ansore on 16-9-12.
 */
public class SocketServiceLoader implements ServletContextListener {

    //Socket服务器线程
    private ServerSocketThread serverSocketThread;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("进入监听器");
        //启动线程
        if(null == serverSocketThread) {
            serverSocketThread = new ServerSocketThread();
            serverSocketThread.start();
            System.out.println("启动线程");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //结束后销毁线程
        if(null != serverSocketThread) {
            //释放资源 , 结束线程
            serverSocketThread.destroyedServerSocket();
            serverSocketThread.interrupt();
        }
    }
}
