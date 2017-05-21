package com.unifi.fattureApp.UI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindowUI {

	private JFrame fattureApp_Frame;
	private JLayeredPane outer_Panel;
	private JPanel myCompany_Panel;

	private int buttonPadding = 8;
	private int buttonHeight = 30;
	private int buttonWidth = 80;
	
	private Color outerColor = new java.awt.Color(232, 246, 250);
	private Color layer1Color = new java.awt.Color(226, 244, 252);
	private Color layer2Color = new java.awt.Color(216,245,255);

	private Dimension windowDimension = new Dimension(500, 700);
	private JTextField companyName_TF;
	private JTextField compantVat_TF;
	private JTextField companyAddress_TF;
	private JTextField companyCity_TF;
	private JTextField companyProvince_TF;
	private JTextField companyZip_TF;
	private JTextField companyCountry_TF;
	private JTextField companyPhone_TF;
	private JTextField companyEmail_TF;

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
		outer_Panel.setBackground(outerColor);
		outer_Panel.setBounds(0, 0, fattureApp_Frame.getContentPane().getWidth(), fattureApp_Frame.getContentPane().getHeight() );
		fattureApp_Frame.getContentPane().add(outer_Panel);
		outer_Panel.setLayout(null);

		int outerInsets = 8;
		int topPanelHeight = 80;
		
		myCompany_Panel = new JPanel();
		myCompany_Panel.setName("CompanyPanel");
		myCompany_Panel.setBorder(BorderFactory.createLineBorder(Color.white));
		myCompany_Panel.setBounds(outerInsets, outerInsets, outer_Panel.getWidth() - outerInsets * 2, topPanelHeight);
		outer_Panel.add(myCompany_Panel);
		myCompany_Panel.setBackground(layer1Color);
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
		addMyCompany_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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
		invoice_Panel.setBorder(BorderFactory.createLineBorder(Color.white));
		int invoicePanelY = (outerInsets * 2) + myCompany_Panel.getHeight();
		int invoicePanelWidth = outer_Panel.getWidth() - (outerInsets * 2);
		int invoicePanelHeight = outer_Panel.getHeight() - (invoicePanelY + outerInsets);
		invoice_Panel.setBounds(outerInsets, invoicePanelY, invoicePanelWidth, invoicePanelHeight);
		outer_Panel.add(invoice_Panel);
		invoice_Panel.setBackground(layer1Color);
		invoice_Panel.setLayout(null);
	
		
		outer_Panel.setLayer(invoice_Panel, 1);
		outer_Panel.setLayer(myCompany_Panel, 1);
		
		fattureApp_Frame.pack();
	}

	private void showAddCompanyPanel() {
		// SHOW PANEL TO ADD CUSTOMER
		JPanel addCompany_Panel = new JPanel();
		addCompany_Panel.setName("AddCompanyPanel");
		addCompany_Panel.setBackground(layer2Color);
		addCompany_Panel.setBorder(BorderFactory.createLineBorder(Color.white,3));
		int insets = 22;
		int width = outer_Panel.getWidth() - insets - insets;
		int height = outer_Panel.getHeight() - insets - insets;
		addCompany_Panel.setBounds(insets, insets, width, height);
		outer_Panel.add(addCompany_Panel);
		addCompany_Panel.setLayout(null);
		outer_Panel.setLayer(addCompany_Panel, 2);

		// ADD COMPONENTS INSIDE PANEL
		int addPanelY = addCompany_Panel.getY();
		insets = 8;
		int insetsBtwField = 23;
		
		JLabel addCompanyTitle_Label = new JLabel("Company");
		addCompanyTitle_Label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		width = (int)addCompanyTitle_Label.getPreferredSize().getWidth();
		height = (int)addCompanyTitle_Label.getPreferredSize().getHeight();
		addCompanyTitle_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,addPanelY - 10 , width, height);
		addCompany_Panel.add(addCompanyTitle_Label);
		
		
		JLabel companyName_Label = new JLabel("Company Name:");
		width = (int)companyName_Label.getPreferredSize().getWidth();
		height = (int)companyName_Label.getPreferredSize().getHeight();
		companyName_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,addCompanyTitle_Label.getY() + addCompanyTitle_Label.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyName_Label);
		
		companyName_TF = new JTextField();
		width = 300;
		companyName_TF.setHorizontalAlignment(JTextField.CENTER);
		companyName_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyName_Label.getY() + companyName_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyName_TF);
		
		JLabel companyVat_Label = new JLabel("VAT Number:");
		width = (int)companyVat_Label.getPreferredSize().getWidth();
		height = (int)companyVat_Label.getPreferredSize().getHeight();
		companyVat_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,companyName_TF.getY() + companyName_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyVat_Label);
		
		compantVat_TF = new JTextField();
		width = 200;
		compantVat_TF.setHorizontalAlignment(JTextField.CENTER);
		compantVat_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyVat_Label.getY() + companyVat_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(compantVat_TF);
		
		JLabel companyAddress_Label = new JLabel("Address:");
		width = (int)companyAddress_Label.getPreferredSize().getWidth();
		height = (int)companyAddress_Label.getPreferredSize().getHeight();
		companyAddress_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,compantVat_TF.getY() + compantVat_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyAddress_Label);
		
		companyAddress_TF = new JTextField();
		width = 300;
		companyAddress_TF.setHorizontalAlignment(JTextField.CENTER);
		companyAddress_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyAddress_Label.getY() + companyAddress_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyAddress_TF);
		
		int insetsMiddle = 80;
		
		JLabel companyCity_Label = new JLabel("City:");
		width = (int)companyCity_Label.getPreferredSize().getWidth();
		height = (int)companyCity_Label.getPreferredSize().getHeight();
		companyCity_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) - insetsMiddle ,companyAddress_TF.getY() + companyAddress_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyCity_Label);
		
		companyCity_TF = new JTextField();
		width = 130;
		companyCity_TF.setHorizontalAlignment(JTextField.CENTER);
		companyCity_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) - insetsMiddle, companyCity_Label.getY() + companyCity_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyCity_TF);
		
		JLabel companyProvince_Label = new JLabel("Province:");
		width = (int)companyProvince_Label.getPreferredSize().getWidth();
		height = (int)companyProvince_Label.getPreferredSize().getHeight();
		companyProvince_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) + insetsMiddle ,companyAddress_TF.getY() + companyAddress_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyProvince_Label);
		
		companyProvince_TF = new JTextField();
		width = 130;
		companyProvince_TF.setHorizontalAlignment(JTextField.CENTER);
		companyProvince_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) + insetsMiddle, companyProvince_Label.getY() + companyProvince_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyProvince_TF);
		
		JLabel companyZip_Label = new JLabel("ZIP Code:");
		width = (int)companyZip_Label.getPreferredSize().getWidth();
		height = (int)companyZip_Label.getPreferredSize().getHeight();
		companyZip_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) - insetsMiddle ,companyProvince_TF.getY() + companyProvince_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyZip_Label);
		
		companyZip_TF = new JTextField();
		width = 80;
		companyZip_TF.setHorizontalAlignment(JTextField.CENTER);
		companyZip_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) - insetsMiddle, companyZip_Label.getY() + companyZip_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyZip_TF);
		
		JLabel companyCountry_Label = new JLabel("Country:");
		width = (int)companyCountry_Label.getPreferredSize().getWidth();
		height = (int)companyCountry_Label.getPreferredSize().getHeight();
		companyCountry_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) + insetsMiddle ,companyProvince_TF.getY() + companyProvince_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyCountry_Label);
		
		companyCountry_TF = new JTextField();
		width = 100;
		companyCountry_TF.setHorizontalAlignment(JTextField.CENTER);
		companyCountry_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) + insetsMiddle, companyCountry_Label.getY() + companyCountry_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyCountry_TF);
		
		
		JLabel companyPhone_Label = new JLabel("Phone:");
		width = (int)companyPhone_Label.getPreferredSize().getWidth();
		height = (int)companyPhone_Label.getPreferredSize().getHeight();
		companyPhone_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2),companyCountry_TF.getY() + companyCountry_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyPhone_Label);
		
		companyPhone_TF = new JTextField();
		width = 150;
		companyPhone_TF.setHorizontalAlignment(JTextField.CENTER);
		companyPhone_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyPhone_Label.getY() + companyPhone_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyPhone_TF);
		
		JLabel companyEmail_Label = new JLabel("Email:");
		width = (int)companyEmail_Label.getPreferredSize().getWidth();
		height = (int)companyEmail_Label.getPreferredSize().getHeight();
		companyEmail_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2),companyPhone_TF.getY() + companyPhone_TF.getHeight() + insetsBtwField , width, height);
		addCompany_Panel.add(companyEmail_Label);
		
		companyEmail_TF = new JTextField();
		width = 190;
		companyEmail_TF.setHorizontalAlignment(JTextField.CENTER);
		companyEmail_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyEmail_Label.getY() + companyEmail_Label.getHeight() + insets, width, 28);
		addCompany_Panel.add(companyEmail_TF);		
		
		
		
		JButton cancel_Button = new JButton();
		cancel_Button.setName("CancelButton");
		cancel_Button.setText("Cancel");
		cancel_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		cancel_Button.setBounds((addCompany_Panel.getWidth() / 2) - buttonWidth - 24,
				addCompany_Panel.getHeight() - 20 - addCompany_Panel.getY(), buttonWidth, buttonHeight);
		addCompany_Panel.add(cancel_Button);
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCompany_Panel.setVisible(false);
				outer_Panel.remove(addCompany_Panel);
			}
		});

		JButton save_Button = new JButton();
		save_Button.setName("SaveButton");
		save_Button.setText("Save");
		save_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		save_Button.setBounds((addCompany_Panel.getWidth() / 2) + 24,
				addCompany_Panel.getHeight() - 20 - addCompany_Panel.getY(), buttonWidth, buttonHeight);
		addCompany_Panel.add(save_Button);
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// save company
				
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
