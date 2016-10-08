package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;

/**
 * 更新 锁状态的线程
 * @author ansore
 *
 */

public class UpdateThreadParkStatusLock extends Thread {
	int id;
	int locked;
	public UpdateThreadParkStatusLock(int Id,int Locked){
		this.id=Id;
		this.locked=Locked;
	}
	
	public void run(){
		Connection conn=null;
        try{
           conn = DButil.open();
             String sql = "update parkstatus set locked=? where id=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ;
             pstmt.setInt(1, locked);
             pstmt.setInt(2, id);
             int num = pstmt.executeUpdate();
             if(num>0){
                 System.out.println("更新车位状态成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
}
}
