package com.focus.start;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.focus.panels.MainBody;


public class Focusing {

	public static void main(String[] args) {
		
		MainBody body = new MainBody();

		JFrame mainWindow = new JFrame();
		mainWindow.setTitle("Pomodoro Focus Ninja");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(400, 600);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		
		mainWindow.add(body.body(),BorderLayout.CENTER);
		mainWindow.add(body.base(),BorderLayout.SOUTH);
		mainWindow.add(body.head(),BorderLayout.NORTH);
		
		
		
		mainWindow.setVisible(true);
	}
}



/*
 * Future Actions:
 * 1. Add a checkbox for activating/deactivating mouse "kidnapper"
 * 2. Refactor ButtonFunctions. Lots of duplicate codes
 * 3. Maybe configurable sound after each timer ends
 * 4. Add a background pic. App is plain Gray.
 * 5. Add progress bar functionality
 */

