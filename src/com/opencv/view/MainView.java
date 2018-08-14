package com.opencv.view;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.opencv.listener.ExitKeyEvent;

public class MainView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JLabel imageLabel;

	
	public MainView(){
		this.setUndecorated(true);
       	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
       	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	this.setBounds(0,0,this.getWidth(),this.getHeight());
       	
       	JPanel panel = new JPanel();
       	panel.setLayout(null);
       	panel.setBackground(Color.BLACK);
       	this.add(panel);
       	
       	//將截圖的影像置中
       	imageLabel = new JLabel(); 
       	imageLabel.setSize(800, 600);
       	int max_x = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       	int max_y = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       	int x = (max_x - imageLabel.getWidth()) / 2;
       	int y = (max_y - imageLabel.getHeight()) /2;
    	imageLabel.setLocation(x, y);
       	panel.add(imageLabel);
       	this.addKeyListener(new ExitKeyEvent());
       	
       	this.setVisible(true); 
	}
	
	public void update(BufferedImage image) {
		Icon icon = new ImageIcon(image);
		imageLabel.setIcon(icon);
		this.revalidate();
		System.out.println("display");
	}
}
