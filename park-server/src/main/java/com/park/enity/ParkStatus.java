package com.park.enity;

/**
 * 与PC交互实体类
 * 停车场车位状态实体类
 * Created by ansore on 16-9-12.
 */
public class ParkStatus implements java.io.Serializable {

    private int id;
    private int locked;
    private int ordered;
    private int blank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getBlank() {
        return blank;
    }

    public void setBlank(int blank) {
        this.blank = blank;
    }
}
