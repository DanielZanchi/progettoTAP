package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
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

		initLabelsTextFields();

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
			}else {
				boolean saved = myMongoUiComunication.editClientFromDatabase(clientNameTF.getText(),
						clientVatTF.getText(), clientAddressTF.getText(), clientCityTF.getText(),
						clientProvinceTF.getText(), clientZipTF.getText(), clientCountryTF.getText(),
						clientPhoneTF.getText(), clientEmailTF.getText());
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


	private void initLabelsTextFields() {
		int addPanelY = addClientPanel.getY();
		int insets = 8;
		int insetsMiddle = 80;

		JLabel addClientTitleLabel = new JLabel("Client");
		addClientTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		addClientTitleLabel.setBounds((int) ((addClientPanel.getWidth() / 2) - (addClientTitleLabel.getPreferredSize().getWidth() / 2)), addPanelY - 10, (int)addClientTitleLabel.getPreferredSize().getWidth(), (int)addClientTitleLabel.getPreferredSize().getHeight());
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
			clientNameTF.setText(myMongoUiComunication.getCurrentSelectedClient().getName());
			clientVatTF.setText(myMongoUiComunication.getCurrentSelectedClient().getFiscalCode());
			clientAddressTF.setText(myMongoUiComunication.getCurrentSelectedClient().getCityResidence());
			clientCityTF.setText(myMongoUiComunication.getCurrentSelectedClient().getCity());
			clientProvinceTF.setText(myMongoUiComunication.getCurrentSelectedClient().getProvince());
			clientZipTF.setText(myMongoUiComunication.getCurrentSelectedClient().getZip());
			clientCountryTF.setText(myMongoUiComunication.getCurrentSelectedClient().getCountry());
			clientPhoneTF.setText(myMongoUiComunication.getCurrentSelectedClient().getPhone());
			clientEmailTF.setText(myMongoUiComunication.getCurrentSelectedClient().getEmail());
		}
		this.setVisible(true);
	}
}