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
import com.focus.functions.ButtonFunctions;

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

		JLabel roundValue = new JLabel("3");
		
		JSlider workDurationSlider = new JSlider(1, 30, 25);
		workDurationSlider.addChangeListener(new ButtonFunctions(workDurationVal, workDurationSlider));
		Components.setWorkDuration(workDurationSlider);

		
		JSlider shortBreakSlider = new JSlider(1, 15, 5);
		shortBreakSlider.addChangeListener(new ButtonFunctions(shortBreakVal, shortBreakSlider));
		Components.setShortBreak(shortBreakSlider);

		JSlider longBreakSlider = new JSlider(1, 30, 15);
		longBreakSlider.addChangeListener(new ButtonFunctions(longBreakVal, longBreakSlider));
		Components.setLongBreak(longBreakSlider);

		JSlider roundsSlider = new JSlider(1, 10, 3);
		roundsSlider.addChangeListener(new ButtonFunctions(roundValue, roundsSlider));
		Components.setRounds(roundsSlider);

		JButton reset = new JButton("Reset");
		reset.addActionListener(new ButtonFunctions(workDurationSlider, shortBreakSlider, longBreakSlider, roundsSlider, "reset"));

		Component comps[] = { workDuration, workDurationVal, workDurationSlider, shortBreak, shortBreakVal,
				shortBreakSlider, longBreak, longBreakVal, longBreakSlider, round, roundValue, roundsSlider,
				Box.createVerticalStrut(60), reset };

		for (Component component : comps) {
			settings.add(component, gbc);
			gbc.gridy++;
		}

		return settings;
	}
}
