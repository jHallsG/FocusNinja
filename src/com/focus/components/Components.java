package com.focus.components;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Components {
	private static JSlider workDuration, shortBreak, longBreak, rounds;
	private static JLabel timerLabel;

	public static JLabel getTimerLabel() {
		return timerLabel;
	}

	public static void setTimerLabel(JLabel timerLabel) {
		Components.timerLabel = timerLabel;
	}

	public static JSlider getWorkDuration() {
		return workDuration;
	}

	public static JSlider getShortBreak() {
		return shortBreak;
	}

	public static JSlider getLongBreak() {
		return longBreak;
	}

	public static JSlider getRounds() {
		return rounds;
	}

	public static void setWorkDuration(JSlider workDuration) {
		Components.workDuration = workDuration;
	}

	public static void setShortBreak(JSlider shortBreak) {
		Components.shortBreak = shortBreak;
	}

	public static void setLongBreak(JSlider longBreak) {
		Components.longBreak = longBreak;
	}

	public static void setRounds(JSlider rounds) {
		Components.rounds = rounds;
	}
}
