package com.unifi.fattureApp.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Frame;

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
					window.fattureApp_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		fattureApp_Frame.setTitle("FattureApp");
		fattureApp_Frame.setPreferredSize(windowDimension);
		fattureApp_Frame.setResizable(false);
		fattureApp_Frame.pack();
		fattureApp_Frame.setForeground(java.awt.Color.LIGHT_GRAY);
		fattureApp_Frame.setLayout(null);
		fattureApp_Frame.setLocationRelativeTo(null);
		
		
		JPanel outer_Panel = new JPanel();
		outer_Panel.setBackground(new java.awt.Color(245, 245, 245));
		outer_Panel.setBounds(0, 0, fattureApp_Frame.getContentPane().getWidth(), fattureApp_Frame.getContentPane().getHeight() );
		outer_Panel.setPreferredSize(windowDimension);
		fattureApp_Frame.add(outer_Panel);
		outer_Panel.setLayout(null);

		int insets = 8;
		int topPanelHeight = 100;
		
		JPanel myCompany_Panel = new JPanel();
		myCompany_Panel.setBounds(insets, insets, outer_Panel.getWidth() - insets * 2, topPanelHeight);
		outer_Panel.add(myCompany_Panel);
		myCompany_Panel.setBackground(new java.awt.Color(232, 230, 230));
		myCompany_Panel.setLayout(null);
		
		JLabel myCompany_Label = new JLabel("My Company");
		myCompany_Label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		myCompany_Label.setBounds(insets, insets, 100, 16);
		myCompany_Panel.add(myCompany_Label);
		
		JLabel myCompanyInfo_Label = new JLabel("info");
		myCompanyInfo_Label.setBounds(insets + myCompany_Label.getWidth(), insets, 300, 16);
		myCompany_Panel.add(myCompanyInfo_Label);
		
		JPanel invoice_Panel = new JPanel();
		int invoicePanelY = (insets * 2) + myCompany_Panel.getHeight();
		int invoicePanelWidth = outer_Panel.getWidth() - (insets * 2);
//		int invoicePanelHeight = outer_Panel.getHeight() - (myCompany_Panel.getHeight() + (insets * 6)); // dobrebbe essere insets * 3
		int invoicePanelHeight = outer_Panel.getHeight() - (invoicePanelY + insets);
		invoice_Panel.setBounds(insets, invoicePanelY, invoicePanelWidth, invoicePanelHeight);
		outer_Panel.add(invoice_Panel);
		invoice_Panel.setBackground(new java.awt.Color(232, 230, 230));
		invoice_Panel.setLayout(null);
		
		fattureApp_Frame.pack();
	}

//	public JFrame getFrame() {
//		// TODO Auto-generated method stub
//		return fattureApp_Frame;
//	}
	
	
}
