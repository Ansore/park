package com.park.data;

/**
 * TODO 静态常量 ！！！！！不规范 后面要改
 * 接口常量
 * Created by ansore on 16-9-12.
 */
public interface Data {

    /**
     * PC交互常量
     *
     */
    //登录
    public int Login = 1;

    //注册
    public int RegInfo = 2;

    //服务器向客户端请求车位信息
    public int GetParkInfo = 4;

    //服务器向客户段发送控制信息
    public int ControlInfo = 5;

    //锁定
    public int Lock = 10;

    //解锁
    public int Unlock = 11;

    //服务器向客户端发送预约信息
    public int OrderInfo = 6;

    //双向，结束停车
    public int EndParkInfo = 7;
    //响应
    public int Answer = 20;
    //获取预定信息
    public int GetOrderInfoPC = 8;

}
