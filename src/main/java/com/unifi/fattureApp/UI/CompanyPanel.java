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

import org.apache.log4j.Logger;

import com.unifi.fattureApp.App.MongoUiComunication;

public class CompanyPanel extends JPanel implements AddPanel {
	private static final long serialVersionUID = 6387743798709513734L;

	private static final Logger LOGGER = Logger.getLogger(CompanyPanel.class);

	private MongoUiComunication myMongoUiComunication;

	private JTextField companyNameTF;
	private JTextField companyVatTF;
	private JTextField companyAddressTF;
	private JTextField companyCityTF;
	private JTextField companyProvinceTF;
	private JTextField companyZipTF;
	private JTextField companyCountryTF;
	private JTextField companyPhoneTF;
	private JTextField companyEmailTF;
	private CompanyPanel addCompanyPanel;

	private LinkedList<JTextField> textFields;

	private Color layerColor = new java.awt.Color(216, 245, 255);

	private boolean isSaving;

	public CompanyPanel(JLayeredPane outerPanel, int buttonWidth, int buttonHeight, MongoUiComunication mongoUiCom) {
		addCompanyPanel = this;
		myMongoUiComunication = mongoUiCom;

		this.setVisible(false);

		addCompanyPanel.setName("AddCompanyPanel");
		addCompanyPanel.setBackground(layerColor);
		addCompanyPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		int insets = 22;
		int width = outerPanel.getWidth() - insets - insets;
		int height = outerPanel.getHeight() - insets - insets;
		addCompanyPanel.setBounds(insets, insets, width, height);
		outerPanel.add(addCompanyPanel);
		addCompanyPanel.setLayout(null);
		outerPanel.setLayer(addCompanyPanel, 2);

		textFields = new LinkedList<>();

		// ADD COMPONENTS INSIDE PANEL
		int addPanelY = addCompanyPanel.getY();
		insets = 8;
		int insetsBtwField = 23;

		JLabel addCompanyTitleLabel = new JLabel("Company");
		addCompanyTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		width = (int) addCompanyTitleLabel.getPreferredSize().getWidth();
		height = (int) addCompanyTitleLabel.getPreferredSize().getHeight();
		addCompanyTitleLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2), addPanelY - 10, width, height);
		addCompanyPanel.add(addCompanyTitleLabel);

		JLabel companyNameLabel = new JLabel("Company Name:");
		width = (int) companyNameLabel.getPreferredSize().getWidth();
		height = (int) companyNameLabel.getPreferredSize().getHeight();
		companyNameLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				addCompanyTitleLabel.getY() + addCompanyTitleLabel.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyNameLabel);

		companyNameTF = new JTextField();
		companyNameTF.setName("companyNameTextField");
		width = 300;
		companyNameTF.setHorizontalAlignment(JTextField.CENTER);
		companyNameTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyNameLabel.getY() + companyNameLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyNameTF);

		JLabel companyVatLabel = new JLabel("VAT Number:");
		width = (int) companyVatLabel.getPreferredSize().getWidth();
		height = (int) companyVatLabel.getPreferredSize().getHeight();
		companyVatLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyNameTF.getY() + companyNameTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyVatLabel);

		companyVatTF = new JTextField();
		companyVatTF.setName("companyVatTextField");
		width = 200;
		companyVatTF.setHorizontalAlignment(JTextField.CENTER);
		companyVatTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyVatLabel.getY() + companyVatLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyVatTF);

		JLabel companyAddressLabel = new JLabel("Address:");
		width = (int) companyAddressLabel.getPreferredSize().getWidth();
		height = (int) companyAddressLabel.getPreferredSize().getHeight();
		companyAddressLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyVatTF.getY() + companyVatTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyAddressLabel);

		companyAddressTF = new JTextField();
		companyAddressTF.setName("companyAddressTextField");
		width = 300;
		companyAddressTF.setHorizontalAlignment(JTextField.CENTER);
		companyAddressTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyAddressLabel.getY() + companyAddressLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyAddressTF);

		int insetsMiddle = 80;

		JLabel companyCityLabel = new JLabel("City:");
		width = (int) companyCityLabel.getPreferredSize().getWidth();
		height = (int) companyCityLabel.getPreferredSize().getHeight();
		companyCityLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				companyAddressTF.getY() + companyAddressTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyCityLabel);

		companyCityTF = new JTextField();
		companyCityTF.setName("companyCityTextField");
		width = 130;
		companyCityTF.setHorizontalAlignment(JTextField.CENTER);
		companyCityTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				companyCityLabel.getY() + companyCityLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyCityTF);

		JLabel companyProvinceLabel = new JLabel("Province:");
		width = (int) companyProvinceLabel.getPreferredSize().getWidth();
		height = (int) companyProvinceLabel.getPreferredSize().getHeight();
		companyProvinceLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				companyAddressTF.getY() + companyAddressTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyProvinceLabel);

		companyProvinceTF = new JTextField();
		companyProvinceTF.setName("companyProvinceTextField");
		width = 130;
		companyProvinceTF.setHorizontalAlignment(JTextField.CENTER);
		companyProvinceTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				companyProvinceLabel.getY() + companyProvinceLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyProvinceTF);

		JLabel companyZipLabel = new JLabel("ZIP Code:");
		width = (int) companyZipLabel.getPreferredSize().getWidth();
		height = (int) companyZipLabel.getPreferredSize().getHeight();
		companyZipLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				companyProvinceTF.getY() + companyProvinceTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyZipLabel);

		companyZipTF = new JTextField();
		companyZipTF.setName("companyZipTextField");
		width = 80;
		companyZipTF.setHorizontalAlignment(JTextField.CENTER);
		companyZipTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) - insetsMiddle,
				companyZipLabel.getY() + companyZipLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyZipTF);

		JLabel companyCountryLabel = new JLabel("Country:");
		width = (int) companyCountryLabel.getPreferredSize().getWidth();
		height = (int) companyCountryLabel.getPreferredSize().getHeight();
		companyCountryLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				companyProvinceTF.getY() + companyProvinceTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyCountryLabel);

		companyCountryTF = new JTextField();
		companyCountryTF.setName("companyCountryTextField");
		width = 100;
		companyCountryTF.setHorizontalAlignment(JTextField.CENTER);
		companyCountryTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2) + insetsMiddle,
				companyCountryLabel.getY() + companyCountryLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyCountryTF);

		JLabel companyPhoneLabel = new JLabel("Phone:");
		width = (int) companyPhoneLabel.getPreferredSize().getWidth();
		height = (int) companyPhoneLabel.getPreferredSize().getHeight();
		companyPhoneLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyCountryTF.getY() + companyCountryTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyPhoneLabel);

		companyPhoneTF = new JTextField();
		companyPhoneTF.setName("companyPhoneTextField");
		width = 150;
		companyPhoneTF.setHorizontalAlignment(JTextField.CENTER);
		companyPhoneTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyPhoneLabel.getY() + companyPhoneLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyPhoneTF);

		JLabel companyEmailLabel = new JLabel("Email:");
		width = (int) companyEmailLabel.getPreferredSize().getWidth();
		height = (int) companyEmailLabel.getPreferredSize().getHeight();
		companyEmailLabel.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyPhoneTF.getY() + companyPhoneTF.getHeight() + insetsBtwField, width, height);
		addCompanyPanel.add(companyEmailLabel);

		companyEmailTF = new JTextField();
		companyEmailTF.setName("companyEmailTextField");
		width = 190;
		companyEmailTF.setHorizontalAlignment(JTextField.CENTER);
		companyEmailTF.setBounds((addCompanyPanel.getWidth() / 2) - (width / 2),
				companyEmailLabel.getY() + companyEmailLabel.getHeight() + insets, width, 28);
		addCompanyPanel.add(companyEmailTF);

		JButton cancelButton = new JButton();
		cancelButton.setName("CancelButton");
		cancelButton.setText("Cancel");
		cancelButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		cancelButton.setBounds((addCompanyPanel.getWidth() / 2) - buttonWidth - 24,
				addCompanyPanel.getHeight() - 20 - addCompanyPanel.getY(), buttonWidth, buttonHeight);
		addCompanyPanel.add(cancelButton);
		cancelButton.addActionListener(e ->	addCompanyPanel.setVisible(false));

		JButton saveButton = new JButton();
		saveButton.setName("SaveButton");
		saveButton.setEnabled(false);
		saveButton.setText("Save");
		saveButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		saveButton.setBounds((addCompanyPanel.getWidth() / 2) + 24,
				addCompanyPanel.getHeight() - 20 - addCompanyPanel.getY(), buttonWidth, buttonHeight);
		addCompanyPanel.add(saveButton);
		saveButton.addActionListener(e -> {
			if(addCompanyPanel.isSaving()) {
				boolean saved = myMongoUiComunication.addCompanyToDatabase(companyNameTF.getText(),
						companyVatTF.getText(), companyAddressTF.getText(), companyCityTF.getText(),
						companyProvinceTF.getText(), companyZipTF.getText(), companyCountryTF.getText(),
						companyPhoneTF.getText(), companyEmailTF.getText());

				if (saved) {
					myMongoUiComunication.setCurrentSelectedCompany(myMongoUiComunication.getSavedCompanies().get(myMongoUiComunication.getSavedCompanies().size()-1));
					myMongoUiComunication.enableEditCompanyButton();
				} else {
					LOGGER.error("Error: Company was not saved!!!");
				}
			}else {
				boolean saved = myMongoUiComunication.editCompanyFromDatabase(companyNameTF.getText(),
						companyVatTF.getText(), companyAddressTF.getText(), companyCityTF.getText(),
						companyProvinceTF.getText(), companyZipTF.getText(), companyCountryTF.getText(),
						companyPhoneTF.getText(), companyEmailTF.getText());
				if (saved) {
					System.out.println("Modificata con successo Yay");
				} else {
					LOGGER.error("Error: non modificata!!!");
				}
			}

			addCompanyPanel.setVisible(false);
			resetTextFields();
			myMongoUiComunication.updateCompanyReference();
		});

		Component[] components = addCompanyPanel.getComponents();
		for (Component component : components) {
			if (component.getClass().equals(JTextField.class) && !((JTextField) component).getName().equals("companyPhoneTextField")
					&& !((JTextField) component).getName().equals("companyEmailTextField")) {
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

	private void resetTextFields() {
		companyNameTF.setText("");
		companyVatTF.setText("");
		companyAddressTF.setText("");
		companyCityTF.setText("");
		companyProvinceTF.setText("");
		companyZipTF.setText("");
		companyCountryTF.setText("");
		companyPhoneTF.setText("");
		companyEmailTF.setText("");
	}

	public boolean isSaving() {
		return isSaving;
	}

	@Override
	public void setAddingMode(boolean isSaving) {
		this.isSaving = isSaving;
		if(!isSaving) {
			companyNameTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getName());
			companyVatTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getVatCode());
			companyAddressTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getAddress());
			companyCityTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getCity());
			companyProvinceTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getProvince());
			companyZipTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getZipCode());
			companyCountryTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getCountry());
			companyPhoneTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getPhone());
			companyEmailTF.setText(myMongoUiComunication.getCurrentSelectedCompany().getEmail());
		}
		this.setVisible(true);
	}
}