package com.park.dao;

import com.park.enity.OrderInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ansore on 16-10-6.
 */
public interface OrderInfoDAO {
    /**
     * 查看用户是否已经预约
     * 0 否 1 是
     * @param phone
     * @return
     */
    int orderIsExist(@Param("phone") String phone);

    /**
     * 添加预约信息
     * @return
     */
    int addOrderInfo(OrderInfo orderInfo);

    /**
     * 删除预约记录
     * @param phone
     * @return
     */
    int delOrderInfo(@Param("phone") String phone);

    /**
     * 获取预定信息
     * @param phone
     * @return
     */
    OrderInfo getOrderInfo(@Param("phone") String phone);
}
