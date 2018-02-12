package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class FormattedButton extends JButton {
	
	public FormattedButton(String text, String name) {
		this.setText(text);
		this.setName(name);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
}
