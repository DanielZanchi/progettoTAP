package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.unifi.fattureApp.App.DatabaseUiComunication;

public class ClientPanel extends PanelWithObligatoryTextFields implements AddPanel {
	private static final long serialVersionUID = -4964123340815964907L;
	private static final Logger LOGGER = Logger.getLogger(ClientPanel.class);

	private DatabaseUiComunication myDatabaseUiComunication;

	private JTextField clientNameTF;
	private JTextField clientVatTF;
	private JTextField clientAddressTF;
	private JTextField clientCityTF;
	private JTextField clientProvinceTF;
	private JTextField clientZipTF;
	private JTextField clientCountryTF;
	private JTextField clientPhoneTF;
	private JTextField clientEmailTF;

	private Color layerColor = new java.awt.Color(216, 245, 255);
	private ClientPanel addClientPanel;
	private boolean isSaving;

	public ClientPanel(JLayeredPane outerPanel, int buttonWidth, int buttonHeight, DatabaseUiComunication dbUiCom) {
		addClientPanel = this;
		myDatabaseUiComunication = dbUiCom;

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

		initLabelsTextFields();

		FormattedButton cancelButton = new FormattedButton("Cancel", "CancelButton");
		cancelButton.setBounds((addClientPanel.getWidth() / 2) - buttonWidth - 24,
				addClientPanel.getHeight() - 20 - addClientPanel.getY(), buttonWidth, buttonHeight);
		addClientPanel.add(cancelButton);
		cancelButton.addActionListener(e -> addClientPanel.setVisible(false));

		FormattedButton saveButton = new FormattedButton("Save", "SaveButton");
		saveButton.setEnabled(false);
		saveButton.setBounds((addClientPanel.getWidth() / 2) + 24,
				addClientPanel.getHeight() - 20 - addClientPanel.getY(), buttonWidth, buttonHeight);
		addClientPanel.add(saveButton);

		saveButton.addActionListener(e -> {
			// save company
			if(addClientPanel.isSaving()) {
				myDatabaseUiComunication.addClientToDatabase(clientNameTF.getText(), clientVatTF.getText(),
						clientAddressTF.getText(), clientCityTF.getText(), clientProvinceTF.getText(),
						clientZipTF.getText(), clientCityTF.getText(), clientPhoneTF.getText(),
						clientEmailTF.getText());
			}else {
				boolean saved=myDatabaseUiComunication.editClientFromDatabase(clientNameTF.getText(),
						clientVatTF.getText(), clientAddressTF.getText(), clientCityTF.getText(),
						clientProvinceTF.getText(), clientZipTF.getText(), clientCountryTF.getText(),
						clientPhoneTF.getText(), clientEmailTF.getText());
				if (saved) {
					LOGGER.info(" Client modificata con successo");
				} else {
					LOGGER.error("Error: Client non modificata");
				}
			}
			addClientPanel.setVisible(false);
			resetTextFields();
			dbUiCom.updateClientsReferences();
		});

		String [] freeTextFields= {"clientPhone_TF","clientEmail_TF"};
		super.setUpTextFields(addClientPanel.getComponents(),freeTextFields,saveButton);
	}

	private void initLabelsTextFields() {
		int addPanelY = addClientPanel.getY();
		int insets = 8;
		int insetsMiddle = 80;

		JLabel addClientTitleLabel = new JLabel("Client");
		addClientTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		addClientTitleLabel.setBounds((int) (((double)addClientPanel.getWidth() / 2) - (addClientTitleLabel.getPreferredSize().getWidth() / 2)), addPanelY - 10, (int)addClientTitleLabel.getPreferredSize().getWidth(), (int)addClientTitleLabel.getPreferredSize().getHeight());
		addClientPanel.add(addClientTitleLabel);

		JLabel clientNameLabel = new JLabel("Client Name:");
		setUpLabelInThePanel(clientNameLabel, 0, addClientTitleLabel);

		clientNameTF = new JTextField();
		setUpTextFieldInThePanel(clientNameTF, 300, "clientName_TF", 0, insets, clientNameLabel);

		JLabel clientVatLabel = new JLabel("VAT / Fiscal Number:");
		setUpLabelInThePanel(clientVatLabel, 0, clientNameTF);

		clientVatTF = new JTextField();
		setUpTextFieldInThePanel(clientVatTF, 200, "clientVat_TF", 0, insets, clientVatLabel);

		JLabel clientAddressLabel = new JLabel("Address:");
		setUpLabelInThePanel(clientAddressLabel, 0, clientVatTF);

		clientAddressTF = new JTextField();
		setUpTextFieldInThePanel(clientAddressTF, 300, "clientAddress_TF", 0, insets, clientAddressLabel);

		JLabel clientCityLabel = new JLabel("City:");
		setUpLabelInThePanel(clientCityLabel, - insetsMiddle, clientAddressTF);

		clientCityTF = new JTextField();
		setUpTextFieldInThePanel(clientCityTF, 130, "clientCity_TF", - insetsMiddle, insets, clientCityLabel);

		JLabel clientProvinceLabel = new JLabel("Province:");
		setUpLabelInThePanel(clientProvinceLabel, insetsMiddle, clientAddressTF);

		clientProvinceTF = new JTextField();
		setUpTextFieldInThePanel(clientProvinceTF, 130, "clientProvince_TF", insetsMiddle, insets, clientProvinceLabel);

		JLabel clientZipLabel = new JLabel("ZIP Code:");
		setUpLabelInThePanel(clientZipLabel, -insetsMiddle, clientProvinceTF);

		clientZipTF = new JTextField();
		setUpTextFieldInThePanel(clientZipTF, 80, "clientZip_TF", - insetsMiddle, insets, clientZipLabel);

		JLabel clientCountryLabel = new JLabel("Country:");
		setUpLabelInThePanel(clientCountryLabel, insetsMiddle, clientProvinceTF);

		clientCountryTF = new JTextField();
		setUpTextFieldInThePanel(clientCountryTF, 100, "clientCountry_TF",  insetsMiddle, insets, clientCountryLabel);

		JLabel clientPhoneLabel = new JLabel("Phone:");
		setUpLabelInThePanel(clientPhoneLabel, 0, clientCountryTF);

		clientPhoneTF = new JTextField();
		setUpTextFieldInThePanel(clientPhoneTF, 150, "clientPhone_TF",0, insets, clientPhoneLabel);

		JLabel clientEmailLabel = new JLabel("Email:");
		setUpLabelInThePanel(clientEmailLabel, 0, clientPhoneTF);

		clientEmailTF = new JTextField();
		setUpTextFieldInThePanel(clientEmailTF, 190, "clientEmail_TF",0, insets, clientEmailLabel);
	}

	private void setUpLabelInThePanel(JLabel label, int insetsMiddle, JComponent relatedComponent) {
		int width = (int) label.getPreferredSize().getWidth();
		int height = (int) label.getPreferredSize().getHeight();
		label.setBounds((addClientPanel.getWidth() / 2) - (width / 2)+insetsMiddle, relatedComponent.getY() + relatedComponent.getHeight() + 23, width, height);
		addClientPanel.add(label);
	}

	private void setUpTextFieldInThePanel(JTextField textField, int width, String name, int insetsWidth, int insets, JLabel linkedLabel) {
		textField.setName(name);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds((addClientPanel.getWidth() / 2) - (width / 2)+insetsWidth, linkedLabel.getY() + linkedLabel.getHeight() + insets, width, 28);
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
		if(!isSaving) {
			clientNameTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getName());
			clientVatTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getFiscalCode());
			clientAddressTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getCityResidence());
			clientCityTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getCity());
			clientProvinceTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getProvince());
			clientZipTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getZip());
			clientCountryTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getCountry());
			clientPhoneTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getPhone());
			clientEmailTF.setText(myDatabaseUiComunication.getCurrentSelectedClient().getEmail());
		}
		this.setVisible(true);
	}
}