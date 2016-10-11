package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.park.util.DButil;


public class InsertThreadInParkInfo extends Thread{
	int parkId;
	String palte;
	String telephone;
	int statu;
	public InsertThreadInParkInfo(int ParkId,String Palte,String Telephone,int Statu){
		this.parkId=ParkId;
		this.palte=Palte;
		this.telephone=Telephone;
		this.statu=Statu;
	}
	
	public void run(){
		String sql="insert into parkinfo(parkid,palte,telephone,statu) values(?,?,?,?)";
		Connection conn=DButil.open();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,parkId);
			pstmt.setString(2,palte);
			pstmt.setString(3,telephone);
			pstmt.setInt(4,statu);
			int num=pstmt.executeUpdate();
			if(num>0){
				System.out.println("预约成功！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
	}

}
