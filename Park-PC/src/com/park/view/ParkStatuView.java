package com.park.view;

import java.awt.Color;
import java.awt.Font;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.park.db.QueryInParkInfo;
import com.park.db.QueryTimestampOnly;
import com.park.enity.ParkInfo;
import com.park.enity.ParkStatus;
import com.park.util.TimeMinus;
import com.park.view.listener.ParkStatusListener;

public class ParkStatuView extends JPanel {
	
	public ParkStatuView(int location,ParkStatus parkStatus) {
		
		//停车时长
		long minutes = 0;
		ParkInfo parkInfo=null;
		
			this.setLayout(null);
			this.setBounds(location,0,220,300);//255
			this.setBackground(new Color(225,215,0));
			
			//顶部显示车位序号的标签
			JLabel textJlJLabel=new JLabel("车位编号: "+parkStatus.getId(),SwingConstants.CENTER);//用来显示每个车位的编号
			textJlJLabel.setFont(new Font("宋体", Font.BOLD, 20));
			textJlJLabel.setForeground(Color.RED);
			textJlJLabel.setSize(220, 30);
			textJlJLabel.setOpaque(true);//设置颜色可见
			textJlJLabel.setBackground(Color.white);
			
			//中间显示车位状态的图片
			JLabel pictureJLabel=new JLabel();
			pictureJLabel.setBounds(0,30,220, 150);
			pictureJLabel.setOpaque(true);
			pictureJLabel.setBackground(Color.white);
			//锁定和未锁定显示不同的照片，照片在image中获取
			if(parkStatus.getOrdered()==1) {
				parkInfo=new QueryInParkInfo().getParkInfo(parkStatus.getId());
				if(parkInfo!=null){
					minutes=new TimeMinus().minus(parkInfo.getStarttime());
				}
				pictureJLabel.setIcon(new ImageIcon(getClass().getResource("/image/车位黑白.png")));
			}
			else {
				pictureJLabel.setIcon(new ImageIcon(getClass().getResource("/image/车位黄黑.png")));
			}
			
			//车牌号码标签设置显示
			JLabel numLabel=new JLabel("状态：");
			numLabel.setBounds(0,180,60,25);
			numLabel.setOpaque(true);
			numLabel.setForeground(Color.white);
			numLabel.setBackground(new Color(5,10,10));

			//标签设置显示
			JLabel numTextJLabel=new JLabel();
			if(parkStatus.getOrdered()==1&&parkStatus.getBlank()==1) {
				numTextJLabel.setText("已停车");
			}
			else if(parkStatus.getOrdered()==1&&parkStatus.getBlank()==0) {
				numTextJLabel.setText("已预约");
			} else if(parkStatus.getOrdered()==0&&parkStatus.getBlank()==1){
				numTextJLabel.setText("车位状态异常");
			} else {
				numTextJLabel.setText("空闲");
			}
			numTextJLabel.setOpaque(true);
			numTextJLabel.setForeground(Color.white);
			numTextJLabel.setBackground(new Color(5,10,10));
			numTextJLabel.setBounds(60,180,160,25);
			
			//标签设置显示
			JLabel plateLabel=new JLabel("车牌号: ");
			plateLabel.setBounds(0, 205, 60, 25);
			plateLabel.setOpaque(true);
			plateLabel.setForeground(Color.white);
			plateLabel.setOpaque(true);
			plateLabel.setBackground(new Color(225,215,0));
			
			//标签设置显示
			JLabel plate=new JLabel();
			if(parkStatus.getOrdered()==1&&parkInfo!=null) {
				plate.setText(parkInfo.getPlate());
			}
			else {
				plate.setText("无");
			}
			plate.setBounds(60, 205, 160, 25);
			plate.setOpaque(true);
			plate.setForeground(Color.white);
			plate.setBackground(new Color(225,215,0));
			
			
			//标签设置显示
			JLabel telephone=new JLabel("电话: ");
			telephone.setBounds(0, 230, 60, 25);
			telephone.setOpaque(true);
			telephone.setForeground(Color.white);
			telephone.setOpaque(true);
			telephone.setBackground(new Color(5,10,10));
			//标签设置显示
			JLabel phoneNum=new JLabel();
			if(parkStatus.getOrdered()==1&&parkInfo!=null) {
				phoneNum.setText(parkInfo.getTelephone());
			}
			else {
				phoneNum.setText("无");
			}
			phoneNum.setBounds(60, 230, 160, 25);
			phoneNum.setOpaque(true);
			phoneNum.setForeground(Color.white);
			phoneNum.setBackground(new Color(5,10,10));
			phoneNum.setBackground(new Color(5,10,10));
			
			//标签设置显示
			JLabel timeLabel=new JLabel("时长: ");
			timeLabel.setBounds(0, 255, 60, 25);
			timeLabel.setOpaque(true);
			timeLabel.setForeground(Color.white);
			timeLabel.setOpaque(true);
			timeLabel.setBackground(new Color(225,215,0));
			
			//标签设置显示
			JLabel timeTextJLabel=new JLabel();
			if((parkStatus.getBlank()==1||parkStatus.getOrdered()==1)&&parkInfo!=null) {
				timeTextJLabel.setText(minutes+" 分钟");
			}
			else {
				timeTextJLabel.setText("无");
			}
			timeTextJLabel.setBounds(60, 255, 160, 25);
			timeTextJLabel.setOpaque(true);
			timeTextJLabel.setForeground(Color.white);
			timeTextJLabel.setBackground(new Color(5,10,10));
			timeTextJLabel.setBackground(new Color(225,215,0));
			
			
			
			//向总面板上添加组件
			
			this.add(textJlJLabel);
			this.add(pictureJLabel);
			this.add(numLabel);
			this.add(numTextJLabel);
			this.add(timeLabel);
			this.add(timeTextJLabel);
			this.add(telephone);
			this.add(phoneNum);
			this.add(plateLabel);
			this.add(plate);
			
			
			//按钮
			//预约按钮
			JButton orderPark=null;
			//解锁
			JButton unlockPark=null;
			//锁定
			JButton lockPark = null;
			//结束停车按钮
			JButton endPark=null;
			//监听器
			ParkStatusListener parkStatusListener = new ParkStatusListener(parkStatus);
			
			if(parkStatus.getOrdered()==1) {
				unlockPark = new JButton("解锁车位");
				lockPark = new JButton("锁定车位");
				endPark = new JButton("结束停车");
				
				unlockPark.setFocusPainted(false);
				unlockPark.setForeground(Color.white);
				unlockPark.setBorder(null);
				unlockPark.setBackground(Color.GREEN);
				unlockPark.setBounds(0, 280, 73, 25);
				unlockPark.setActionCommand("unlock");
				unlockPark.addActionListener(parkStatusListener);

				lockPark.setFocusPainted(false);
				lockPark.setForeground(Color.white);
				lockPark.setBorder(null);
				lockPark.setBackground(Color.RED);
				lockPark.setBounds(73, 280, 73, 25);
				lockPark.setActionCommand("lock");
				lockPark.addActionListener(parkStatusListener);
				
				endPark.setFocusPainted(false);
				endPark.setForeground(Color.white);
				endPark.setBorder(null);
				endPark.setBackground(Color.GRAY);
				endPark.setBounds(146, 280, 74, 25);
				endPark.setActionCommand("end");
				endPark.addActionListener(parkStatusListener);
				
				this.add(unlockPark);
				this.add(lockPark);
				this.add(endPark);
				
				
			} else {
				orderPark = new JButton("预约停车");
				
				orderPark.setFocusPainted(false);
				orderPark.setForeground(Color.white);
				orderPark.setBorder(null);
				orderPark.setBackground(Color.BLUE);
				orderPark.setBounds(0, 280, 220, 25);
				orderPark.setActionCommand("order");
				orderPark.addActionListener(parkStatusListener);
				this.add(orderPark);
			}
	}

}
