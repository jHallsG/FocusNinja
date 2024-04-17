package com.focus.functions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import com.focus.components.Components;

/*
 * This class handles all button functionalities, ie, Start button, Reset Button, and ChangeView button.
 */

public class ButtonFunctions implements ActionListener {

	private JButton button;
	private String type, ongoingTimer = " ";
	
	/*
	 * Boolean checks to see if settings is currently in display. Also checks if timers for work, short break and long break are running
	 */
	private boolean settingsActive = false, workRun = false, shortRun = false, longRun = false;
	/*
	 * The card layout used for swapping between display page and settings page
	 */
	private CardLayout cl;
	private JPanel panel;
	/*
	 * Represents the four JSliders used, the work Duration slider, short break slider, long break slider, and the number of rounds slider
	 */
	private JSlider slider1, slider2, slider3, slider4;
	/*
	 * Seconds set at 0. If a Work Duration is set for 25 minutes, the timer would automatically decrement and start at 24:59 instead of 25:59
	 * currentRounds checks against the rounds slider
	 */
	private static int seconds = 0, rounds = 3, currentRounds = 0, maxBarValue;
	private Timer workTimer, shortBreakTimer, longBreakTimer;
	/*
	 * Timer values set at default, 5 mins for short break, 25 minutes for work, and 15 mins for long break
	 */
	private static int shortBreak = 5, workDuration = 25, longBreak = 15;
	/*
	 * The class used for 'kidnapping' the mouse
	 */
	private GlobalMouseListenerImpl globalMouseListener;
	

	public ButtonFunctions(JButton button, String type) {
		this.button = button;
		this.type = type;
		workDurationTimer();
		shortBreakTimer();
		longBreakTimer();
		globalMouseListener = new GlobalMouseListenerImpl();
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
				maxBarValue = workDuration;
				shortBreak = Components.getShortBreak().getValue();
				longBreak = Components.getLongBreak().getValue();
				rounds = Components.getRounds().getValue();
				Components.getBar().setMinimum(0);
				Components.getBar().setMaximum(workDuration);
				updateTimerLabel(workDuration);
				cl.show(panel, "display");
			}
			break;
		// "Start" button functions
		case "play":
			if (button.getText().equals("Start")) {
				Components.getBtn().setEnabled(false);
				button.setText("Stop");
				
				if (!workRun && ongoingTimer.equals(" ")) {
					workRun = true;
					ongoingTimer = "work";
					workTimer.start();
				} else if (!workRun && ongoingTimer.equals("work")) {
					workRun = true;
					workTimer.start();
				} else if (!shortRun && ongoingTimer.equals("shortBreak")) {
					shortRun = true;
					shortBreakTimer.start();
				} else if (!longRun && ongoingTimer.equals("longBreak")) {
					longRun = true;
					longBreakTimer.start();
				}
				

			} else if (button.getText().equals("Stop")) {
				Components.getBtn().setEnabled(true);
				button.setText("Start");
				
				if (workRun) {
					workRun = false;
					workTimer.stop();
				} else if (shortRun) {
					shortRun = false;
					shortBreakTimer.stop();
				} else if (longRun) {
					longRun = false;
					longBreakTimer.stop();
				}
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
				Components.getBar().setValue(maxBarValue - workDuration);
				
				if (workDuration >= 0 && seconds > 0) {
					seconds--;
					updateTimerLabel(workDuration);
				} else if (workDuration > 0 && seconds == 0) {
					workDuration--;
					seconds = 5;
					updateTimerLabel(workDuration);
				} else if (workDuration == 0 && seconds == 0) {
					playSound();
					workRun = false;
					workDuration = Components.getWorkDuration().getValue();
					workTimer.stop();
					
					JOptionPane.showMessageDialog(null, "Time's UP! Time to RELAX!", "Timeout",
							JOptionPane.INFORMATION_MESSAGE);
					globalMouseListener.runGlobalMouseListener();
					
					if (currentRounds < rounds - 1) {
						shortRun = true;
						ongoingTimer = "shortBreak";
						shortBreakTimer.start();
						
					} else {
						longRun = true;
						ongoingTimer = "longBreak";
						longBreakTimer.start();
					}
				}
			}
		});
	}

	private void shortBreakTimer() {
		shortBreakTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shortBreak >= 0 && seconds > 0) {
					seconds--;
					updateTimerLabel(shortBreak);
				} else if (shortBreak > 0 && seconds == 0) {
					shortBreak--;
					seconds = 5;
					updateTimerLabel(shortBreak);
				} else if (shortBreak == 0 && seconds == 0) {
					playSound();
					shortRun = false;
					shortBreak = Components.getShortBreak().getValue();
					shortBreakTimer.stop();
					
					JOptionPane.showMessageDialog(null, "Time's UP! Time to FOCUS!", "Workout",
							JOptionPane.INFORMATION_MESSAGE);
					
					currentRounds++;
					workRun = true;
					ongoingTimer = "work";
				
					globalMouseListener.disableGlobalMouseListener();
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
					seconds = 5;
					updateTimerLabel(longBreak);
				} else if (longBreak == 0 && seconds == 0) {
					playSound();
					JOptionPane.showMessageDialog(null, "Congratulations. You have completed a whole set of Pomodoro!",
							"Workout", JOptionPane.INFORMATION_MESSAGE);
					longRun = false;
					longBreakTimer.stop();
					globalMouseListener.disableGlobalMouseListener();
					resetValues();
					button.setText("Start");
				}
			}
		});
	}

	/*
	 * Updates the timer label from the display page so it coincides with the ongoing timer.
	 */
	private void updateTimerLabel(int breakTime) {
		Components.getTimerLabel().setText(String.format("%02d", breakTime) + " : " + String.format("%02d", seconds));
	}

	private void resetValues() {
		workDuration = Components.getWorkDuration().getValue();
		shortBreak = Components.getShortBreak().getValue();
		longBreak = Components.getLongBreak().getValue();
		rounds = Components.getRounds().getValue();
		updateTimerLabel(workDuration);
		ongoingTimer = " ";
		currentRounds = 0;
	}
	
	private void playSound() {
		String filePath = "Extra/soundEffects.wav";
	    try {
	        // Load the audio file as an AudioInputStream
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

	        // Get a clip resource
	        Clip clip = AudioSystem.getClip();

	        // Open the clip and start playing
	        clip.open(audioInputStream);
	        clip.start();

	        // Close the clip when it finishes playing
	        clip.addLineListener(event -> {
	            if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
	                clip.close();
	            }
	        });

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
