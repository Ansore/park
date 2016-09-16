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
    public String Login = "1";

    //注册
    public String RegInfo = "2";

    //服务器向客户端请求车位信息
    public String GetParkInfo = "4";

    //服务器向客户段发送控制信息
    public String ControlInfo = "5";

    //服务器向客户端发送预约信息
    public String OrderInfo = "6";

    //双向，结束停车
    public String EndParkInfo = "7";



    /**
     * 手机交互静态常量
     * action常量
     */
    //注册
    public String Reg = "reg";
    //登录
    public String LoginPhone = "login";
    //获取停车场信息
    public String GetParksInfo = "getParksInfo";
    //获取车位信息
    public String GetLotInfo = "getLotInfo";
    //预订车位
    public String OrderPark = "orderPark";
    //获取预订信息
    public String GetOrderInfo = "getOrderInfo";
    //开闸（主）
    public String OpenMainRelay = "openMainRelay";
    //解锁车位
    public String UnlockRelay = "unlockRelay";
    //锁定车位
    public String LockRelay = "lockRelay";
    //结束停车
    public String EndPark = "endPark";
    //模拟支付
    public String PayMoney = "payMoney";
}
