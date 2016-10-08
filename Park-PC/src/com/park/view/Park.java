package com.park.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.park.view.listener.ParkActionListener;

/**
 * 主面板
 * @author ansorewv
 *
 */

public class Park extends JFrame {
	 
	JButton openButton;
	JButton closingButton;
	JLabel blankLabel;
	JButton enterButton;
	JButton freshButton;
	JLabel displayLabel;
	
	JPanel buttonPanel;
	JButton carStateButton;
	JButton parkNewsButton;
	JButton parkDailyButton;
	JButton noBookingButton;
	
	JPanel cardPanel;
	JPanel statePanel;
	JPanel newsPanel;
	DailyRecordView dailyPanel;
	

	public static Park park;
	
	//space sp=new space();
	
	
	//按钮监听事件
	ParkActionListener parkActionListener;
	
	
	public static void main(String[] args){
		park = new Park();
	}
	
	
	public JLabel getDisplayLabel(){
		return this.displayLabel;
	}
	

	public Park(){		
		
		/**
		 * 主面板 卡片布局
		 */
		this.setTitle("Park++");
		
		cardPanel=new JPanel(new CardLayout());//卡片布局面板
		
		//获取当前窗口的大小来设置窗体的大小
		Toolkit kt=Toolkit.getDefaultToolkit();
		Dimension screenSize=kt.getScreenSize();
		int screenW=screenSize.width;	//获取当前屏幕的宽
		int screenH=screenSize.height;	//获取当前屏幕的高
		//设置logal,占时显示绝对路径 TODO
		Image kt1=Toolkit.getDefaultToolkit().createImage("image//carlogal.png");
		
		this.Layout();
		
		
		this.setIconImage(kt1);
		this.setBounds(screenW/7,screenH/7,1000,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	//布局JFrame
	public void Layout(){
		//实例监听事件
		parkActionListener = new ParkActionListener(cardPanel);
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		openButton=new JButton("开闸");
		
		openButton.setFocusPainted(false);//字体去掉边框
		openButton.setForeground(Color.white);//button上字体为黑色
		openButton.setBorderPainted(false);//button边框设置为无
		openButton.setBackground(new Color(5,10,10));//按钮背景颜色
		
		
		closingButton=new JButton("关闸");
		closingButton.setFocusPainted(false);
		closingButton.setForeground(Color.white);
		closingButton.setBorder(null);
		closingButton.setBackground(new Color(5,10,10));
		
		blankLabel=new JLabel("");
		blankLabel.setBorder(BorderFactory.createLineBorder(new Color(5,10,10)));//label的边框颜色
		blankLabel.setOpaque(true);//设置背景颜色需要
		blankLabel.setBackground(new Color(5,10,10));
		blankLabel.setForeground(Color.white);
		
		enterButton=new JButton("登陆服务器");
		enterButton.setActionCommand("enterButton");
		enterButton.addActionListener(parkActionListener);
		enterButton.setForeground(Color.white);//字体颜色
		enterButton.setBorderPainted(false);//button边框设置为无
		enterButton.setFocusPainted(false);
		enterButton.setBackground(new Color(5,10,10));
		
		freshButton=new JButton("刷新");
		freshButton.setFocusPainted(false);
		freshButton.setBackground(new Color(5,10,10));
		freshButton.setBorderPainted(false);//button边框设置为无
		freshButton.setForeground(Color.white);
		
		//设置显示服务器是否连接的JLabel
		displayLabel=new JLabel("未链接服务器",SwingConstants.CENTER);
		displayLabel.setFont(new java.awt.Font("Serlf",1,15));//字体，风格，大小
		//displayLabel.setBorder(BorderFactory.createLineBorder(Color.white));
		displayLabel.setOpaque(true);
		displayLabel.setBackground(new Color(225,215,0));
		
		//面板内添加按钮
		buttonPanel=new JPanel();
		buttonPanel.setBackground(new Color(225,215,0));
		//buttonpanel面板进行网格布局
		buttonPanel.setLayout(new GridLayout(18,1));

		
		//实例化网格布局组件
		statePanel = new JPanel();
		statePanel.setBackground(Color.black);
		//
		//向statepanel面板中添加滑动面板
		//statePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		statePanel.setLayout(null);
		//JPanel jPanel=new JPanel();
		//jPanel.setLayout(null);
		//jPanel.setBackground(Color.red);
		//jPanel.setBounds(m,0,200,200);
		//statePanel.add(jPanel);
		dailyPanel=new DailyRecordView();
		
		//向车位状态面板中添加了八个车位
		int m=0;
		for (int i = 0; i < 8; i++) {
			
			JPanel theCarPanel=new JPanel();//车位总面板
			theCarPanel.setLayout(null);
			theCarPanel.setBounds(m,0,220,275);//255
			theCarPanel.setBackground(new Color(225,215,0));
			
			//顶部显示车位序号的标签
			JLabel textJlJLabel=new JLabel("车位编号",SwingConstants.CENTER);//用来显示每个车位的编号
			textJlJLabel.setSize(220, 30);
			textJlJLabel.setOpaque(true);//设置颜色可见
			textJlJLabel.setBackground(Color.white);
			
			//中间显示车位状态的图片
			JLabel pictureJLabel=new JLabel();
			pictureJLabel.setBounds(0,30,220, 150);
			pictureJLabel.setOpaque(true);
			pictureJLabel.setBackground(Color.white);
			pictureJLabel.setIcon(new ImageIcon("image//车位黑白.png"));//锁定和未锁定显示不同的照片，照片在image中获取
		
			
			//车牌号码标签设置显示
			JLabel numLabel=new JLabel("车牌号码:");
			numLabel.setBounds(0,180,60,25);
			numLabel.setOpaque(true);
			numLabel.setForeground(Color.white);
			numLabel.setBackground(new Color(225,215,0));

			//标签设置显示
			JLabel numTextJLabel=new JLabel();//用来显示停入车位的车的车牌号码
			numTextJLabel.setOpaque(true);
			numTextJLabel.setForeground(Color.white);
			numTextJLabel.setBackground(new Color(225,215,0));
			numTextJLabel.setBounds(60,180,160,25);
			
			//标签设置显示
			JLabel timeLabel=new JLabel("停车时长:");
			timeLabel.setBounds(0, 205, 60, 25);
			timeLabel.setOpaque(true);
			timeLabel.setForeground(Color.white);
			timeLabel.setBackground(new Color(5,10,10));
			timeLabel.setOpaque(true);
			
			//标签设置显示
			JLabel timeTextJLabel=new JLabel();//用来显示停入车辆的停车时间
			timeTextJLabel.setBounds(60, 205, 160, 25);
			timeTextJLabel.setOpaque(true);
			timeTextJLabel.setForeground(Color.white);
			timeTextJLabel.setBackground(new Color(5,10,10));
			

			//向总面板上添加组件
			
			theCarPanel.add(textJlJLabel);
			theCarPanel.add(pictureJLabel);
			theCarPanel.add(numLabel);
			theCarPanel.add(numTextJLabel);
			theCarPanel.add(timeLabel);
			theCarPanel.add(timeTextJLabel);
			statePanel.add(theCarPanel);
			m=m+220;
			
		}
		//dailyPanel.setBackground(new Color(5,10,10));
		
		
		
		newsPanel=new JPanel();
		
		
		//space cpt=new space();//创建JPanel parkview类的对象
		/*cpt.Parkview();//调用parview类中的函数
		JFrame ordertabel=new JFrame("未预定表单填写");
		ordertabel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ordertabel.getContentPane().add(cpt);
		ordertabel.setBounds(600,35,335,450);
		ordertabel.setVisible(true);*/
		
		//添加组件
		cardPanel.add(statePanel,"statePanel");
		cardPanel.add(dailyPanel,"dailyPanel");
		cardPanel.add(newsPanel,"newsPanel");
		//cardPanel.add(ordertabel,"ordertabel");
		
		/**
		 * -------------------
		 */
		
		
		/**
		 * 左排按钮实例初始化
		 */
		carStateButton=new JButton("车位状态");
		carStateButton.setActionCommand("carStateButton");
		carStateButton.setBackground(new Color(225,215,0));//黄色
		carStateButton.setForeground(new Color(5,10,10));
		carStateButton.addActionListener(parkActionListener);//实现监听
		carStateButton.setBorderPainted(false);//button边框设置为无
		carStateButton.setFocusPainted(false);//字体去掉边框
		
		parkNewsButton=new JButton("停车场信息");
		parkNewsButton.setBackground(new Color(225,215,0));
		parkNewsButton.setActionCommand("parkNewsButton");
		parkNewsButton.addActionListener(parkActionListener);
		parkNewsButton.setBorderPainted(false);//button边框设置为无
		parkNewsButton.setFocusPainted(false);//字体去掉边框
		
		parkDailyButton=new JButton("日志");
		parkDailyButton.setBackground(new Color(225,215,0));
		parkDailyButton.setActionCommand("parkDailyButton");
		parkDailyButton.addActionListener(parkActionListener);
		parkDailyButton.setBorderPainted(false);//button边框设置为无
		parkDailyButton.setFocusPainted(false);//字体去掉边框
		
		noBookingButton=new JButton("未预定车位表");
		noBookingButton.setBackground(new Color(225,215,0));
		noBookingButton.setActionCommand("noBookkingButton");
		noBookingButton.addActionListener(parkActionListener);
		noBookingButton.setBorderPainted(false);//button边框设置为无
		noBookingButton.setFocusPainted(false);//字体去掉边框
		
		
		buttonPanel.add(carStateButton);
		buttonPanel.add(parkNewsButton);
		buttonPanel.add(parkDailyButton);
		buttonPanel.add(noBookingButton);
	
		//把组件添加进jframe
		this.add(openButton);
		this.add(closingButton);
		this.add(blankLabel);
		this.add(enterButton);
		this.add(freshButton);
		this.add(displayLabel);
		this.add(buttonPanel);
		this.add(cardPanel);
		
		
		//在JFrame下的
				GridBagConstraints s= new GridBagConstraints();
				//定义一个GridBagConstraints，
				//是用来控制添加进的组件的显示位置
				s.fill = GridBagConstraints.BOTH;
				s.gridwidth=1;
				//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
				//s.gridheight=4;
				s.weightx = 0;
				//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
				s.weighty=0;	
				//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
				layout.setConstraints(openButton, s);
				//设置组件
				
				s.fill = GridBagConstraints.BOTH;
				s.gridwidth=1;
				//s.gridheight=4;
				s.weightx = 0; 
				s.weighty=0;
				layout.setConstraints(closingButton, s); 
				
				s.gridwidth=3;
				//s.gridheight=4;
				s.weightx = 1;
				s.weighty=0; 
				layout.setConstraints(blankLabel, s); 
			
				s.gridwidth=2;
				//s.gridheight=4;
				s.weightx =0;
				s.weighty = 0;
				layout.setConstraints(enterButton,s);
			
				s.gridwidth=0;
				//s.gridheight=4;
				s.weightx = 0;
				s.weighty=0;
				layout.setConstraints(freshButton,s);
				
				s.gridwidth=0;
				//s.gridheight=1;
				s.weightx = 1;
				s.weighty=0;
				layout.setConstraints(displayLabel, s);
				
				s.gridwidth=2;
				//s.gridheight=8;
				s.weightx = 0;
				s.weighty=1;
				layout.setConstraints(buttonPanel, s);
				
				s.gridwidth=0;
				s.gridheight=0;
				s.weightx = 1;
				s.weighty=1;
				layout.setConstraints(cardPanel, s);
	}
}
