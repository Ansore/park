package com.park.vo;

/**
 * 登录Vo
 * Created by ansore on 16-9-12.
 */
public class LoginVo extends CommonVo {
    private String telephone;
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
