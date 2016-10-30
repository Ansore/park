package com.park.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

import com.park.data.ParkData;
import com.park.db.DeleteThreadInParkInfo;
import com.park.db.QueryInParkStatus;
import com.park.db.QueryTimestampOnly;
import com.park.db.UpdateInParkStatusLockedOnly;
import com.park.db.UpdateThreadParkStatusOrder;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
import com.park.util.TimeMinus;
import com.park.view.EndPark;
import com.park.view.Park;

public class EndParkListener implements ActionListener {
	
	private EndPark endPark;
	
	public EndParkListener(EndPark endPark) {
		this.endPark = endPark;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		//取消按钮响应
		if(e.getActionCommand().equals("canel")) {
			System.out.println("取消");
			this.endPark.dispose();
		}
		else
		if(ThreadManage.ClientThread.get(ParkData.HardWare)==null) {
			JOptionPane.showMessageDialog(null,"设备未链接", "ERROR",  JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		//确认按钮响应
		if(e.getActionCommand().equals("sure")) {
			String space = this.endPark.getSpaceText().getText();
			
			if(!space.matches("^[0-9]*$")||space.trim().equals("")){
				JOptionPane.showMessageDialog(null,"请输入正确的车位号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			ParkStatus parkStatus = new QueryInParkStatus(Integer.valueOf(space)).callParkStatus();
			if(parkStatus==null) {
				JOptionPane.showMessageDialog(null,"请输入正确的车位号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				if(parkStatus.getBlank()!=0&&parkStatus.getOrdered()==1) {
					JOptionPane.showMessageDialog(null,"请开走您的车", "ERROR",  JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					QueryTimestampOnly queryThread1=new QueryTimestampOnly(Integer.valueOf(space));
				 	Timestamp timestamp=queryThread1.call();
				 	if(timestamp!=null){
				 	System.out.println(timestamp);
				 	}
				 	//更改车位状态
				 	new UpdateInParkStatusLockedOnly(Integer.valueOf(space), 0).start();
				 	new UpdateThreadParkStatusOrder(Integer.valueOf(space),0).start();
				 	//删除停车信息表信息
				 	new DeleteThreadInParkInfo(Integer.valueOf(space)).start();
					//计算时间
					long minutes=new TimeMinus().minus(timestamp);
					Park.park.getDailyPanel().getText().append(new Date()+":车位号："+Integer.valueOf(space)+" 已结束停车 停车费用为："+(double)(minutes*1.0/60)*ParkData.ParkNum+"元\n");
					JOptionPane.showMessageDialog(null,new Date()+":车位号："+Integer.valueOf(space)+" 已结束停车 停车费用为："+(double)(minutes*1.0/60)*ParkData.ParkNum+"元\n", "ERROR",  JOptionPane.OK_OPTION);
					this.endPark.dispose();
				}
			}
		}
	}

}
