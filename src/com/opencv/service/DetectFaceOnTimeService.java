package com.opencv.service;

import java.awt.image.BufferedImage;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.opencv.utils.DetectFaceUtils;
import com.opencv.view.MainView;



public class DetectFaceOnTimeService {
	
	private DetectFaceUtils detectFaceUtils;
	private MainView mainView;
	private BlockingQueue<BufferedImage> q = new LinkedBlockingQueue<>();
	
	public DetectFaceOnTimeService(MainView mainView){
		this.mainView = mainView;
	}
	
	public void server() throws InterruptedException {
		detectFaceUtils = new DetectFaceUtils();
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//			public void run() {
//				detectFace();
//				System.out.println("yes");
//			}
//		};
//		timer.schedule(task, 5000,1);
		
		webCamInputStream();
		while(true) 
			mainView.update(q.take());
	}
	
	private void webCamInputStream() {
		new Thread(() -> {
			try {
				while(true) {
					BufferedImage image = detectFaceUtils.detectFace();
					q.put(image);
					System.out.println("catch picture");
				}
			}catch(Exception e) {
				System.out.println("DetectFaceOnTimeService: " + e.toString());
			}
		}).start();
	} 
}
