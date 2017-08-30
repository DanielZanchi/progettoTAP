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

import com.unifi.fattureApp.App.MongoUiComunication;

public class CompanyPanel extends JPanel{
	
	private MongoUiComunication myMongoUiComunication;

	
	private JTextField companyName_TF;
	private JTextField companyVat_TF;
	private JTextField companyAddress_TF;
	private JTextField companyCity_TF;
	private JTextField companyProvince_TF;
	private JTextField companyZip_TF;
	private JTextField companyCountry_TF;
	private JTextField companyPhone_TF;
	private JTextField companyEmail_TF;
	
	private Color layerColor = new java.awt.Color(216,245,255);

	public CompanyPanel(JLayeredPane outer_Panel,int buttonWidth,int buttonHeight,MongoUiComunication mongoUiCom){
	JPanel addCompany_Panel=this;
	myMongoUiComunication=mongoUiCom;
	
	addCompany_Panel.setName("AddCompanyPanel");
	addCompany_Panel.setBackground(layerColor);
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
	companyName_TF.setName("companyNameTextField");
	width = 300;
	companyName_TF.setHorizontalAlignment(JTextField.CENTER);
	companyName_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyName_Label.getY() + companyName_Label.getHeight() + insets, width, 28);
	addCompany_Panel.add(companyName_TF);
	
	JLabel companyVat_Label = new JLabel("VAT Number:");
	width = (int)companyVat_Label.getPreferredSize().getWidth();
	height = (int)companyVat_Label.getPreferredSize().getHeight();
	companyVat_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,companyName_TF.getY() + companyName_TF.getHeight() + insetsBtwField , width, height);
	addCompany_Panel.add(companyVat_Label);
	
	companyVat_TF = new JTextField();
	companyVat_TF.setName("companyVatTextField");
	width = 200;
	companyVat_TF.setHorizontalAlignment(JTextField.CENTER);
	companyVat_TF.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2), companyVat_Label.getY() + companyVat_Label.getHeight() + insets, width, 28);
	addCompany_Panel.add(companyVat_TF);
	
	JLabel companyAddress_Label = new JLabel("Address:");
	width = (int)companyAddress_Label.getPreferredSize().getWidth();
	height = (int)companyAddress_Label.getPreferredSize().getHeight();
	companyAddress_Label.setBounds((addCompany_Panel.getWidth() / 2) - (width / 2) ,companyVat_TF.getY() + companyVat_TF.getHeight() + insetsBtwField , width, height);
	addCompany_Panel.add(companyAddress_Label);
	
	companyAddress_TF = new JTextField();
	companyAddress_TF.setName("companyAddressTextField");
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
	companyCity_TF.setName("companyCityTextField");
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
	companyProvince_TF.setName("companyProvinceTextField");
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
	companyZip_TF.setName("companyZipTextField");
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
	companyCountry_TF.setName("companyCountryTextField");
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
	companyPhone_TF.setName("companyPhoneTextField");
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
	companyEmail_TF.setName("companyEmailTextField");
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
			//outer_Panel.remove(addCompany_Panel);
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
			
			boolean saved=myMongoUiComunication.addCompanyToDatabase(companyName_TF.getText(), 
					companyVat_TF.getText(),
			        companyAddress_TF.getText(), 
					companyCity_TF.getText(),
					companyProvince_TF.getText(),
					companyZip_TF.getText(),
					companyCountry_TF.getText(),
					companyPhone_TF.getText(),
					companyEmail_TF.getText());
			
			if(saved){
			myMongoUiComunication.printAllCompanies();
			}else{
				System.err.println("Error: Company was not saved!!!");;
			}
			
			addCompany_Panel.setVisible(false);
			resetTextFields();
			//outer_Panel.remove(addCompany_Panel);
		}
	});
	
	
	
	
}
	
	private void resetTextFields(){
		companyName_TF.setText("");
		 companyVat_TF.setText("");
		 companyAddress_TF.setText("");
		 companyCity_TF.setText("");
		 companyProvince_TF.setText("");
		 companyZip_TF.setText("");
		 companyCountry_TF.setText("");
		 companyPhone_TF.setText("");
		 companyEmail_TF.setText("");
	}

}
	
	