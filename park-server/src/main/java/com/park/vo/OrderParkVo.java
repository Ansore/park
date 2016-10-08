package com.park.vo;

/**
 * Created by ansore on 16-9-12.
 */
public class OrderParkVo extends CommonVo {

    //停车场ID
    private int parkId;

    //车位ID
    private int spaceId;

    //手机号
    private String telephone;

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
