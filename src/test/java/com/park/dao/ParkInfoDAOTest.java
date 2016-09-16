package com.park.dao;

import com.park.enity.ParkInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ansore on 16-9-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class ParkInfoDAOTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ParkInfoDAO parkInfoDAO;

    @Test
    public void addPark() throws Exception {
        ParkInfo p = new ParkInfo();
        p.setTelephone("11111111111");
        p.setParkid(11);
        p.setName("向启怀");
        p.setPassword("123");

        int i = parkInfoDAO.addPark(p);

        logger.info("Result={}",i);
    }

    @Test
    public void parkIsExistTest() throws Exception {
        int id = 1234;
        int i = parkInfoDAO.parkIsExist(id);
        logger.info("result = {}", i);

    }

    @Test
    public void parkLogin() throws Exception {

        int id = 12345;
        String p = "13";

        int i = parkInfoDAO.parkLogin(id,p);

        logger.info("result is = {}", i);

    }

    @Test
        public void getParksList() throws Exception {

        List<ParkInfo> l = parkInfoDAO.getParksList();

        for(int i = 0; i<l.size(); i ++) {
            logger.info("L is {}",l.get(i).getName());
        }
    }

}