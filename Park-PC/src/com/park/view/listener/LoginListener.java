package com.park.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.park.conserver.ConServer;
import com.park.data.Data;
import com.park.data.ParkData;
import com.park.dto.Message;
import com.park.tools.ThreadManage;
import com.park.view.Login;
import com.park.view.Park;
import com.park.view.RegistrationView;

public class LoginListener implements ActionListener {
	
	//传入Login对象
	private Login login;
	
	public LoginListener(Login login) {
		this.login = login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//注册按钮响应 
		if(e.getActionCommand().equals("enrollButton")) {
			new RegistrationView();
			this.login.dispose();
		}
		
		//登陆按钮响应
		if(e.getActionCommand().equals("okButton")) {
			if(login.getUsername().getText().trim().equals("")||String.valueOf(login.getPasswordN().getPassword()).trim().equals("")){
				JOptionPane.showMessageDialog(null,"请输入完整信息", "ERROR",  JOptionPane.ERROR_MESSAGE); 
			}
			else {
				int userName = Integer.valueOf(this.login.getUsername().getText().trim());
				String password = new String(this.login.getPasswordN().getPassword()).trim();
				
				Message message = new Message();
				message.setMessageType(Data.Login);
				message.setParkId(userName);
				message.setPassword(password);
				Boolean boolean1 = new ConServer().conToServer(message);
				if(boolean1==true) {
					Park.park.getDisplayLabel().setText("已链接上服务器");
					ParkData.ParkId = userName;
					this.login.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"用户名或密码错误", "ERROR",  JOptionPane.ERROR_MESSAGE); 
				}
				
				System.out.println(userName + "---" + password);
			}
		}
	}

}
