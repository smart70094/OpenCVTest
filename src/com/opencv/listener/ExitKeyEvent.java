package com.opencv.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExitKeyEvent implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String cmd = String.valueOf(e.getKeyChar());
		if(cmd.equalsIgnoreCase("q"))
			System.exit(0);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
