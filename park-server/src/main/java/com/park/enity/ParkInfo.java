package com.park.enity;

/**
 * ParkInfo Enity
 * 数据库实体
 * Created by ansore on 16-9-11.
 */
public class ParkInfo {

    private int parkid;
    private String password;
    private String name;
    private String telephone;

    //是否连上服务器 true 是   false 否
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getParkid() {
        return parkid;
    }

    public void setParkid(int parkid) {
        this.parkid = parkid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
