package com.park.vo;

/**
 * 结束停车Vo
 * Created by ansore on 16-9-12.
 */
public class EndParkVo {

    //停车场Id
    private int parkId;
    //车位Id
    private int spaceId;

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
}
