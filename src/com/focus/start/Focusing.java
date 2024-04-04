package com.focus.start;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.focus.panels.MainBody;

public class Focusing {

	public static void main(String[] args) {
		
		MainBody main = new MainBody();

		JFrame mainWindow = new JFrame();
		mainWindow.setTitle("Pomodoro Focus Ninja");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(400, 600);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		
		mainWindow.add(main.head(),BorderLayout.NORTH);
		mainWindow.add(main.body(),BorderLayout.CENTER);
		mainWindow.add(main.base(),BorderLayout.SOUTH);
		
		mainWindow.setVisible(true);
	}
}

