//package com.park.dao;
//
//import com.park.dao.UserDAO;
//import com.park.enity.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.slf4j.Logger;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by ansore on 16-9-11.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/spring.xml")
//public class UserDaoTest {
//
//    @Autowired
//    UserDAO userDAO;
//
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Test
//    public void userIsExistTest() throws Exception {
//        String s = "21111111111";
//        int i = userDAO.userIsExist(s);
//        logger.info("user is = {}", i);
//    }
//
//    @Test
//    public void addUserTest(){
//        User u = new User();
//        u.setUsername("Ansore");
//        u.setPalte("ESSSSpp");
//        u.setPassword("123");
//        u.setTelephone("22222222226");
//        int t = userDAO.addUser(u);
//        logger.info("User={}", t);
//    }
//
//    @Test
//    public void userLoginTest(){
//        String t = "11111111111";
//        String p = "123";
//
//        int i = userDAO.userLogin(t,p);
//
//        logger.info("result={}",i);
//    }
//
//}
