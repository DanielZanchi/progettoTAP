package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.unifi.fattureApp.App.MongoUiComunication;

public class ClientPanel extends JPanel implements AddPanel {
	private static final long serialVersionUID = -4964123340815964907L;

	private MongoUiComunication myMongoUiComunication;

	private JTextField clientNameTF;
	private JTextField clientVatTF;
	private JTextField clientAddressTF;
	private JTextField clientCityTF;
	private JTextField clientProvinceTF;
	private JTextField clientZipTF;
	private JTextField clientCountryTF;
	private JTextField clientPhoneTF;
	private JTextField clientEmailTF;

	private LinkedList<JTextField> textFields;

	private Color layerColor = new java.awt.Color(216, 245, 255);

	private ClientPanel addClientPanel;

	private boolean isSaving;

	public ClientPanel(JLayeredPane outerPanel, int buttonWidth, int buttonHeight, MongoUiComunication mongoUiCom) {
		addClientPanel = this;
		myMongoUiComunication = mongoUiCom;

		this.setVisible(false);

		addClientPanel.setName("AddClientPanel");
		addClientPanel.setBackground(layerColor);
		addClientPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		int insets = 22;
		int width = outerPanel.getWidth() - insets - insets;
		int height = outerPanel.getHeight() - insets - insets;
		addClientPanel.setBounds(insets, insets, width, height);
		outerPanel.add(addClientPanel);
		addClientPanel.setLayout(null);
		outerPanel.setLayer(addClientPanel, 2);

		textFields = new LinkedList<>();

		// ADD COMPONENTS INSIDE PANEL
		int addPanelY = addClientPanel.getY();
		insets = 8;
		int insetsBtwField = 23;

		JLabel addClientTitleLabel = new JLabel("Client");
		addClientTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		width = (int) addClientTitleLabel.getPreferredSize().getWidth();
		height = (int) addClientTitleLabel.getPreferredSize().getHeight();
		addClientTitleLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2), addPanelY - 10, width, height);
		addClientPanel.add(addClientTitleLabel);

		JLabel clientNameLabel = new JLabel("Client Name:");
		width = (int) clientNameLabel.getPreferredSize().getWidth();
		height = (int) clientNameLabel.getPreferredSize().getHeight();
		clientNameLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				addClientTitleLabel.getY() + addClientTitleLabel.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientNameLabel);

		clientNameTF = new JTextField();
		/*
		width = 300;
		clientNameTF.setName("clientName_TF");
		clientNameTF.setHorizontalAlignment(JTextField.CENTER);
		clientNameTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientNameLabel.getY() + clientNameLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientNameTF);
		*/
		setUpTextFieldInThePanel(clientAddressTF, 300, "clientName_TF", (addClientPanel.getWidth() / 2) - (width / 2),
				clientNameLabel.getY() + clientNameLabel.getHeight() + insets,clientNameLabel);
		

		JLabel clientVatLabel = new JLabel("VAT / Fiscal Number:");
		width = (int) clientVatLabel.getPreferredSize().getWidth();
		height = (int) clientVatLabel.getPreferredSize().getHeight();
		clientVatLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientNameTF.getY() + clientNameTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientVatLabel);

		clientVatTF = new JTextField();
		/*
		width = 200;
		clientVatTF.setName("clientVat_TF");
		clientVatTF.setHorizontalAlignment(JTextField.CENTER);
		clientVatTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientVatLabel.getY() + clientVatLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientVatTF);
		*/
		setUpTextFieldInThePanel(clientVatTF, 200, "clientVat_TF", (addClientPanel.getWidth() / 2) - (width / 2),
				clientVatLabel.getY() + clientVatLabel.getHeight() + insets,clientVatLabel);

		JLabel clientAddressLabel = new JLabel("Address:");
		width = (int) clientAddressLabel.getPreferredSize().getWidth();
		height = (int) clientAddressLabel.getPreferredSize().getHeight();
		clientAddressLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientVatTF.getY() + clientVatTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientAddressLabel);

		clientAddressTF = new JTextField();
		/*
		width = 300;
		clientAddressTF.setName("clientAddress_TF");
		clientAddressTF.setHorizontalAlignment(JTextField.CENTER);
		clientAddressTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientAddressLabel.getY() + clientAddressLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientAddressTF);
		*/
		setUpTextFieldInThePanel(clientAddressTF, 300, "clientAddress_TF", (addClientPanel.getWidth() / 2) - (width / 2),
				clientAddressLabel.getY() + clientAddressLabel.getHeight() + insets,clientAddressLabel);

		int insetsMiddle = 80;

		JLabel clientCityLabel = new JLabel("City:");
		width = (int) clientCityLabel.getPreferredSize().getWidth();
		height = (int) clientCityLabel.getPreferredSize().getHeight();
		clientCityLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				clientAddressTF.getY() + clientAddressTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientCityLabel);

		clientCityTF = new JTextField();
		width = 130;
		clientCityTF.setName("clientCity_TF");
		clientCityTF.setHorizontalAlignment(JTextField.CENTER);
		clientCityTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				clientCityLabel.getY() + clientCityLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientCityTF);

		JLabel clientProvinceLabel = new JLabel("Province:");
		width = (int) clientProvinceLabel.getPreferredSize().getWidth();
		height = (int) clientProvinceLabel.getPreferredSize().getHeight();
		clientProvinceLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				clientAddressTF.getY() + clientAddressTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientProvinceLabel);

		clientProvinceTF = new JTextField();
		width = 130;
		clientProvinceTF.setName("clientProvince_TF");
		clientProvinceTF.setHorizontalAlignment(JTextField.CENTER);
		clientProvinceTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				clientProvinceLabel.getY() + clientProvinceLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientProvinceTF);

		JLabel clientZipLabel = new JLabel("ZIP Code:");
		width = (int) clientZipLabel.getPreferredSize().getWidth();
		height = (int) clientZipLabel.getPreferredSize().getHeight();
		clientZipLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				clientProvinceTF.getY() + clientProvinceTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientZipLabel);

		clientZipTF = new JTextField();
		width = 80;
		clientZipTF.setName("clientZip_TF");
		clientZipTF.setHorizontalAlignment(JTextField.CENTER);
		clientZipTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				clientZipLabel.getY() + clientZipLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientZipTF);

		JLabel clientCountryLabel = new JLabel("Country:");
		width = (int) clientCountryLabel.getPreferredSize().getWidth();
		height = (int) clientCountryLabel.getPreferredSize().getHeight();
		clientCountryLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				clientProvinceTF.getY() + clientProvinceTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientCountryLabel);

		clientCountryTF = new JTextField();
		width = 100;
		clientCountryTF.setName("clientCountry_TF");
		clientCountryTF.setHorizontalAlignment(JTextField.CENTER);
		clientCountryTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				clientCountryLabel.getY() + clientCountryLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientCountryTF);

		JLabel clientPhoneLabel = new JLabel("Phone:");
		width = (int) clientPhoneLabel.getPreferredSize().getWidth();
		height = (int) clientPhoneLabel.getPreferredSize().getHeight();
		clientPhoneLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientCountryTF.getY() + clientCountryTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientPhoneLabel);

		clientPhoneTF = new JTextField();
		width = 150;
		clientPhoneTF.setName("clientPhone_TF");
		clientPhoneTF.setHorizontalAlignment(JTextField.CENTER);
		clientPhoneTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientPhoneLabel.getY() + clientPhoneLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientPhoneTF);

		JLabel clientEmailLabel = new JLabel("Email:");
		width = (int) clientEmailLabel.getPreferredSize().getWidth();
		height = (int) clientEmailLabel.getPreferredSize().getHeight();
		clientEmailLabel.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientPhoneTF.getY() + clientPhoneTF.getHeight() + insetsBtwField, width, height);
		addClientPanel.add(clientEmailLabel);

		clientEmailTF = new JTextField();
		width = 190;
		clientEmailTF.setName("clientEmail_TF");
		clientEmailTF.setHorizontalAlignment(JTextField.CENTER);
		clientEmailTF.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				clientEmailLabel.getY() + clientEmailLabel.getHeight() + insets, width, 28);
		addClientPanel.add(clientEmailTF);

		JButton cancelButton = new JButton();
		cancelButton.setName("CancelButton");
		cancelButton.setText("Cancel");
		cancelButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		cancelButton.setBounds((addClientPanel.getWidth() / 2) - buttonWidth - 24,
				addClientPanel.getHeight() - 20 - addClientPanel.getY(), buttonWidth, buttonHeight);
		addClientPanel.add(cancelButton);
		cancelButton.addActionListener(e -> addClientPanel.setVisible(false));

		JButton saveButton = new JButton();
		saveButton.setEnabled(false);
		saveButton.setName("SaveButton");
		saveButton.setText("Save");
		saveButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		saveButton.setBounds((addClientPanel.getWidth() / 2) + 24,
				addClientPanel.getHeight() - 20 - addClientPanel.getY(), buttonWidth, buttonHeight);
		addClientPanel.add(saveButton);

		saveButton.addActionListener(e -> {
			// save company
			if(addClientPanel.isSaving()) {
				myMongoUiComunication.addClientToDatabase(clientNameTF.getText(), clientVatTF.getText(),
						clientAddressTF.getText(), clientCityTF.getText(), clientProvinceTF.getText(),
						clientZipTF.getText(), clientCityTF.getText(), clientPhoneTF.getText(),
						clientEmailTF.getText());
			}
			addClientPanel.setVisible(false);
			resetTextFields();
			mongoUiCom.updateClientsReferences();
		});

		// check if all required field aren't empty. if so activate the save button.

		Component[] components = addClientPanel.getComponents();
		for (Component component : components) {
			if (component.getClass().equals(JTextField.class) && (!((JTextField) component).getName().equals("clientPhone_TF")
					&& !((JTextField) component).getName().equals("clientEmail_TF"))) {
				textFields.add((JTextField) component);
			}
		}

		for (JTextField tf : textFields) {
			tf.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					changed();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					changed();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					changed();
				}

				public void changed() {
					boolean shouldActivate = true;
					for (JTextField tf : textFields) {
						if (tf.getText().equals("")) {
							shouldActivate = false;
							break;
						}
					}
					saveButton.setEnabled(shouldActivate);
				}
			});
		}
	}
	
	
	private void setUpTextFieldInThePanel(JTextField textField,int width,String name,int boundsWidth,int boundsHeigth, JLabel linkedLabel) {
		textField.setName(name);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds((addClientPanel.getWidth() / 2) - (width / 2),
				linkedLabel.getY() + linkedLabel.getHeight() + 8, width, 28);
		addClientPanel.add(textField);
	}


	private void resetTextFields() {
		clientNameTF.setText("");
		clientVatTF.setText("");
		clientAddressTF.setText("");
		clientCityTF.setText("");
		clientProvinceTF.setText("");
		clientZipTF.setText("");
		clientCountryTF.setText("");
		clientPhoneTF.setText("");
		clientEmailTF.setText("");
	}

	public boolean isSaving() {
		return isSaving;
	}

	@Override
	public void setAddingMode(boolean isSaving) {
		this.isSaving = isSaving;
		this.setVisible(true);
	}
}