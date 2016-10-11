package com.park.view;

import java.awt.Color;
import java.awt.Font;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.park.db.QueryInParkInfo;
import com.park.db.QueryTimestampOnly;
import com.park.enity.ParkInfo;
import com.park.enity.ParkStatus;
import com.park.util.TimeMinus;

public class ParkStatuView extends JPanel {
	
	public ParkStatuView(int location,ParkStatus parkStatus) {
		
		//停车时长
		long minutes = 0;
		ParkInfo parkInfo=null;
		
			this.setLayout(null);
			this.setBounds(location,0,220,275);//255
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
			 	minutes=new TimeMinus().minus(parkInfo.getStarttime());
				pictureJLabel.setIcon(new ImageIcon("image//车位黑白.png"));
			}
			else {
				pictureJLabel.setIcon(new ImageIcon("image//车位黄黑.png"));
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
				plate.setText("null");
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
				phoneNum.setText("null");
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
			if(parkStatus.getOrdered()==1&&parkInfo!=null&&minutes!=0) {
				timeTextJLabel.setText(minutes+" 分钟");
			}
			else {
				timeTextJLabel.setText("null");
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
	}

}
