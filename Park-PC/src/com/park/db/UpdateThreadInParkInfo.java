package com.park.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.park.util.DButil;


public class UpdateThreadInParkInfo extends Thread{

	int parkId;
	String palte;
	String telephone;
	int statu;
	public UpdateThreadInParkInfo(int ParkId,String Palte,String Telephone,int Statu){
		this.parkId=ParkId;
		this.palte=Palte;
		this.telephone=Telephone;
		this.statu=Statu;
	}
	
	public UpdateThreadInParkInfo() {
		
	}
	
	public void UpdateThreadInParkStatus(int id,int sta) {
		Connection conn=null;
        try{
           conn = DButil.open();
             String sql = "update parkinfo set statu=? where parkId=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
           
 			pstmt.setInt(1,sta);
 			pstmt.setInt(2,id);
           int num = pstmt.executeUpdate();
           
           if(num>0){
                 System.out.println("更新停车信息成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
     }
	
	public void run(){
		Connection conn=null;
        try{
       	
           conn = DButil.open();
             String sql = "update parkinfo set palte=?,telephone=?,statu=? where parkId=?";
             PreparedStatement pstmt = conn.prepareStatement(sql);
           
 			pstmt.setString(1,palte);
 			pstmt.setString(2,telephone);
 			pstmt.setInt(3,statu);
 			pstmt.setInt(4,parkId);
             int num = pstmt.executeUpdate();
             if(num>0){
                 System.out.println("更新停车信息成功！！！");
             }
         }catch (Exception e) {
             e.printStackTrace();
             
         }finally{
        	 DButil.close(conn);
         }
     }
		
	}

