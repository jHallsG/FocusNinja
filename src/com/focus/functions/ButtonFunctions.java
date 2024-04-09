package com.focus.functions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import com.focus.components.Components;

public class ButtonFunctions implements ActionListener {

	private JButton button;
	private String type;
	private boolean settingsActive = false, workRun = false, shortRun = false, longRun = false;;
	private CardLayout cl;
	private JPanel panel;
	private JSlider slider1, slider2, slider3, slider4;
	private static int seconds = 0, rounds, currentRounds = 0;
	private Timer workTimer, shortBreakTimer, longBreakTimer;
	private static int shortBreak = 5, workDuration = 25, longBreak = 15;

	public ButtonFunctions(JButton button, String type) {
		this.button = button;
		this.type = type;
		workDurationTimer();
		shortBreakTimer();
		longBreakTimer();
	}

	public ButtonFunctions(CardLayout cl, JPanel panel, String type) {
		this.cl = cl;
		this.panel = panel;
		this.type = type;
	}

	public ButtonFunctions(JSlider slider1, JSlider slider2, JSlider slider3, JSlider slider4, String type) {
		this.slider1 = slider1;
		this.slider2 = slider2;
		this.slider3 = slider3;
		this.slider4 = slider4;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (type) {
		// swap between settings and display panel
		case "switch":
			if (!settingsActive) {
				settingsActive = true;
				cl.show(panel, "settings");
			} else {
				settingsActive = false;
				seconds = 0;
				// save all component values before switching to display panel
				workDuration = Components.getWorkDuration().getValue();
				shortBreak = Components.getShortBreak().getValue();
				longBreak = Components.getLongBreak().getValue();
				rounds = Components.getRounds().getValue();
				updateTimerLabel(workDuration);
				cl.show(panel, "display");
			}
			break;
		// "Start" button functions
		case "play":
			if (button.getText().equals("Start")) {
				Components.getBtn().setEnabled(false);
				button.setText("Stop");
				
				if (workRun == true) workTimer.start();
				else if (shortRun == true) shortBreakTimer.start();
				else if (longRun == true) longBreakTimer.start();
				else workTimer.start();
				
			} else if (button.getText().equals("Stop")) {
				Components.getBtn().setEnabled(true);
				button.setText("Start");
				
				if (workRun == true) workTimer.stop();
				else if (shortRun == true) shortBreakTimer.stop();
				else if (longRun == true) longBreakTimer.stop();
			}
			break;
			
		case "reset":
			slider1.setValue(25);
			slider2.setValue(5);
			slider3.setValue(15);
			slider4.setValue(3);
			break;
		}
	}

	private void workDurationTimer() {
		workTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (workDuration >= 0 && seconds > 0) {
					seconds--;
					updateTimerLabel(workDuration);
				} else if (workDuration > 0 && seconds == 0) {
					workDuration--;
					seconds = 59;
					updateTimerLabel(workDuration);
				} else if (workDuration == 0 && seconds == 0) {
					JOptionPane.showMessageDialog(null, "Time's UP! Time to RELAX!", "Timeout", JOptionPane.INFORMATION_MESSAGE);
					workTimer.stop();
					workDuration = Components.getWorkDuration().getValue();
					workRun = false;
					
					if (currentRounds < rounds-1) {
						shortRun = true;
						shortBreakTimer.start();
					} else {
						longRun = true;
						longBreakTimer.start();
					}
				}
			}
		});
	}
	
	private void shortBreakTimer() {
		workRun = true;
		shortBreakTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (shortBreak >= 0 && seconds > 0) {
					seconds--;
					updateTimerLabel(shortBreak);
				} else if (shortBreak > 0 && seconds == 0) {
					shortBreak--;
					seconds = 59;
					updateTimerLabel(shortBreak);
				} else if (shortBreak == 0 && seconds == 0) {
					JOptionPane.showMessageDialog(null, "Time's UP! Time to FOCUS!", "Workout", JOptionPane.INFORMATION_MESSAGE);
					shortBreakTimer.stop();
					currentRounds ++;
					shortBreak = Components.getShortBreak().getValue();
					shortRun = false;
					workRun = true;;
					System.out.println("Current Round: " + currentRounds + " Rounds: " + rounds);
					workTimer.start();
				}
			}
		});
	}
	
	private void longBreakTimer() {
		longBreakTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (longBreak >= 0 && seconds > 0) {
					seconds--;
					updateTimerLabel(longBreak);
				} else if (longBreak > 0 && seconds == 0) {
					longBreak--;
					seconds = 59;
					updateTimerLabel(longBreak);
				} else if (longBreak == 0 && seconds == 0) {
					JOptionPane.showMessageDialog(null, "Congratulations. You have completed a whole set of Pomodoro!", "Workout", JOptionPane.INFORMATION_MESSAGE);
					longRun = false;
					longBreakTimer.stop();
					resetValues();
					button.setText("Start");
				}
			}
		});
	}
	
	private void updateTimerLabel(int breakTime) {
		Components.getTimerLabel().setText(String.format("%02d", breakTime) + " : " + String.format("%02d", seconds));
	}
	
	private void resetValues() {
		workDuration = Components.getWorkDuration().getValue();
		shortBreak = Components.getShortBreak().getValue();
		longBreak = Components.getLongBreak().getValue();
		rounds = Components.getRounds().getValue();
		currentRounds = 0;
		updateTimerLabel(workDuration);
	}
}
