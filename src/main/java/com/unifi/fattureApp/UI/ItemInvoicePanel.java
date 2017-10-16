package com.unifi.fattureApp.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemInvoicePanel extends JPanel {	
	private JTextField itemDescription_TF;
	private JFormattedTextField itemPrice_TF;

	private Color layerColor = new java.awt.Color(216, 245, 255);

	public ItemInvoicePanel(JLayeredPane outer_Panel, int buttonWidth, int buttonHeight) {
		JPanel addItem_Panel = this;

		addItem_Panel.setName("AddItemPanel");
		addItem_Panel.setBackground(layerColor);
		addItem_Panel.setBorder(BorderFactory.createLineBorder(Color.white, 3));
		int insets = 22;
		int width = outer_Panel.getWidth() - insets - insets;
		int height = outer_Panel.getHeight() - (insets * 2) - 350;
		addItem_Panel.setBounds(insets, insets, width, height);
		outer_Panel.add(addItem_Panel);
		addItem_Panel.setLayout(null);
		outer_Panel.setLayer(addItem_Panel, 2);

		// ADD COMPONENTS INSIDE PANEL
		int addPanelY = addItem_Panel.getY();
		insets = 8;
		int insetsBtwField = 23;
		
		JLabel addItemTitle_Label = new JLabel("Item");
		addItemTitle_Label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		width = (int)addItemTitle_Label.getPreferredSize().getWidth();
		height = (int)addItemTitle_Label.getPreferredSize().getHeight();
		addItemTitle_Label.setBounds((addItem_Panel.getWidth() / 2) - (width / 2) ,addPanelY - 10 , width, height);
		addItem_Panel.add(addItemTitle_Label);
		
		JLabel itemDescription_Label = new JLabel("Item Description:");
		width = (int)itemDescription_Label.getPreferredSize().getWidth();
		height = (int)itemDescription_Label.getPreferredSize().getHeight();
		itemDescription_Label.setBounds((addItem_Panel.getWidth() / 2) - (width / 2) ,addItemTitle_Label.getY() + addItemTitle_Label.getHeight() + insetsBtwField , width, height);
		addItem_Panel.add(itemDescription_Label);
		
		itemDescription_TF = new JTextField();
		width = 350;
		itemDescription_TF.setHorizontalAlignment(JTextField.CENTER);
		itemDescription_TF.setBounds((addItem_Panel.getWidth() / 2) - (width / 2), itemDescription_Label.getY() + itemDescription_Label.getHeight() + insets, width, 28);
		addItem_Panel.add(itemDescription_TF);
		
		JLabel itemPrice_Label = new JLabel("Price (Excl. VAT):");
		width = (int)itemPrice_Label.getPreferredSize().getWidth();
		height = (int)itemPrice_Label.getPreferredSize().getHeight();
		itemPrice_Label.setBounds((addItem_Panel.getWidth() / 2) - (width / 2) ,itemDescription_TF.getY() + itemDescription_TF.getHeight() + insetsBtwField , width, height);
		addItem_Panel.add(itemPrice_Label);
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		
		itemPrice_TF = new JFormattedTextField(decimalFormat);
		itemPrice_TF.setColumns(15);
		width = 70;
		itemPrice_TF.setHorizontalAlignment(JTextField.CENTER);
		itemPrice_TF.setBounds((addItem_Panel.getWidth() / 2) - (width / 2), itemPrice_Label.getY() + itemPrice_Label.getHeight() + insets, width, 28);
		addItem_Panel.add(itemPrice_TF);
		
		JButton cancel_Button = new JButton();
		cancel_Button.setName("CancelButton");
		cancel_Button.setText("Cancel");
		cancel_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		cancel_Button.setBounds((addItem_Panel.getWidth() / 2) - buttonWidth - 24,
				addItem_Panel.getHeight() - 20 - addItem_Panel.getY(), buttonWidth, buttonHeight);
		addItem_Panel.add(cancel_Button);
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem_Panel.setVisible(false);
				//outer_Panel.remove(addClient_Panel);
			}
		});

		JButton save_Button = new JButton();
		save_Button.setName("SaveButton");
		save_Button.setText("Save");
		save_Button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		save_Button.setBounds((addItem_Panel.getWidth() / 2) + 24,
				addItem_Panel.getHeight() - 20 - addItem_Panel.getY(), buttonWidth, buttonHeight);
		addItem_Panel.add(save_Button);
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// save company
				System.out.println(itemPrice_TF.getText());
				addItem_Panel.setVisible(false);
				//outer_Panel.remove(addClient_Panel);
			}
		});
	}
}