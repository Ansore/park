package com.park.view;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

/**
 * 日志Panel
 * 窗口 text
 * @author ansore
 *
 */

public class DailyRecordView extends JPanel{
	
	
	private JLabel label1=new JLabel();
	private JLabel label2=new JLabel();
	private JLabel label3=new JLabel("车场日志");
	private JLabel label4=new JLabel();
	private JLabel label5=new JLabel();
	private JLabel[]a={label1,label2,label3,label4,label5};
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JPanel panel4=new JPanel();
	private JPanel panel5=new JPanel();
	private JPanel panel6=new JPanel();
	private JPanel panel7=new JPanel();
	private JPanel panel8=new JPanel();
	private JPanel panel9=new JPanel();
	private JPanel panel10=new JPanel();
	private JPanel panel11=new JPanel();
	private JPanel panel12=new JPanel();
	private JPanel panel13=new JPanel();
	private JPanel panel14=new JPanel();
	private JPanel panel15=new JPanel();
	private JPanel panel16=new JPanel();
	private JPanel panel17=new JPanel();
	private JPanel panel18=new JPanel();
	private JPanel panel19=new JPanel();
	private JPanel panel20=new JPanel();
	private JPanel panel21=new JPanel();
	private JPanel[]b={panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11,panel12,panel13};
	private JPanel[]d={panel14,panel15,panel16,panel17,panel18,panel19,panel20,panel21};
	
	//显示日志面板
	JTextArea text=new JTextArea();
	
	
	public DailyRecordView ()
	{
			this.setBackground(Color.BLACK);//设置面板颜色为黑色
			GridBagLayout gridbag =new GridBagLayout();//创建网格包对象
			GridBagConstraints c=new GridBagConstraints();//创建网格包的约束对象
			setLayout(gridbag);//设置面板包布局为网格包
			c.fill=GridBagConstraints.BOTH;//组件充满显示区域
			a[2].setHorizontalAlignment(SwingConstants.CENTER);
			a[2].setForeground(Color.YELLOW);//设置标签上的字体颜色为黄色
			a[2].setFont(new Font("Monospaced",Font.BOLD,30));//标签的字体和大小
			c.weightx=1;//水平拉伸幅度为1
			c.weighty=1;//垂直拉伸幅度为1
			gridbag.setConstraints(a[0], c);
			add(a[0]);//添加到面板
			for(int i=0;i<8;i++)
		{  
			d[i].setBackground(Color.BLACK);//空白面板为黑色
			gridbag.setConstraints(d[i], c);
			add(d[i]);//添加到面板
		}	
			c.gridwidth=0;//panel1为此行最后一个0000000000
			gridbag.setConstraints(a[1], c);
			add(a[1]);//添加到面板
			c.gridheight=11;//垂直占用格子数为11
			c.gridwidth=1;//水平占用格子数为1
			panel1.setBackground(Color.BLACK);//空白面板为黑色
			gridbag.setConstraints(panel1,c);
			add(panel1);
			c.gridheight=1;//垂直占用格子数为1
			c.gridwidth=8;//水平占用格子数为8
			gridbag.setConstraints(a[2],c);
			add(a[2]);
			c.gridwidth=0;//panel1为此行最后一个0000000000
			panel2.setBackground(Color.BLACK);//空白面板为黑色
			gridbag.setConstraints(panel2, c);
			add(panel2);//添加到面板
			c.gridheight=10;//垂直占用格子数为1
			c.gridwidth=8;//水平占用格子数为8
			text.setEditable(false);
			text.setFont(new Font("Monospaced",Font.BOLD,20));
			gridbag.setConstraints(text,c);
			add(text);
			c.gridwidth=0;//此行最后一个组件
			c.gridheight=1;//垂直占用格子数
			for(int i=0;i<10;i++)
			{
			b[i].setBackground(Color.BLACK);//空白面板为黑色
			gridbag.setConstraints(b[i], c);
			add(b[i]);//空白面板panel b[i]依次添加到面板
			}
			c.gridwidth=1;//水平占用格子数为1
			gridbag.setConstraints(a[3],c);
			add(a[3]);
			c.gridwidth=8;//水平占用格子数为8
			panel3.setBackground(Color.BLACK);//空白面板为黑色
			gridbag.setConstraints(panel3,c);
			add(panel3);
			c.gridwidth=1;//水平占用格子数为1
			gridbag.setConstraints(a[4],c);
			add(a[4]);
			}
}
