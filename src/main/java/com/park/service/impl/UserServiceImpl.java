package com.park.service.impl;

import com.park.dao.UserDAO;
import com.park.exception.ParkException;
import com.park.exception.StatusEnum;
import com.park.service.UserService;
import com.park.vo.LoginVo;
import com.park.vo.RegisterVo;
import com.park.enity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ansore on 16-9-12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDAO userDAO;

    @Override
    public int userRegister(RegisterVo registerVo) {

        int result = 0;

        //检测用户是否存在
        if(userDAO.userIsExist(registerVo.getTelephone())==1){
            throw new ParkException(StatusEnum.getStatusCode(300));
        }

        //实体转化
        User u = new User();
        u.setPassword(registerVo.getPassword());
        u.setTelephone(registerVo.getTelephone());
        u.setPalte(registerVo.getPalte());
        u.setUsername(registerVo.getUsername());

        //注册用户
        if(userDAO.addUser(u)==1){
            result = 1;
        }
        else {
            throw new ParkException(StatusEnum.getStatusCode(301));
        }
        return result;
    }

    @Override
    public int userLogin(LoginVo loginVo) {

        int result = 0;
        int i = userDAO.userLogin(loginVo.getTelephone(),loginVo.getPassword());
        if(i==0){
            throw new ParkException(StatusEnum.getStatusCode(302));
        }
        if(i>1) {
            throw new ParkException(StatusEnum.getStatusCode(100));
        }
        if(i==1) {
            result = 1;
        }
        return result;
    }
}
