package com.park.dao;

import com.park.enity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ansore on 16-9-11.
 */
public interface UserDAO {

    /**
     * 检测用户是否存在
     * @return
     */
    int userIsExist(@Param("telephone") String telephone);

    /**
     * Add Phone User
     * 添加手机用户
     * @param u
     * @return
     */
    int addUser(User u);

    /**
     * 验证用户登录
     * @param telephone
     * @param password
     * @return 0用户名或密码错误
     *          1 正常登录
     */
    int userLogin(@Param("telephone") String telephone,@Param("password") String password);


}
