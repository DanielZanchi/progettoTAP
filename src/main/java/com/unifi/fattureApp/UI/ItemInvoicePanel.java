package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.unifi.fattureApp.App.MongoUiComunication;

public class ItemInvoicePanel extends JPanel implements AddPanel {
	private static final long serialVersionUID = 1L;
	private JTextField itemDescriptionTF;
	private JTextField itemNameTF;
	private JFormattedTextField itemPriceTF;

	private LinkedList<JTextField> textFields;
	private MongoUiComunication mongoUiComunication;

	private Color layerColor = new java.awt.Color(216, 245, 255);
	private ItemInvoicePanel addItemPanel;
	private boolean isSaving;

	public ItemInvoicePanel(JLayeredPane outerPanel, int buttonWidth, int buttonHeight,MongoUiComunication mongoUiCom) {
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

		// ADD COMPONENTS INSIDE PANEL
		int addPanelY = addItemPanel.getY();
		insets = 8;
		int insetsBtwField = 23;

		JLabel addItemTitleLabel = new JLabel("Item");
		addItemTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		width = (int) addItemTitleLabel.getPreferredSize().getWidth();
		height = (int) addItemTitleLabel.getPreferredSize().getHeight();
		addItemTitleLabel.setBounds((addItemPanel.getWidth() / 2) - (width / 2), addPanelY - 10, width, height);
		addItemPanel.add(addItemTitleLabel);

		JLabel itemNameLabel = new JLabel("Item Name:");
		width = (int) itemNameLabel.getPreferredSize().getWidth();
		height = (int) itemNameLabel.getPreferredSize().getHeight();
		itemNameLabel.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				addItemTitleLabel.getY() + addItemTitleLabel.getHeight() + insetsBtwField, width, height);
		addItemPanel.add(itemNameLabel);
		itemNameTF = new JTextField();
		width = 200;
		itemNameTF.setName("invoiceName_TF");
		itemNameTF.setHorizontalAlignment(JTextField.CENTER);
		itemNameTF.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				itemNameLabel.getY() + itemNameLabel.getHeight() + insets, width, 28);
		addItemPanel.add(itemNameTF);

		JLabel itemDescriptionLabel = new JLabel("Item Description:");
		width = (int) itemDescriptionLabel.getPreferredSize().getWidth();
		height = (int) itemDescriptionLabel.getPreferredSize().getHeight();
		itemDescriptionLabel.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				itemNameTF.getY() + itemNameTF.getHeight() + insetsBtwField, width, height);
		addItemPanel.add(itemDescriptionLabel);
		itemDescriptionTF = new JTextField();
		width = 350;
		itemDescriptionTF.setHorizontalAlignment(JTextField.CENTER);
		itemDescriptionTF.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				itemDescriptionLabel.getY() + itemDescriptionLabel.getHeight() + insets, width, 28);
		itemDescriptionTF.setName("invoiceDescription_TF");
		addItemPanel.add(itemDescriptionTF);

		JLabel itemPriceLabel = new JLabel("Price (incl. VAT):");
		width = (int) itemPriceLabel.getPreferredSize().getWidth();
		height = (int) itemPriceLabel.getPreferredSize().getHeight();
		itemPriceLabel.setBounds((addItemPanel.getWidth() / 2) - (width / 2),
				itemDescriptionTF.getY() + itemDescriptionTF.getHeight() + insetsBtwField, width, height);
		addItemPanel.add(itemPriceLabel);
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

		JButton cancelButton = new JButton();
		cancelButton.setName("CancelButton");
		cancelButton.setText("Cancel");
		cancelButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		cancelButton.setBounds((addItemPanel.getWidth() / 2) - buttonWidth - 24,
				addItemPanel.getHeight() - 20 - addItemPanel.getY(), buttonWidth, buttonHeight);
		addItemPanel.add(cancelButton);
		cancelButton.addActionListener(e -> addItemPanel.setVisible(false));

		JButton saveButton = new JButton();
		saveButton.setName("SaveButton");
		saveButton.setText("Save");
		saveButton.setEnabled(false);
		saveButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		saveButton.setBounds((addItemPanel.getWidth() / 2) + 24,
				addItemPanel.getHeight() - 20 - addItemPanel.getY(), buttonWidth, buttonHeight);
		addItemPanel.add(saveButton);
		saveButton.addActionListener(e -> {
			// save invoice
			if(addItemPanel.isSaving()) {
				mongoUiComunication.addInvoiceToDatabase(itemNameTF.getText(), itemDescriptionTF.getText(), itemPriceTF.getText());
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

	public boolean isSaving() {
		return isSaving;
	}

	@Override
	public void setAddingMode(boolean isSaving) {
		this.isSaving = isSaving;
		this.setVisible(true);
	}
}