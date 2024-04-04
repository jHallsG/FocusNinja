package com.focus.functions;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

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
}
