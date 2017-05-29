package com.park.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import com.park.data.Data;
import com.park.data.ParkData;
import com.park.db.DeleteThreadInParkInfo;
import com.park.db.InsertThreadInParkInfo;
import com.park.db.QueryInParkStatus;
import com.park.db.QueryTimestampOnly;
import com.park.db.UpdateInParkStatusLockedOnly;
import com.park.db.UpdateThreadParkStatusOrder;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
import com.park.view.Park;

public class ParkUtil {
	//结束停车 返回费用
	public static double endPark(int id) {
		QueryTimestampOnly queryThread1=new QueryTimestampOnly(id);
	 	Timestamp timestamp=queryThread1.call();
	 	if(timestamp!=null){
	 	System.out.println(timestamp);
	 	}
	 	//更改车位状态
	 	new UpdateInParkStatusLockedOnly(id, 0).start();
	 	new UpdateThreadParkStatusOrder(id,0).start();
	 	//删除停车信息表信息
	 	new DeleteThreadInParkInfo(id).start();
		//计算时间
		long minutes=new TimeMinus().minus(timestamp);

		//保留两位小数
		BigDecimal bg = new BigDecimal((double)(minutes*1.0/60)*ParkData.ParkNum);  
		double payNum =  bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println("停车时间为："+minutes);
		
		System.out.println("结束停车,费用为："+payNum);
		JOptionPane.showMessageDialog(null,"车位号："+id+" 已结束停车 停车费用为："+payNum+"元\n", "停车结束",  JOptionPane.YES_OPTION); 
	 	Park.park.getDailyPanel().getText().append(new Date().toLocaleString()+":车位号："+id+" 已结束停车 停车费用为："+payNum+"元\n");
		
		if(ThreadManage.ClientThread.size()!=0)
		ThreadManage.ClientThread.get(ParkData.HardWare).SenderMessages("control relay "+id+" 0");

		return payNum;
	}
	
	public static boolean orderSpace(String palte,int id,String phone) {
		ParkStatus parkStatus = new QueryInParkStatus().callParkStatus();
		if(parkStatus==null || parkStatus.getBlank() == 1 || parkStatus.getOrdered() == 1) {
			return false;
		}
		Park.park.getDailyPanel().getText().append(new Date().toLocaleString()+":车牌号： "+palte+" 预约了"+id+"号车位\n");
		//添加预约信息
		new InsertThreadInParkInfo(id, palte, phone, 0).start();
		//更改车位状态
		new UpdateInParkStatusLockedOnly(id, 1).start();
		new UpdateThreadParkStatusOrder(id,1).start();
		ParkControlUtil.lockSpace(id);
		return true;
	}
}
