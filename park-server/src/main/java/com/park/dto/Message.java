package com.park.dto;

import com.park.enity.ParkStatus;
import java.util.List;

/**
 * Message DTO
 * 与PC客户端通信实体
 * Created by ansore on 16-9-12.
 */
public class Message implements java.io.Serializable {

    //消息类型
    private int messageType;

    //状态
    private boolean statu;

    //注册
    private String telephone;
    private String userName;
    private String password;
    private String parkName;

    //控制信息
    private String controlInfo;

    //停车场车位状态
    private List<ParkStatus> parkList;

    //预约信息
    //车牌
    private String palte;
    //车位编号
    private int parkId;
    //用户手机号   private String telephone;

    //结束停车 车位编号   private int parkId;

    //费用
    private double payNum;

    public String getControlInfo() {
        return controlInfo;
    }

    public void setControlInfo(String controlInfo) {
        this.controlInfo = controlInfo;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public boolean getStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public List<ParkStatus> getParkList() {
        return parkList;
    }

    public void setParkList(List<ParkStatus> parkList) {
        this.parkList = parkList;
    }

    public String getPalte() {
        return palte;
    }

    public void setPalte(String palte) {
        this.palte = palte;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public double getPayNum() {
        return payNum;
    }

    public void setPayNum(double payNum) {
        this.payNum = payNum;
    }
}
