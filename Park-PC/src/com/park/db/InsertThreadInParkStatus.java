package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.park.util.DButil;

public class InsertThreadInParkStatus extends Thread {

	int id;
	int locked;
	int ordered;
	int blank;
	public InsertThreadInParkStatus(int Id,int Locked,int Ordered,int Blank){
		this.id=Id;
		this.locked=Locked;
		this.ordered=Ordered;
		this.blank=Blank;
	}
	
	public void run(){
		
		String sql="insert into parkstatus(id,locked,ordered,blank) values(?,?,?,?)";
		
		Connection conn=DButil.open();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setInt(2,locked);
			pstmt.setInt(3,ordered);
			pstmt.setInt(4,blank);
			int num=pstmt.executeUpdate();
			if(num>0){
				System.out.println("添加车位状态成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
	}
}
