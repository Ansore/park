package com.park.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.park.data.ParkData;
import com.park.enity.ParkStatus;
import com.park.tools.ThreadManage;
import com.park.util.ParkControlUtil;
import com.park.util.ParkUtil;
import com.park.view.ParkForm;

public class ParkStatusListener implements ActionListener  {

	private ParkStatus parkStatus;
	
	public ParkStatusListener(ParkStatus parkStatus) {
		this.parkStatus = parkStatus;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("lock")) {
			if(ParkControlUtil.lockSpace(parkStatus.getId())==false) {
				JOptionPane.showMessageDialog(null,"失败，可能该设备没有链接", "ERROR",  JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getActionCommand().equals("unlock")) {
			if(ParkControlUtil.unlockSpace(parkStatus.getId())==false) {
				JOptionPane.showMessageDialog(null,"失败，可能该设备没有链接", "ERROR",  JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getActionCommand().equals("order")) {
			ParkForm parkForm = new ParkForm();
			parkForm.getSpaceText().setEnabled(false);
			parkForm.getSpaceText().setText(this.parkStatus.getId()+"");
		}
		if(e.getActionCommand().equals("end")) {
			
			if(ThreadManage.ClientThread.get(ParkData.HardWare)==null) {
				JOptionPane.showMessageDialog(null,"设备未链接", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			int n = JOptionPane.showConfirmDialog(null, "确认结束吗?", "确认对话框", JOptionPane.YES_NO_OPTION);   
			if (n == JOptionPane.YES_OPTION) {   
				
				ParkUtil.endPark(parkStatus.getId());
				
			} else if (n == JOptionPane.NO_OPTION) {
			}  
		}
	}

}
