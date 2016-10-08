package com.park.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.park.view.listener.LoginListener;

/**
 * 登陆窗口
 * @author ansore
 *
 */

public class Login extends JFrame {
	
	private Container c;
	//用户名
	private JTextField username;
	//密码
	private JPasswordField password;
	//登录按钮
	private JButton okButton;
	//注册按钮
	private JButton enrollButton;
	//按钮监听
	private LoginListener loginListener;
	
	public Login(){
		
		this.setTitle("登录");
		
		
		//初始化组建
		loginListener = new LoginListener(this);
		username = new JTextField();
		password = new JPasswordField();
		okButton = new JButton("登陆");
		okButton.setActionCommand("okButton");
		okButton.addActionListener(loginListener);
		enrollButton = new JButton("注册");
		enrollButton.setActionCommand("enrollButton");
		enrollButton.addActionListener(loginListener);
		
		
		c = this.getContentPane();
		
		c.setLayout(new BorderLayout());
		
		initFrame();
		
		c.setBackground(Color.red);
		
		Image kt1=Toolkit.getDefaultToolkit().createImage("image//carlogal.png");
		
		this.setIconImage(kt1);
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/3,Toolkit.getDefaultToolkit().getScreenSize().height/3,300,200);
		this.setVisible(true);
		this.setResizable(true);
	}

	/**
	 * 初始化组建布局
	 */
	private void initFrame() {
			
		//顶部
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(new JLabel("服务器登陆"));
		c.add(titlePanel,"North");
		titlePanel.setBackground(Color.white);
		
		//中部表单
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		fieldPanel.setBackground(Color.black);
		JLabel l1 = new JLabel("用户名:");
		l1.setBounds(50, 20, 50, 20);
		l1.setForeground(Color.white);
		JLabel l2 = new JLabel("密    码:");
		l2.setForeground(Color.white);
		l2.setBounds(50, 60, 50, 20);
		fieldPanel.add(l1);
		fieldPanel.add(l2);
		username.setBounds(110,20,120,20);
		password.setBounds(110,60,120,20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel,"Center");
		
		//底部按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
	
		enrollButton.setBackground(Color.black);
		enrollButton.setForeground(Color.white);
		enrollButton.setFocusPainted(false);
		
		okButton.setBackground(Color.black);
		okButton.setForeground(Color.white);
		okButton.setFocusPainted(false);

		buttonPanel.add(okButton);
		buttonPanel.add(enrollButton);
		c.add(buttonPanel,"South");
	}

	public JTextField getUsername() {
		return username;
	}

	public JPasswordField getPasswordN() {
		return password;
	}
	
}
