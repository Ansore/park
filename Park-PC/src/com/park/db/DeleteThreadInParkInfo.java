package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;


public class DeleteThreadInParkInfo extends Thread {

	int parkId;
	public DeleteThreadInParkInfo(int ParkId){
		this.parkId=ParkId;
	}
	
	public void run(){
		Connection conn=DButil.open();
		 try{
			    String sql = "delete from parkinfo where parkid=?";
			    PreparedStatement pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, parkId);
		    int num =pstmt.executeUpdate();
		    if(num>0){
		      System.out.println("删除停车信息成功");
		          }
		   }catch (Exception e) {
		          e.printStackTrace();
		    }finally{
	        	 DButil.close(conn);
	         }
	}
}
