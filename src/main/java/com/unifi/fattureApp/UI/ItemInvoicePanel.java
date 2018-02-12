package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.unifi.fattureApp.App.MongoUiComunication;

public class ItemInvoicePanel extends JPanel implements AddPanel {
	private static final long serialVersionUID = 8698651509983266694L;

	private JTextField itemDescriptionTF;
	private JTextField itemNameTF;
	private JFormattedTextField itemPriceTF;

	private LinkedList<JTextField> textFields;
	private MongoUiComunication mongoUiComunication;

	private Color layerColor = new java.awt.Color(216, 245, 255);
	private ItemInvoicePanel addItemPanel;
	private boolean isSaving;

	public ItemInvoicePanel(JLayeredPane outerPanel, int buttonWidth, int buttonHeight, MongoUiComunication mongoUiCom) {
		this.mongoUiComunication = mongoUiCom;
		addItemPanel = this;
		this.setVisible(false);

		addItemPanel.setName("AddInvoicePanel");
		addItemPanel.setBackground(layerColor);
		addItemPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		int insets = 22;
		int width = outerPanel.getWidth() - insets - insets;
		int height = outerPanel.getHeight() - (insets * 2) - 250;
		addItemPanel.setBounds(insets, insets, width, height);
		outerPanel.add(addItemPanel);
		addItemPanel.setLayout(null);
		outerPanel.setLayer(addItemPanel, 2);

		textFields = new LinkedList<>();

		initLabelsTextFields();

		insets = 8;

		FormattedButton cancelButton = new FormattedButton("Cancel", "CancelButton");
		cancelButton.setBounds((addItemPanel.getWidth() / 2) - buttonWidth - 24,
				addItemPanel.getHeight() - 20 - addItemPanel.getY(), buttonWidth, buttonHeight);
		addItemPanel.add(cancelButton);
		cancelButton.addActionListener(e -> addItemPanel.setVisible(false));

		FormattedButton saveButton = new FormattedButton("Save", "SaveButton");
		saveButton.setEnabled(false);
		saveButton.setBounds((addItemPanel.getWidth() / 2) + 24,
				addItemPanel.getHeight() - 20 - addItemPanel.getY(), buttonWidth, buttonHeight);
		addItemPanel.add(saveButton);
		saveButton.addActionListener(e -> {
			// save invoice
			if(addItemPanel.isSaving()) {
				mongoUiComunication.addInvoiceToDatabase(itemNameTF.getText(), itemDescriptionTF.getText(), itemPriceTF.getText());
			}else {
				boolean saved = mongoUiComunication.editInvoiceFromDatabase(itemNameTF.getText(), itemPriceTF.getText(), itemDescriptionTF.getText());
			}

			addItemPanel.setVisible(false);
			mongoUiComunication.updateInvoicesReferences();
		});

		// check if all required field aren't empty. if so activate the save button.
		Component[] components = addItemPanel.getComponents();
		for (Component component : components) {
			if (component.getClass().equals(JTextField.class)) {
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
		int addPanelY = addItemPanel.getY();
		int insets = 8;
		int insetsMiddle = 23;

		JLabel addItemTitleLabel = new JLabel("Item");
		addItemTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		int width = (int) addItemTitleLabel.getPreferredSize().getWidth();
		int height = (int) addItemTitleLabel.getPreferredSize().getHeight();
		addItemTitleLabel.setBounds((addItemPanel.getWidth() / 2) - (width / 2), addPanelY - 10, width, height);
		addItemPanel.add(addItemTitleLabel);

		JLabel itemNameLabel = new JLabel("Item Name:");
		setUpLabelInThePanel(itemNameLabel, 0, addItemTitleLabel);

		itemNameTF = new JTextField();
		setUpTextFieldInThePanel(itemNameTF, 200, "invoiceName_TF", 0, insetsMiddle, itemNameLabel);

		JLabel itemDescriptionLabel = new JLabel("Item Description:");
		setUpLabelInThePanel(itemDescriptionLabel, 0, itemNameTF);

		itemDescriptionTF = new JTextField();
		setUpTextFieldInThePanel(itemDescriptionTF, 350, "invoiceDescription_TF", 0, insetsMiddle, itemDescriptionLabel);

		JLabel itemPriceLabel = new JLabel("Price (incl. VAT):");
		setUpLabelInThePanel(itemPriceLabel, 0, itemDescriptionTF);

		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		itemPriceTF = new JFormattedTextField(decimalFormat);
		itemPriceTF.setColumns(15);
		width = 70;
		itemPriceTF.setHorizontalAlignment(JTextField.CENTER);
		itemPriceTF.setName("invoicePrice_TF");
		itemPriceTF.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				itemPriceLabel.getY() + itemPriceLabel.getHeight() + insets, width, 28);
		addItemPanel.add(itemPriceTF);
	}

	private void setUpLabelInThePanel(JLabel label, int insetsMiddle, JComponent relatedComponent) {
		int width = (int) label.getPreferredSize().getWidth();
		int height = (int) label.getPreferredSize().getHeight();
		label.setBounds((addItemPanel.getWidth() / 2) - (width / 2)+insetsMiddle, relatedComponent.getY() + relatedComponent.getHeight() + 23, width, height);
		addItemPanel.add(label);
	}

	private void setUpTextFieldInThePanel(JTextField textField, int width, String name, int insetsWidth, int insets, JLabel linkedLabel) {
		textField.setName(name);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds((addItemPanel.getWidth() / 2) - (width / 2)+insetsWidth, linkedLabel.getY() + linkedLabel.getHeight() + insets, width, 28);
		addItemPanel.add(textField);
	}

	public boolean isSaving() {
		return isSaving;
	}

	@Override
	public void setAddingMode(boolean isSaving) {
		this.isSaving = isSaving;
		if(!isSaving) {
			itemNameTF.setText(mongoUiComunication.getCurrentSelectedInvoice().getName());
			itemPriceTF.setText(mongoUiComunication.getCurrentSelectedInvoice().getPrice());
			itemDescriptionTF.setText(mongoUiComunication.getCurrentSelectedInvoice().getDescription());
		}
		this.setVisible(true);
	}
}