package com.focus.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.focus.components.Components;
import com.focus.functions.ButtonFunctions;

public class DisplayPanel{
	
	
	public static JPanel display() {
		
		JPanel display = new JPanel(new GridBagLayout());
		
		JLabel timerLabel = new JLabel(String.format("%02d",Components.getWorkDuration().getValue()) + " : " + String.format("%02d", 0));
		Components.setTimerLabel(timerLabel);
		
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(1,5,10,5);
			gbc.gridy = 0;
			gbc.gridwidth = 3;

		JButton playButton = new JButton("Start");
		playButton.addActionListener(new ButtonFunctions(playButton, "play"));
		
		

		JProgressBar bar = new JProgressBar();
		bar.setForeground(Color.green);
//		bar.setValue(Components.getWorkDuration().getValue());
		
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
