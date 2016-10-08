package com.park.service.impl;

import com.park.dao.ParkInfoDAO;
import com.park.enity.ParkInfo;
import com.park.exception.ParkException;
import com.park.exception.StatusEnum;
import com.park.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by ansore on 16-9-12.
 */
@Service
public class ParkServiceImpl implements ParkService {

    @Autowired
    ParkInfoDAO parkInfoDAO;

    @Override
    public int parkRegister(ParkInfo parkInfo) {

        int result = 0;

        //检测是否注册
        if(parkInfoDAO.parkIsExist(parkInfo.getParkid())==1) {
            throw new ParkException(StatusEnum.getStatusCode(400));
        }

        if(parkInfoDAO.addPark(parkInfo)==1) {
            result = 1;
        }
        else {
            throw new ParkException(StatusEnum.getStatusCode(401));
        }

        return result;
    }

    @Override
    public int parkLogin(int parkid, String password) {

        int result = 0;

        int i = parkInfoDAO.parkLogin(parkid,password);

        if(i==0) {
            throw new ParkException(StatusEnum.getStatusCode(402));
        }

        if(i>1) {
            throw new ParkException(StatusEnum.getStatusCode(100));
        }

        if(i==1) {
            result = 1;
        }

        return result;
    }

    /**
     * 获取停车场列表
     * @return
     */

    @Override
    public List<ParkInfo> getParksInfoList() {
        List<ParkInfo> l = null;

        l = parkInfoDAO.getParksList();

        if(l==null || l.size()==0) {
            throw new ParkException(StatusEnum.getStatusCode(100));
        }
        return l;
    }
}
