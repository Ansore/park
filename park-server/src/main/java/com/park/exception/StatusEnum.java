package com.park.exception;

/**
 * 异常枚举类
 * Created by ansore on 16-9-12.
 */
public enum StatusEnum {
    SERVERERROR(100,"服务器错误"),
    ERORR(101,"未知错误"),
    VALUENOTCOMPLETE(102,"参数不齐全"),
    REQUESTERROR(103,"请求方式应为POST"),
    PCNOCON(104,"客户端未链接"),
    GETTIMEOUT(105,"请求超时"),
    ORDERED(106,"已经预约"),
    NOORDER(107,"没有预约"),
    LOGINSUCCESS(200,"登录成功"),
    REGFILED(201,"注册成功"),
    GETSUCESS(202,"获取成功"),
    PAYSUCESS(203,"支付成功"),
    CONTROLSUCESS(204,"控制成功"),
    ORDERSUCESS(205,"预约成功"),
    USERISEXIST(300,"用户已存在"),
    USERREGFAILED(301,"用户注册失败"),
    USERLOGINFAILED(302,"用户名或密码错误"),
    USERISNOEXIST(303,"获取用户失败"),
    PARKISEXIST(400,"停车场已存在"),
    PARKREGFAILED(401,"停车场注册失败"),
    PARKRLOGINFAILED(402,"用户名或密码错误")
    ;

    private int statusCode;
    private String statusInfo;

    private StatusEnum(int statusCode,String statusInfo){
        this.statusCode = statusCode;
        this.statusInfo = statusInfo;
    }

    public static StatusEnum getStatusCode(int statusCode){
        for(StatusEnum statusEnum : values()){
            if(statusEnum.getStatusCode()==statusCode){
                return statusEnum;
            }
        }
        return null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
