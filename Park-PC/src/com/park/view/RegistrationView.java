package com.park.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.park.view.listener.RegistrationListener;

/**
 * 登陆面板
 * @author ansore
 *
 */

public class RegistrationView extends JFrame {
	private JLabel label1=new JLabel("账号注册");//面板的标题
	private JLabel label2=new JLabel("注册ID(数字):");
	private JLabel label3=new JLabel("停车场名称:");
	private JLabel label4=new JLabel("电话号码:");
	private JLabel label5=new JLabel("地址:");
	private JLabel label6=new JLabel("说明:");
	private JLabel label7=new JLabel("密码:");
	private JLabel label8=new JLabel("确认密码:");
	private JLabel[] a={label2,label3,label4,label5,label6,label7,label8};
	private JTextField textfield1=new JTextField();
	private JTextField textfield2=new JTextField();
	private JTextField textfield3=new JTextField();
	private JTextField textfield4=new JTextField();
	private JTextField textfield5=new JTextField();
	private JTextField[] b={textfield1,textfield2,textfield3,textfield4,textfield5};
	private JPasswordField password1=new JPasswordField();
	private JPasswordField password2=new JPasswordField();
	private JButton button1=new JButton("确认");
	private JButton button2=new JButton("取消");
	private JButton[]d={button1,button2};
	
	//监听事件
	RegistrationListener registrationListener;
	
	public RegistrationView()//对组件布局
	{
		this.setTitle("注册");
		
		registrationListener = new RegistrationListener(this);
		
		Image kt1=Toolkit.getDefaultToolkit().createImage("image//carlogal.png");

		this.setIconImage(kt1);
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/3,Toolkit.getDefaultToolkit().getScreenSize().height/4,375,430);
	
		this.setLayout(null);//布局为空
		int i;
		label1.setBounds(160, 20, 150, 30);//JLabel label1布局 设置大小
		label1.setForeground(Color.BLACK);
		this.add(label1);//在面板添加label x
		for(i=0;i<5;i++)//依次对label[]a布局 设置大小并添加到面板 
			{
				a[i].setBounds(45,80+50*i, 150, 30);
				a[i].setForeground(Color.BLACK);
				this.add(a[i]);
			}
		
		for(i=0;i<3;i++)//依次对JTextField[]b布局 设置大小并添加到面板
			{  
				b[i].setBounds(145,80+50*i, 150, 30);
		       this.add(b[i]);
			}
		password1.setBounds(145,230, 150, 30);//对JPasswordField c布局并设置大小
		this.add(password1);//JPasswordField c添加到面板
		password2.setBounds(145,280, 150, 30);//对JPasswordField c布局并设置大小
		this.add(password2);//JPasswordField c添加到面板
		
		d[0].setBounds(45, 330, 100, 30);//对“确认”按钮布局并设置大小
		d[0].setActionCommand("reg");
		d[0].addActionListener(registrationListener);
		d[0].setBackground(Color.white);
		d[0].setFocusable(false);
		this.add(d[0]);//“确认”按钮添加到面板
		d[1].setBounds(195, 330, 100, 30);//对“重置”按钮布局并设置大小
		d[1].setActionCommand("dispose");
		d[1].addActionListener(registrationListener);
		d[1].setBackground(Color.white);
		d[1].setFocusable(false);
		this.add(d[1]);	//“重置”按钮添加到面板	

		this.setBackground(Color.WHITE);
		this.setVisible(true);
	}


	public JTextField getTextfield1() {
		return textfield1;
	}

	public JTextField getTextfield2() {
		return textfield2;
	}

	public JTextField getTextfield3() {
		return textfield3;
	}

	public JTextField getTextfield4() {
		return textfield4;
	}


	public JTextField getTextfield5() {
		return textfield5;
	}


	public JPasswordField getPassword1() {
		return password1;
	}

	public JPasswordField getPassword2() {
		return password2;
	}
}

