package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;

public class UpdateThreadInParkStatus extends Thread {

	int id;
	int locked;
	int ordered;
	int blank;
	public UpdateThreadInParkStatus(int Id,int Locked,int Ordered,int Blank){
		this.id=Id;
		this.locked=Locked;
		this.ordered=Ordered;
		this.blank=Blank;
	}
	
	public void run(){
		Connection conn=null;
        try{
           conn = DButil.open();
             String sql = "update parkstatus set locked=?,ordered=?,blank=? where id=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ;
             pstmt.setInt(1, locked);
             pstmt.setInt(2, ordered);
             pstmt.setInt(3, blank);
             pstmt.setInt(4, id);
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
