package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientPanel extends JPanel{
	
	private JTextField clientName_TF;
	private JTextField clientSurname_TF;
	private JTextField clientFiscalCode_TF;
	private JTextField clientCity_TF;
	private JTextField clientPhone_TF;
	private JTextField clientEmail_TF;
	
	private Color layerColor = new java.awt.Color(216,245,255);

	public ClientPanel(JLayeredPane outer_Panel,int buttonWidth,int buttonHeight){
	JPanel addClient_Panel=this;
	
	addClient_Panel.setName("AddClientPanel");
	addClient_Panel.setBackground(layerColor);
	addClient_Panel.setBorder(BorderFactory.createLineBorder(Color.white,3));
	int insets = 22;
	int width = outer_Panel.getWidth() - insets - insets;
	int height = outer_Panel.getHeight() - insets - insets;
	addClient_Panel.setBounds(insets, insets, width, height);
	outer_Panel.add(addClient_Panel);
	addClient_Panel.setLayout(null);
	outer_Panel.setLayer(addClient_Panel, 2);

	// ADD COMPONENTS INSIDE PANEL
	int addPanelY = addClient_Panel.getY();
	insets = 8;
	int insetsBtwField = 23;
	
	JLabel addClientTitle_Label = new JLabel("Client");
	addClientTitle_Label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
	width = (int)addClientTitle_Label.getPreferredSize().getWidth();
	height = (int)addClientTitle_Label.getPreferredSize().getHeight();
	addClientTitle_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) ,addPanelY - 10 , width, height);
	addClient_Panel.add(addClientTitle_Label);
	
	
	JLabel clientName_Label = new JLabel("Client Name:");
	width = (int)clientName_Label.getPreferredSize().getWidth();
	height = (int)clientName_Label.getPreferredSize().getHeight();
	clientName_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) ,addClientTitle_Label.getY() + addClientTitle_Label.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientName_Label);
	
	clientName_TF = new JTextField();
	width = 300;
	clientName_TF.setHorizontalAlignment(JTextField.CENTER);
	clientName_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2), clientName_Label.getY() + clientName_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientName_TF);
	

	
	
	
	JLabel clientSurname_Label = new JLabel("Surname:");
	width = (int)clientSurname_Label.getPreferredSize().getWidth();
	height = (int)clientSurname_Label.getPreferredSize().getHeight();
	clientSurname_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) ,clientName_TF.getY() + clientName_TF.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientSurname_Label);
	
	clientSurname_TF = new JTextField();
	width = 300;
	clientSurname_TF.setHorizontalAlignment(JTextField.CENTER);
	clientSurname_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2), clientSurname_Label.getY() + clientSurname_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientSurname_TF);
	
	int insetsMiddle = 80;
	
	JLabel clientFiscalCode_Label = new JLabel("Fiscal Code:");
	width = (int)clientFiscalCode_Label.getPreferredSize().getWidth();
	height = (int)clientFiscalCode_Label.getPreferredSize().getHeight();
	clientFiscalCode_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) - insetsMiddle ,clientSurname_TF.getY() + clientSurname_TF.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientFiscalCode_Label);
	
	clientFiscalCode_TF = new JTextField();
	width = 130;
	clientFiscalCode_TF.setHorizontalAlignment(JTextField.CENTER);
	clientFiscalCode_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) - insetsMiddle, clientFiscalCode_Label.getY() + clientFiscalCode_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientFiscalCode_TF);
	
	JLabel clientCity_Label = new JLabel("City:");
	width = (int)clientCity_Label.getPreferredSize().getWidth();
	height = (int)clientCity_Label.getPreferredSize().getHeight();
	clientCity_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) + insetsMiddle ,clientSurname_TF.getY() + clientSurname_TF.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientCity_Label);
	
	clientCity_TF = new JTextField();
	width = 130;
	clientCity_TF.setHorizontalAlignment(JTextField.CENTER);
	clientCity_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2) + insetsMiddle, clientCity_Label.getY() + clientCity_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientCity_TF);
	
	
	JLabel clientPhone_Label = new JLabel("Phone:");
	width = (int)clientPhone_Label.getPreferredSize().getWidth();
	height = (int)clientPhone_Label.getPreferredSize().getHeight();
	clientPhone_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2),clientCity_TF.getY() + clientCity_TF.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientPhone_Label);
	
	clientPhone_TF = new JTextField();
	width = 150;
	clientPhone_TF.setHorizontalAlignment(JTextField.CENTER);
	clientPhone_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2), clientPhone_Label.getY() + clientPhone_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientPhone_TF);
	
	JLabel clientEmail_Label = new JLabel("Email:");
	width = (int)clientEmail_Label.getPreferredSize().getWidth();
	height = (int)clientEmail_Label.getPreferredSize().getHeight();
	clientEmail_Label.setBounds((addClient_Panel.getWidth() / 2) - (width / 2),clientPhone_TF.getY() + clientPhone_TF.getHeight() + insetsBtwField , width, height);
	addClient_Panel.add(clientEmail_Label);
	
	clientEmail_TF = new JTextField();
	width = 190;
	clientEmail_TF.setHorizontalAlignment(JTextField.CENTER);
	clientEmail_TF.setBounds((addClient_Panel.getWidth() / 2) - (width / 2), clientEmail_Label.getY() + clientEmail_Label.getHeight() + insets, width, 28);
	addClient_Panel.add(clientEmail_TF);		
	
	JButton cancel_Button = new JButton();
	cancel_Button.setName("CancelButton");
	cancel_Button.setText("Cancel");
	cancel_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	cancel_Button.setBounds((addClient_Panel.getWidth() / 2) - buttonWidth - 24,
			addClient_Panel.getHeight() - 20 - addClient_Panel.getY(), buttonWidth, buttonHeight);
	addClient_Panel.add(cancel_Button);
	cancel_Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			addClient_Panel.setVisible(false);
			//outer_Panel.remove(addCompany_Panel);
		}
	});

	JButton save_Button = new JButton();
	save_Button.setName("SaveButton");
	save_Button.setText("Save");
	save_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	save_Button.setBounds((addClient_Panel.getWidth() / 2) + 24,
			addClient_Panel.getHeight() - 20 - addClient_Panel.getY(), buttonWidth, buttonHeight);
	addClient_Panel.add(save_Button);
	save_Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// save client
			
			addClient_Panel.setVisible(false);
			//outer_Panel.remove(addCompany_Panel);
		}
	});
	
	
	
	
}
}

