//package com.park.dao;
//
//
//import com.park.enity.OrderInfo;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
///**
// * Created by ansore on 16-10-6.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/spring.xml")
//
//public class OrderInfoTest {
//
//    @Autowired
//    OrderInfoDAO orderInfoDAO;
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Test
//    public void orderIsExist() throws Exception {
//        String phone = "12345678910";
//        int i = orderInfoDAO.orderIsExist(phone);
//        logger.info("result is {}",i);
//    }
//
//    @Test
//    public void addOrderInfo() throws Exception {
////        String phone = "12345678910";
////        int parkid = 1;
////        OrderInfo orderInfo = new OrderInfo();
////        orderInfo.setPhone(phone);
////        orderInfo.setParkid(parkid);
////
////        int i = orderInfoDAO.addOrderInfo(orderInfo);
//        logger.info("result is {}",orderInfoDAO.getOrderInfo("11111111111").getParkid());
//    }
//
//    @Test
//    public void delOrderInfo() throws Exception {
//        String phone = "12345678910";
//        int i = orderInfoDAO.delOrderInfo(phone);
//        logger.info("result is {}",i);
//    }
//
//}
