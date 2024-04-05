package com.focus.functions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.focus.components.Components;

public class Functions {
	
	private boolean settingsActive = false;
	
	public void switchView(JButton btn, CardLayout cl, JPanel panel) {
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (settingsActive == false) {
					settingsActive = true;
					cl.show(panel,"settings");
				} else {
					settingsActive = false;
					cl.show(panel, "display");
				}
			}
		});
	}
	
	public void getSliderValue(JSlider slider, JLabel value) {
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				value.setText(String.format("%02d:00", slider.getValue()));
			}
		});
	}
	
	public Timer setTimer(int seconds, JLabel timerLabel, int workDuration) {
		
		/*
		 *  directly using any of the arg variables results in an enclosing scope must be final error.
		 *  making the variables final would prevent us from decrementing their values
		 *  one way is to wrap them around an array, that way the array would be final but the values inside can be decremented
		 */
		final int[] secondsArray = {seconds};
		final int[] workDurationArray = {workDuration};
		final Timer[] countDownTimer = {null};
		
		countDownTimer[0] = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (workDurationArray[0] >= 0 && secondsArray[0] > 0) {
					secondsArray[0]--;
					timerLabel.setText(String.format("%02d : %02d", workDurationArray[0], secondsArray[0]));
					
				} else if (workDurationArray[0] > 0 && secondsArray[0] == 0) {
					workDurationArray[0]--;
					secondsArray[0] = 10;
					timerLabel.setText(String.format("%02d : %02d", workDurationArray[0], secondsArray[0]));
					
				} else if (workDurationArray[0] == 0 && secondsArray[0] == 0) {
					countDownTimer[0].stop();
				}
			}
		});
		
		return countDownTimer[0];
	}
	
	public void playFunction(JButton play, JLabel label) {
		
		int workDuration = Components.getWorkDuration().getValue();
		System.out.println("Work Duration: " + workDuration);
		Timer timer = setTimer(5, label, workDuration);
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (play.getText().equals("Start")) {
					play.setText("Stop");
					timer.start();
				} else if (play.getText().equals("Stop")){
					play.setText("Start");
					timer.stop();
				}
			}
		});
	}
	
	public ActionListener timer() {
		return null;
	}
}
