package com.park.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.park.conserver.ConServer;
import com.park.data.Data;
import com.park.dto.Message;
import com.park.view.RegistrationView;

public class RegistrationListener implements ActionListener {
	
	private RegistrationView registrationView;
	
	public RegistrationListener(RegistrationView registrationView) {
		this.registrationView = registrationView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//注册处理
		if(e.getActionCommand().equals("reg")){
			
			int parkId;
			
			try {
				parkId = Integer.valueOf(this.registrationView.getTextfield1().getText().trim());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"请输入正确ID", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String parkName = this.registrationView.getTextfield2().getText().trim();
			String phone = this.registrationView.getTextfield3().getText().trim();
			String passwd1 = new String(this.registrationView.getPassword1().getPassword()).trim();
			String passwd2 = new String(this.registrationView.getPassword2().getPassword()).trim();
			
			if(parkName.equals("")||phone.equals("")||passwd1.equals("")||passwd2.equals("")) {
				JOptionPane.showMessageDialog(null,"请输入完整信息", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(!Pattern.compile("[0-9]*").matcher(phone).matches()) {
				JOptionPane.showMessageDialog(null,"请输入正确手机号", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(!passwd1.equals(passwd2)) {
				JOptionPane.showMessageDialog(null,"两次密码不一致", "ERROR",  JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			//注册
			Message message = new Message();
			message.setMessageType(Data.RegInfo);
			message.setParkId(parkId);
			message.setParkName(parkName);
			message.setPassword(passwd1);
			message.setTelephone(phone);
			
			boolean result = new ConServer().conToServer(message);
			if(result == true) {
				JOptionPane.showMessageDialog(null,"注册成功，请返回登陆", "SUCESS",  JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"注册失败，未知错误", "ERROR",  JOptionPane.ERROR_MESSAGE);
			}
			
			System.out.println(parkId+ "-----"+parkName + "---" + phone+ "---"+passwd1 + "---" + passwd2);
			
		}
		
		//取消处理
		if(e.getActionCommand().equals("dispose")) {
			this.registrationView.dispose();
		}
		
	}

}
