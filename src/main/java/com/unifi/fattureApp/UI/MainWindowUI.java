package com.unifi.fattureApp.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import javafx.scene.paint.Color;

import java.awt.Dimension;
import java.awt.BorderLayout;

public class MainWindowUI {

	private JFrame fattureApp_Frame;

	private Dimension windowDimension = new Dimension(450, 700);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowUI window = new MainWindowUI();
					window.fattureApp_Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowUI() {
		initialize();
	}

	private void initialize() {
		fattureApp_Frame = new JFrame();
		fattureApp_Frame.setTitle("Fatture App");
		fattureApp_Frame.setSize(windowDimension);
		fattureApp_Frame.setResizable(false);
		fattureApp_Frame.getContentPane().setForeground(java.awt.Color.LIGHT_GRAY);
		fattureApp_Frame.getContentPane().setSize(windowDimension);
		fattureApp_Frame.getContentPane().setLayout(null);
		
		JPanel outer_Panel = new JPanel();
		outer_Panel.setBackground(new java.awt.Color(245, 245, 245));
		outer_Panel.setBounds(0, 0, fattureApp_Frame.getContentPane().getWidth(), fattureApp_Frame.getContentPane().getHeight());
		fattureApp_Frame.getContentPane().add(outer_Panel);
		outer_Panel.setLayout(null);
		
		int insets = 8;
		int topPanelHeight = 100;
		
		JPanel myCompany_Panel = new JPanel();
		myCompany_Panel.setBounds(insets, insets, outer_Panel.getWidth() - insets * 2, topPanelHeight);
		outer_Panel.add(myCompany_Panel);
		myCompany_Panel.setBackground(new java.awt.Color(232, 230, 230));
		myCompany_Panel.setLayout(null);
		
		JPanel invoice_Panel = new JPanel();
		int invoicePanelY = (insets * 2) + myCompany_Panel.getHeight();
		int invoicePanelWidth = outer_Panel.getWidth() - (insets * 2);
		int invoicePanelHeight = outer_Panel.getHeight() - (myCompany_Panel.getHeight() + (insets * 6));
		invoice_Panel.setBounds(insets, invoicePanelY, invoicePanelWidth, invoicePanelHeight);
		outer_Panel.add(invoice_Panel);
		invoice_Panel.setBackground(new java.awt.Color(232, 230, 230));
		invoice_Panel.setLayout(null);

		
	}
}
