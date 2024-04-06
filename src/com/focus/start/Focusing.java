package com.focus.start;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.focus.panels.MainBody;
import com.focus.panels.SettingsPanel;

public class Focusing {

	public static void main(String[] args) {
		
		SettingsPanel.settings();
		MainBody body = new MainBody();

		JFrame mainWindow = new JFrame();
		mainWindow.setTitle("Pomodoro Focus Ninja");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(400, 600);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		
		mainWindow.add(body.head(),BorderLayout.NORTH);
		mainWindow.add(body.body(),BorderLayout.CENTER);
		mainWindow.add(body.base(),BorderLayout.SOUTH);
		
		mainWindow.setVisible(true);
	}
}

