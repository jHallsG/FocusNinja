package com.focus.start;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.focus.panels.MainBody;


public class Focusing {

	public static void main(String[] args) {
		JFrame mainWindow = new JFrame();
		
		mainWindow.setTitle("Pomodoro Focus Ninja");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(400, 600);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.add(initializeUI(), BorderLayout.CENTER);
		mainWindow.setVisible(true);
	}
	
	private static JPanel initializeUI() {
		MainBody body = new MainBody();
		
		// Replace main panel background
		JPanel mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					Image backgroundImage = ImageIO.read(new File("Extra/hilltopsbg.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                	System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
		};
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(body.body(),BorderLayout.CENTER);
		mainPanel.add(body.base(),BorderLayout.SOUTH);
		mainPanel.add(body.head(),BorderLayout.NORTH);
		return mainPanel;
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

