package com.park.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.park.data.Data;
import com.park.data.ParkData;
import com.park.db.InsertThreadInParkInfo;
import com.park.db.QueryInParkStatus;
import com.park.db.UpdateThreadParkStatusOrder;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
import com.park.view.ParkForm;

public class ParkFormListener implements ActionListener {
	
	private ParkForm parkForm;
	
	public ParkFormListener(ParkForm parkForm) {
		this.parkForm = parkForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		//取消按钮响应
		if(e.getActionCommand().equals("canel")) {
			System.out.println("取消");
			this.parkForm.dispose();
		}
		else
		if(ThreadManage.ClientThread.get(ParkData.HardWare)==null) {
			JOptionPane.showMessageDialog(null,"设备未链接", "ERROR",  JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		//确认按钮响应
		if(e.getActionCommand().equals("sure")) {
			String name = this.parkForm.getNameText().getText();
			String phone = this.parkForm.getPhoneText().getText();
			String palte = this.parkForm.getPlateText().getText();
			String space = this.parkForm.getSpaceText().getText();
			/**
			 * 检测输入字段
			 */
			if(!phone.matches("^[0-9]*$")||phone.trim().equals("")) {
				JOptionPane.showMessageDialog(null,"请输入正确的手机号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!space.matches("^[0-9]*$")||space.trim().equals("")){
				JOptionPane.showMessageDialog(null,"请输入正确的车位号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(name.trim().equals("")) {
				JOptionPane.showMessageDialog(null,"请输入正确的用户姓名", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(palte.trim().equals("")) {
				JOptionPane.showMessageDialog(null,"请输入正确的车牌号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			ParkStatus parkStatus = new QueryInParkStatus(Integer.valueOf(space)).callParkStatus();
			if(parkStatus==null) {
				JOptionPane.showMessageDialog(null,"请输入正确的车位号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				if(parkStatus.getBlank()!=0||parkStatus.getLocked()!=0||parkStatus.getOrdered()!=0) {
					JOptionPane.showMessageDialog(null,"该车位被预定或者被锁定", "ERROR",  JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					new UpdateThreadParkStatusOrder(Integer.valueOf(space), 1).start();
					new InsertThreadInParkInfo(Integer.valueOf(space), palte, phone, 0).start();;
					JOptionPane.showMessageDialog(null,"预定成功", "SUCESS",  JOptionPane.OK_OPTION);
					this.parkForm.dispose();
				}
			}
		}
		
	}

}
