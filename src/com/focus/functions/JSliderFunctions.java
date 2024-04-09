package com.focus.functions;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderFunctions implements ChangeListener{
	private JLabel value;
	private JSlider slider;
	
	public JSliderFunctions(JLabel value, JSlider slider) {
		this.slider = slider;
		this.value = value;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		value.setText(String.format("%02d : 00", slider.getValue()));
	}

}
