package com.focus.functions;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.focus.components.Components;
import com.focus.panels.DisplayPanel;

public class ButtonFunctions implements ActionListener, ChangeListener, PropertyChangeListener {
	
	private JButton button;
	private String type;
	private boolean settingsActive = false;
	private CardLayout cl;
	private JPanel panel;
	private JLabel value, label;
	private JSlider slider , slider1, slider2, slider3, slider4;
	
	public ButtonFunctions(JLabel label) {
		this.label = label;
	}
	
	public ButtonFunctions(JLabel value, JSlider slider) {
		this.slider = slider;
		this.value = value;
	}
	
	public ButtonFunctions(JButton button, String type) {
		this.button = button;
		this.type = type;
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
		
		switch(type) {
		case "switch":
			if (!settingsActive) {
				settingsActive = true;
				cl.show(panel,"settings");
			} else {
				settingsActive = false;
				Components.getTimerLabel().setText(String.format("%02d",Components.getWorkDuration().getValue()) + " : " + String.format("%02d", 0));
				cl.show(panel, "display");
			}
			break;
		case "play":
			int workDuration = Components.getWorkDuration().getValue();
			System.out.println("Work Duration: " + workDuration);
			if (button.getText().equals("Start")) {
				button.setText("Stop");
//				timer.start();
			} else if (button.getText().equals("Stop")){
				button.setText("Start");
//				timer.stop();
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

	@Override
	public void stateChanged(ChangeEvent e) {
		value.setText(String.format("%02d:00", slider.getValue()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName());
		
		
//		label.setText(String.format("%02d:00", Components.getWorkDuration().getValue()));
	}
}
