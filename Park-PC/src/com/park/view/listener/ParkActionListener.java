package com.park.view.listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.park.data.Data;
import com.park.dto.Message;
import com.park.tools.ThreadManage;
import com.park.view.EndPark;
import com.park.view.Login;
import com.park.view.ParkForm;




/**
 * 按钮监听事件
 * @author silver
 *
 */


public class ParkActionListener implements java.awt.event.ActionListener {
	
	JPanel cardLayout;
	
	public ParkActionListener(JPanel jPanel) {
		this.cardLayout = jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("carStateButton")){
			((CardLayout) cardLayout.getLayout()).show(cardLayout, "statePanel");
		}
		
		if(e.getActionCommand().equals("parkNewsButton")) {
			((CardLayout) cardLayout.getLayout()).show(cardLayout, "newsPanel");
		}
		if(e.getActionCommand().equals("parkDailyButton")){
			((CardLayout) cardLayout.getLayout()).show(cardLayout, "dailyPanel");
		}
		//登录服务器按钮监听事件响应
		if(e.getActionCommand().equals("enterButton")) {
			System.out.println(ThreadManage.ServerThread.size());
			if(ThreadManage.ServerThread.size()==0) {
			new Login();
			}
			else {
				JOptionPane.showMessageDialog(null,"已链接服务器", "ERROR",  JOptionPane.ERROR_MESSAGE); 
				return;
			}
		}
		//停车表单填写
		if(e.getActionCommand().equals("noBookkingButton")) {
			new ParkForm();
		}
		
		//结束停车
		if(e.getActionCommand().equals("endPark")) {
			new EndPark();
		}
	}

}
