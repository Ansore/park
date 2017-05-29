package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.Callable;

import com.park.util.DButil;


public class QueryTimestampOnly  implements Callable<Timestamp>{
	int parkId;
	Timestamp tms;
	public QueryTimestampOnly(int ParkId){
		this.parkId=ParkId;
	}
	
	public Timestamp call(){
		Connection conn=DButil.open();
    	String sql="select * from parkinfo where parkid=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,parkId);	
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				tms=rs.getTimestamp("starttime");
				return tms;
			}else{
				System.out.println("获取失败");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
       	 DButil.close(conn);
        }
		return null;
	}
}
