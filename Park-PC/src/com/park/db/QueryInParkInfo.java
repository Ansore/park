package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import com.park.enity.ParkInfo;
import com.park.util.DButil;


public class QueryInParkInfo implements Callable<String>{

	int parkId;
	public QueryInParkInfo(int ParkId){
		this.parkId=ParkId;
	}
	
	public QueryInParkInfo() {
		
	}
	
	public String call(){
		Connection conn=DButil.open();
    	String sql="select * from parkinfo where parkid=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,parkId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				return "1";
			}else{
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
       	 DButil.close(conn);
        }
		return "null";
	}
	
	public int getParkIdCall(String phone) {
		int result = 0;
		Connection conn=DButil.open();
    	String sql="select * from parkinfo where telephone=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,phone);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("parkid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
       	 DButil.close(conn);
        }
		return result;
	}
	
	
	public ParkInfo getParkInfo(int parkid) {
		ParkInfo parkInfo = new ParkInfo();
		Connection conn=DButil.open();
    	String sql="select *from parkinfo where parkid=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,parkid);	
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				parkInfo.setStarttime(rs.getTimestamp("starttime"));
				parkInfo.setTelephone(rs.getString("telephone"));
				parkInfo.setParkid(rs.getInt("parkid"));
				parkInfo.setPlate(rs.getString("palte"));
				return parkInfo;
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
		return parkInfo;
	}
}
