package com.park.service;

import com.park.enity.ParkInfo;

import java.util.List;

/**
 * 停车场Service
 * Created by ansore on 16-9-12.
 */
public interface ParkService {

    /**
     * 注册停车场
     * @param parkInfo
     * @return 1成功
     *          0失败
     */
    int parkRegister(ParkInfo parkInfo);

    /**
     * 停车场登录
     * @param parkid
     * @param password
     * @return
     */
    int parkLogin(int parkid,String password);

    /**
     * 获取停车场列表
     * @return
     */
    List<ParkInfo> getParksInfoList();
}
