package com.opencv.main;

import com.opencv.service.DetectFaceOnTimeService;
import com.opencv.view.MainView;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		MainView mainView = new MainView();
		DetectFaceOnTimeService service = new DetectFaceOnTimeService(mainView);	
		service.server();
	}

}
