package com.park.service;

import com.park.enity.User;
import com.park.vo.LoginVo;
import com.park.vo.RegisterVo;

/**
 * Created by ansore on 16-9-12.
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerVo
     * @return 1成功
     *         0失败
     */
    int userRegister(RegisterVo registerVo);

    /**
     * 用户登录
     * @param loginVo
     * @return
     */
    int userLogin(LoginVo loginVo);

    /**
     * 根据手机号码获取用户信息
     * @param telephone
     * @return
     */
    User getUser(String telephone);
}
