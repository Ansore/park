package com.park.dto;

/**
 * 数据传输层DTO
 * 返回给手机的数据
 * Created by ansore on 16-9-12.
 */
public class Result<T> {

    private boolean status;
    private String statusInfo;
    private T data;
    //停车场名
    private String parkName;
    //车位号
    private int lotId;
    //支付费用
    private int payNum;

    public int getPayNum() {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public Result(boolean status, String statusInfo){
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
