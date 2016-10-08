package com.park.vo;

/**
 * 获取某停车场车位信息
 * Created by ansore on 16-9-12.
 */
public class GetLotInfoVo extends CommonVo {
    private int parkId;

    public int getParkid() {
        return parkId;
    }

    public void setParkid(int parkId) {
        this.parkId = parkId;
    }
}
