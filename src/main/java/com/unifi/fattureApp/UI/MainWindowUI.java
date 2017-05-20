package com.unifi.fattureApp.UI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowUI {

	private JFrame fattureApp_Frame;
	private JLayeredPane outer_Panel;
	private JPanel myCompany_Panel;

	private Dimension windowDimension = new Dimension(500, 700);

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
		fattureApp_Frame.setTitle("Fatture App");
		fattureApp_Frame.setPreferredSize(windowDimension);
		fattureApp_Frame.setResizable(false);
		fattureApp_Frame.pack();
		fattureApp_Frame.setBackground(java.awt.Color.LIGHT_GRAY);
		fattureApp_Frame.getContentPane().setLayout(null);
		fattureApp_Frame.setLocationRelativeTo(null);
		
		
		outer_Panel = new JLayeredPane();
		outer_Panel.setName("OuterPanel");
		outer_Panel.setBackground(new java.awt.Color(245, 245, 245));
		outer_Panel.setBounds(0, 0, fattureApp_Frame.getContentPane().getWidth(), fattureApp_Frame.getContentPane().getHeight() );
		fattureApp_Frame.getContentPane().add(outer_Panel);
		outer_Panel.setLayout(null);

		int outerInsets = 8;
		int topPanelHeight = 80;
		
		myCompany_Panel = new JPanel();
		myCompany_Panel.setName("CompanyPanel");
		
		myCompany_Panel.setBounds(outerInsets, outerInsets, outer_Panel.getWidth() - outerInsets * 2, topPanelHeight);
		outer_Panel.add(myCompany_Panel);
		myCompany_Panel.setBackground(new java.awt.Color(232, 230, 230));
		myCompany_Panel.setLayout(null);
		
		int innerInsets = 12;
		String myCompanyName_String = "My Company";
		JLabel myCompany_Label = new JLabel(myCompanyName_String);
		myCompany_Label.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		double width = myCompany_Label.getPreferredSize().getWidth();
		double height = myCompany_Label.getPreferredSize().getHeight();
		myCompany_Label.setBounds(innerInsets, innerInsets, (int) width, (int) height);
		myCompany_Panel.add(myCompany_Label);
		
		JLabel myCompanyInfo_Label = new JLabel("info");
		width = myCompany_Label.getPreferredSize().getWidth();
		height = myCompany_Label.getPreferredSize().getHeight();
		myCompany_Label.setBounds(innerInsets, innerInsets, (int) width, (int) height);
		myCompanyInfo_Label.setBounds(innerInsets, myCompany_Label.getY() +  innerInsets + myCompany_Label.getHeight(), (int)width, (int)height);
		myCompany_Panel.add(myCompanyInfo_Label);
		
		//BUTTON TO ADD NEW COMPANY
		JButton addMyCompany_Button = new JButton("Add");
		addMyCompany_Button.setName("AddCompanyButton");
		int buttonHeight = 32;
		int buttonWidth = (int)(addMyCompany_Button.getPreferredSize().getWidth());
		addMyCompany_Button.setBounds(myCompany_Panel.getWidth() - innerInsets - buttonWidth, (myCompany_Panel.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
		myCompany_Panel.add(addMyCompany_Button);
		outer_Panel.setLayer(addMyCompany_Button, 1);
		addMyCompany_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAddCompanyPanel();
			}
		});
		
		JPanel invoice_Panel = new JPanel();
		invoice_Panel.setName("InvoicePanel");
		int invoicePanelY = (outerInsets * 2) + myCompany_Panel.getHeight();
		int invoicePanelWidth = outer_Panel.getWidth() - (outerInsets * 2);
		int invoicePanelHeight = outer_Panel.getHeight() - (invoicePanelY + outerInsets);
		invoice_Panel.setBounds(outerInsets, invoicePanelY, invoicePanelWidth, invoicePanelHeight);
		outer_Panel.add(invoice_Panel);
		invoice_Panel.setBackground(new java.awt.Color(232, 230, 230));
		invoice_Panel.setLayout(null);
	
		
		outer_Panel.setLayer(invoice_Panel, 1);
		outer_Panel.setLayer(myCompany_Panel, 1);
		
		fattureApp_Frame.pack();
	}
	
	private void showAddCompanyPanel() {
		//SHOW PANEL TO ADD CUSTOMER
		JPanel addCompany_Panel = new JPanel();
		addCompany_Panel.setName("AddCompanyPanel");
		addCompany_Panel.setBackground(new java.awt.Color(220,220,220));
		addCompany_Panel.setBorder(BorderFactory.createRaisedBevelBorder());
		int insets = 22;
		int width = outer_Panel.getWidth() - insets - insets;
		int height = outer_Panel.getHeight() - insets - insets;
		addCompany_Panel.setBounds(insets,insets,width,height);
		outer_Panel.add(addCompany_Panel);
		addCompany_Panel.setLayout(null);
		outer_Panel.setLayer(addCompany_Panel, 2);
		
		//ADD COMPONENTS INSIDE PANEL
		JButton cancel_Button = new JButton();
		cancel_Button.setName("CancelButton");
		cancel_Button.setText("Cancel");
		cancel_Button.setBorder(BorderFactory.createLineBorder(Color.))));
		int buttonHeight = 32;
		int buttonWidth = (int)(cancel_Button.getPreferredSize().getWidth());
		cancel_Button.setBounds((addCompany_Panel.getWidth() / 2) - buttonWidth - 50, addCompany_Panel.getHeight() - 20 - addCompany_Panel.getY(), buttonWidth, buttonHeight);
		addCompany_Panel.add(cancel_Button);
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCompany_Panel.setVisible(false);
				outer_Panel.remove(addCompany_Panel);
			}
		});
		
	}

	public JFrame getMainFrame() {
		// TODO Auto-generated method stub
		return fattureApp_Frame;
	}

}
