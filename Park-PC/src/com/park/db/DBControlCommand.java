package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.park.util.DButil;






public class DBControlCommand {
	
	//添加车位状态
	public void insertInParkstatus(int id,int locked,int ordered,int blank){
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
				System.out.println("插入成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		
	}
	
	public int insertInParkinfo(int parkId,String palte,String telephone,int statu){//parkinfo表的插入
		int result = 0;
		String sql="insert into parkinfo(parkId,palte,telephone,statu) values(?,?,?,?)";
		Connection conn=DButil.open();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,parkId);
			pstmt.setString(2,palte);
			pstmt.setString(3,telephone);
			pstmt.setInt(4,statu);
			int num=pstmt.executeUpdate();
			if(num>0){
				System.out.println("插入成功！！");
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.close(conn);
		}
		
		return result;
	}
	
	public int queryInParkstatus(int ID){//parkstatus查询
    	Connection conn=DButil.open();
    	String sql="select *from parkstatus where id=?";
    	try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				int id=rs.getInt("id");
				int locked=rs.getInt("locked");
				int ordered=rs.getInt("ordered");
				int blank=rs.getInt("blank");
				System.out.println(id+" "+locked+" "+ordered+" "+blank);
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
       	 DButil.close(conn);
        }return 0;
    	
    }
	
	
	public int queryInParkinfo(){//parkinfo查询
    	Connection conn=DButil.open();
    	String sql="select *from parkinfo";
    	try {
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				int ID=rs.getInt("parkId");
				String palte =rs.getString("palte");
				String telephone=rs.getString("telephone");
				int statu=rs.getInt("statu");
				Timestamp starttime=rs.getTimestamp("starttime");
				System.out.println(ID+" "+palte+" "+telephone+" "+statu+" "+starttime);
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
       	 DButil.close(conn);
        }
		return 0;
    	
    }
	
		public void deleteInParkstatus(int id){//parkstatus表的删除
			Connection conn=DButil.open();
		 try{
			 
			 
		    String sql = "delete from Parkstatus where id=?";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, id);
		    int num =pstmt.executeUpdate();
		    if(num>0){
		      System.out.println("删除成功！");
		           }
		   }catch (Exception e) {
		          e.printStackTrace();
		    }finally{
	        	 DButil.close(conn);
	         }
	}
		
	public void deleteInParkinfo(int ID){//parkinfo表的删除
		Connection conn=DButil.open();
		 try{
			 
			 
			    String sql = "delete from Parkinfo where parkId=?";
			    PreparedStatement pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, ID);
		    int num =pstmt.executeUpdate();
		    if(num>0){
		      System.out.println("删除成功");
		           }
		   }catch (Exception e) {
		          e.printStackTrace();
		    }finally{
	        	 DButil.close(conn);
	         }
		 
		  }
	
	public void updateInParkstatus(int id,int locked,int ordered,int blank){ //parkstatus表的更新
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
                 System.out.println("更新成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
     }
	
	public void updateInParkinfo(int parkId,String palte,String telephone,int statu,Timestamp starttime){//parkinfo
		//表的更新 
		Connection conn=null;
        try{
       	
           conn = DButil.open();
             String sql = "update parkinfo set palte=?,telephone=?,statu=?,starttime=? where parkId=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
           
 			pstmt.setString(1,palte);
 			pstmt.setString(2,telephone);
 			pstmt.setInt(3,statu);
 			pstmt.setTimestamp(4, starttime);
 			pstmt.setInt(5,parkId);
             int num = pstmt.executeUpdate();
             if(num>0){
                 System.out.println("更新成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
     }



}
