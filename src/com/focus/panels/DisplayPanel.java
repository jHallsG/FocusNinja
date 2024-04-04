package com.focus.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import com.focus.components.Components;

public class DisplayPanel{
	
	public static JPanel display() {
		
		int workDuration = Components.getWorkDuration().getValue();
		int longBreak = Components.getLongBreak().getValue();
		int shortBreak = Components.getShortBreak().getValue();
		int seconds = 0;
		
		String formattedSeconds = String.format("%02d", seconds);
		
		JLabel timerLabel = new JLabel(workDuration + " : " + formattedSeconds);
		JPanel display = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(1,5,10,5);
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			
//		Timer countDownTimer = new Timer(1000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				seconds--;
//				timerLabel.setText(String.format("%d:%02d",workDuration, seconds));
//			}
//		});
		
		JButton playButton = new JButton("Start");
//		compFunc.playFunction(playButton, countDownTimer);
		
		
		
		JProgressBar bar = new JProgressBar();
		bar.setValue(0);
		
		display.add(bar,gbc);
		gbc.gridy++;
		display.add(timerLabel,gbc);
		gbc.gridy++;
		display.add(Box.createVerticalStrut(60),gbc);
		gbc.gridy++;
		display.add(playButton,gbc);
		
		
		return display;
	}
}
