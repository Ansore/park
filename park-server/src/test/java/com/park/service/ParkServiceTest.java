//package com.park.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.*;
//
///**
// * Created by ansore on 16-9-13.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/spring.xml")
//public class ParkServiceTest {
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
////    @Autowired
////    ParkService parkService;
//
//    @Test
//    public void parkRegister() throws Exception {
//
//    }
//
//    @Test
//    public void parkLogin() throws Exception {
//
//        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath*:spring/spring.xml");
//        ParkService parkService = (ParkService) applicationContext.getBean("parkServiceImpl");
//
//        try {
//            int i = parkService.parkLogin(12345,"123");
//
//            logger.info("sss = {}",i);
//        } catch (Exception e)
//        {
//            logger.info("123 = {}",e);
//            e.printStackTrace();
//        }
//
//
//    }
//
//}