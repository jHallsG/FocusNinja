package com.focus.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.focus.functions.ButtonFunctions;

public class MainBody {
	
	public MainBody(){
		// Initialize the values from settings panel first before displaying them on display panel, otherwise it will result to null error.
		SettingsPanel.settings();
	}

	private static CardLayout cl = new CardLayout();
	private static JPanel body = new JPanel();
	
	public JPanel head() {
		
		JPanel head = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 15));
		head.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));

		ImageIcon btn = new ImageIcon("Images/shuriken.png");

		JButton changeView = new JButton();
		changeView.setIcon(btn);
		changeView.setPreferredSize(new Dimension(30, 30));
		changeView.addActionListener(new ButtonFunctions(cl, body(), "switch"));
		

		JLabel focus = new JLabel("Focus Ninja");
		focus.setFont(new Font("Consolas", Font.BOLD, 20));

		head.add(Box.createRigidArea(new Dimension(30, 30)));
		head.add(changeView);
		head.add(Box.createRigidArea(new Dimension(50, 30)));
		head.add(focus);

		return head;
	}

	public JPanel body() {
		
		body.setLayout(cl);
		
		body.add(DisplayPanel.display(), "display");
		body.add(SettingsPanel.settings(), "settings");
		
		return body;
	}
	

	public JPanel base() {
		
		JPanel base = new JPanel();
		base.setBackground(Color.GRAY);
		return base;
	}
}