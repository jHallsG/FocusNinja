package com.focus.components;

import javax.swing.JSlider;

public class Components {
	static JSlider workDuration = new JSlider(1, 60, 25);
	static JSlider shortBreak = new JSlider(1, 15, 5);
	static JSlider longBreak = new JSlider(1, 30, 15);
	static JSlider rounds = new JSlider(1, 10, 3);

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
