package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;


public class DeleteThreadInParkStatus  extends Thread{

	int id;
	
	public DeleteThreadInParkStatus(int Id){
		this.id=Id;
	}
	
	public void run(){
		Connection conn=DButil.open();
		 try{
			 
		    String sql = "delete from Parkstatus where id=?";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, id);
		    int num =pstmt.executeUpdate();
		    
		    if(num>0){
		      System.out.println("删除车位信息成功！");
		    }
		   }catch (Exception e) {
		          e.printStackTrace();
		    }finally{
	        	 DButil.close(conn);
	         }
	}
}
