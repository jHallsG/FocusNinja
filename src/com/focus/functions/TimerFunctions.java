package com.focus.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.focus.components.Components;

public class TimerFunctions extends Timer {

	private static int seconds = 0;
	private static int workDuration = 25;

	public TimerFunctions(int initialWorkDuration, ActionListener listener) {
		super(1000, listener);
		this.workDuration = initialWorkDuration;
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (workDuration >= 0 && seconds > 0) {
					seconds--;
					Components.getTimerLabel()
							.setText(String.format("%02d", workDuration) + " : " + String.format("%02d", seconds));
				} else if (workDuration > 0 && seconds == 0) {
					workDuration--;
					seconds = 59;
					Components.getTimerLabel()
							.setText(String.format("%02d", workDuration) + " : " + String.format("%02d", seconds));
				} else if (workDuration == 0 && seconds == 0) {
					TimerFunctions.this.stop();
				}
			}
		});
	}

	public void startTimer() {
		start();
	}

	public void stopTimer() {
		stop();
	}
}
