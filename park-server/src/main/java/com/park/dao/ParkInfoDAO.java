package com.park.dao;

import com.park.enity.ParkInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ansore on 16-9-12.
 */
public interface ParkInfoDAO {

    /**
     * 检测停车场是否已经注册
     * @param parkid
     * @return
     */
    int parkIsExist(@Param("parkid") int parkid);

    /**
     * 添加停车场
     * @param parkInfo
     * @return
     */
    int addPark(ParkInfo parkInfo);

    /**
     * 停车场管理登录
     * @param parkid
     * @param password
     * @return
     */
    int parkLogin(@Param("parkid") int parkid,@Param("password") String password);

    /**
     * 获取所有停车场列表
     * @return
     */
    List<ParkInfo> getParksList();
}
