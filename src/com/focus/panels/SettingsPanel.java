package com.focus.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.focus.components.Components;

public class SettingsPanel {

	public static JPanel settings() {
		JPanel settings = new JPanel();
		settings.setLayout(new GridBagLayout());
		settings.setPreferredSize(new Dimension(400, 600));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 5, 10, 5);
		gbc.gridy = 0;
		gbc.gridwidth = 3;

		JLabel workDuration = new JLabel("Work Duration");
		workDuration.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel workDurationVal = new JLabel("25:00");

		JLabel shortBreak = new JLabel("Short Break");
		shortBreak.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel shortBreakVal = new JLabel("5:00");

		JLabel longBreak = new JLabel("Long Break");
		longBreak.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel longBreakVal = new JLabel("15:00");

		JLabel round = new JLabel("Rounds");
		round.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel roundValue = new JLabel("3:00");
		
		JSlider workDurationSlider = new JSlider(1, 60, 25);

		JSlider shortBreakSlider = new JSlider(1, 15, 5);

		JSlider longBreakSlider = new JSlider(1, 30, 15);

		JSlider roundsSlider = new JSlider(1, 10, 3);

		JButton reset = new JButton("Reset");

		// body.setBackground(Color.green);

		Component comp[] = { workDuration, workDurationVal, workDurationSlider, shortBreak, shortBreakVal,
				shortBreakSlider, longBreak, longBreakVal, longBreakSlider, round, roundValue, roundsSlider,
				Box.createVerticalStrut(60), reset };

		for (Component component : comp) {
			settings.add(component, gbc);
			gbc.gridy++;
		}

		return settings;
	}
}
