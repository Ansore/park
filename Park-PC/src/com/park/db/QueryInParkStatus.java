package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.park.enity.ParkStatus;
import com.park.util.DButil;


public class QueryInParkStatus implements Callable<String>{
    int ID;
	public QueryInParkStatus(int Id){
		this.ID=Id;
	}
	
	//空构造函数
	public QueryInParkStatus() {
		
	}
	
	public String call(){
		Connection conn=DButil.open();
    	String sql="select *from parkstatus where id=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ID);
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
	
	public List<ParkStatus> callAll() {
		List<ParkStatus> l = new ArrayList<>();
		Connection conn=DButil.open();
    	String sql="select *from parkstatus";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			System.out.println("获取车位数据");
			while(rs.next()) {
				System.out.println("有车位数据");
				ParkStatus parkStatus = new ParkStatus();
				parkStatus.setId(rs.getInt("id"));
				parkStatus.setBlank(rs.getInt("blank"));
				parkStatus.setLocked(rs.getInt("locked"));
				parkStatus.setOrdered(rs.getInt("ordered"));
				l.add(parkStatus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
       	 DButil.close(conn);
        }
    	System.out.println("车位个数="+l.size());
		return l;
	}
	
	public ParkStatus callParkStatus() {
		ParkStatus parkStatus = new ParkStatus();
		Connection conn=DButil.open();
    	String sql="select * from parkstatus where id=?";
    	try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ID);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				parkStatus.setId(rs.getInt("id"));
				parkStatus.setBlank(rs.getInt("blank"));
				parkStatus.setLocked(rs.getInt("locked"));
				parkStatus.setOrdered(rs.getInt("ordered"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
       	 DButil.close(conn);
        }
		return parkStatus;
	}
}
