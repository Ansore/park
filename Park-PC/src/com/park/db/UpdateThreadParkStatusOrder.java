package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;

public class UpdateThreadParkStatusOrder extends Thread {
	
	int id;
	int ordered;
	public UpdateThreadParkStatusOrder(int Id,int ordered){
		this.id=Id;
		this.ordered=ordered;
	}
	
	public void run(){
		
		Connection conn=null;
        try{
           conn = DButil.open();
             String sql = "update parkstatus set ordered=? where id=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ;
             pstmt.setInt(1, ordered);
             pstmt.setInt(2, id);
             int num = pstmt.executeUpdate();
             if(num>0){
                 System.out.println("更新车位预约状态成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
}

}
