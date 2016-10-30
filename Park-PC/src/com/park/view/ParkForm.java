package com.park.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import com.park.view.listener.ParkFormListener;

/**
 * 停车表单填写
 * @author ansore
 *
 */

public class ParkForm extends JFrame {
	
	private JLabel label1=new JLabel("表单填写");
	private JLabel nameLabel=new JLabel("车主姓名:");
	private JLabel plateLabel=new JLabel("车牌号码:");
	private JLabel phoneLabel=new JLabel("电话号码:");
	private JLabel spaceLabel=new JLabel("预定车位:");
	private JLabel[]a={nameLabel,plateLabel,phoneLabel,spaceLabel};
	private JTextField nameText=new JTextField();
	private JTextField plateText=new JTextField();
	private JTextField phoneText=new JTextField();
	private JTextField spaceText=new JTextField();
	private JTextField[]b={nameText,plateText,phoneText,spaceText};
	private JButton sure=new JButton("确认");
	private JButton canel=new JButton("取消");
	private JButton[]c={sure,canel};
	
	private ParkFormListener parkFormListener;
	
	private JPanel mainPanel;
	
	public ParkForm() {
		parkFormListener = new ParkFormListener(this);
		Toolkit kt=Toolkit.getDefaultToolkit();
		Dimension screenSize=kt.getScreenSize();
		int screenW=screenSize.width;	//获取当前屏幕的宽
		int screenH=screenSize.height;	//获取当前屏幕的高
		//设置logal,占时显示绝对路径 TODO
		Image kt1=Toolkit.getDefaultToolkit().createImage(getClass().getResource("/image/carlogal.png"));
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		mainPanel.setLayout(null);//布局为空
		
		int i;
		label1.setBounds(125, 20, 150, 30);//JLabel1布局 设置大小
		label1.setForeground(Color.white);
		label1.setFont(new Font("Monospaced",Font.BOLD,15));
		mainPanel.add(label1);//在面板添加Jlabel1
		for(i=0;i<4;i++)//依次对Jlabel[]a布局 设置大小并添加到面板
			{
				a[i].setForeground(Color.white);
				a[i].setBounds(40,80+50*i, 150, 30);
				a[i].setFont(new Font("Monospaced",Font.CENTER_BASELINE,15));
				this.add(a[i]);
			}
		for(i=0;i<4;i++)//依次对JTextField[]b布局 设置大小并添加到面板
			{  b[i].setBounds(140,80+50*i, 150, 30);
		       this.add(b[i]);
			}
		c[0].setBounds(40, 295, 100, 30);//对“确认”按钮布局并设置大小
		c[0].setBackground(Color.white);
		c[0].setFocusPainted(false);
		c[0].setActionCommand("sure");
		c[0].addActionListener(parkFormListener);
		mainPanel.add(c[0]);//“确认”按钮添加到面板
		c[1].setBounds(180, 295, 100, 30);//对“重置”按钮布局并设置大小
		c[1].setBackground(Color.white);
		c[1].setFocusPainted(false);
		c[1].setActionCommand("canel");
		c[1].addActionListener(parkFormListener);
		mainPanel.add(c[1]);	//“重置”按钮添加到面板	
		
		this.setTitle("停车表单填写");
		this.setIconImage(kt1);
		this.setBounds(screenW/3,screenH/4,350,400);
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
	}
	

	public JTextField getPlateText() {
		return plateText;
	}

	public JTextField getPhoneText() {
		return phoneText;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public JTextField getSpaceText() {
		return spaceText;
	}
	
	
}