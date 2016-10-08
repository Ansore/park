package com.park.enity;

import java.sql.Date;

public class DBelements {
     private int id;//parkstatus表的id
     private int locked;
     private int ordered;
     public int getOrdered() {
		return ordered;
	}
	public void setOrdered(int ordered) {
		this.ordered = ordered;
	}
	private int blank;
     private int parkId; //parkinfo表的ID
     private String palte;
     public String getPalte() {
		return palte;
	}
	public void setPalte(String palte) {
		this.palte = palte;
	}
	private String telephone;
     private int statu;
     private Date starttime;


	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getStatu() {
		return statu;
	}
	public void setStatu(int statu) {
		this.statu = statu;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
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
	public int getBlank() {
		return blank;
	}
	public void setBlank(int blank) {
		this.blank = blank;
	}
    
}
