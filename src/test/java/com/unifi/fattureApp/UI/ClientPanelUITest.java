package com.unifi.fattureApp.UI;

import java.io.IOException;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientPanelUITest {
	private FrameFixture window;
	private JPanelFixture addClient_Panel;
	private JButtonFixture cancelAdd_Button;
	private JButtonFixture saveAdd_Button;
	private JButtonFixture addClient_Button;
	private JButtonFixture editClient_Button;

	@Before
	public void setUp() throws IOException {
		MainWindowUI frame = new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		addClient_Button = window.panel("ClientPanel").button("AddClientButton");
		editClient_Button = window.panel("ClientPanel").button("editClientButton");
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void testAddButtonAction() {		
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		addClient_Panel.requireVisible();
	}

	@Test
	public void testCancelButtonText() {
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		cancelAdd_Button = addClient_Panel.button("CancelButton");
		cancelAdd_Button.requireText("Cancel");
	}

	@Test
	public void testCancelButtonAction() {
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		cancelAdd_Button = addClient_Panel.button("CancelButton");
		cancelAdd_Button.click();
		addClient_Panel.requireNotVisible();
	}

	@Test
	public void testSaveButtonText() {
		getSaveButton();
		saveAdd_Button.requireText("Save");
	}

	@Test
	public void testSaveButtonNoInputsAction() {
		getSaveButton();
		saveAdd_Button.requireDisabled();
	}

	@Test
	public void testSaveButtonWithInputsAction() {
		getSaveButton();
		setTextfieldsStrings("0", "1", "2", "3", "4", "5", "6", "", "");
		saveAdd_Button.click();
		addClient_Panel.requireNotVisible();
	}

	@Test
	public void testSaveButtonWithWrongInputsAction() {
		getSaveButton();
		setTextfieldsStrings("0", "1", "2", "3", "4", "5", "", "", "");
		saveAdd_Button.requireDisabled();
	}
	
	// edit button
	
	@Test 
	public void testEditButtonActionAddPanelVisible() {
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		setTextfieldsStrings("0", "1", "2", "3", "4", "", "", "", "");
		saveAdd_Button = addClient_Panel.button("SaveButton");
		saveAdd_Button.click();
		editClient();
		addClient_Panel.requireVisible();
	}
	
	@Test 
	public void testEditButtonWithNoCompanySelected() {
		editClient();
		editClient_Button.requireDisabled();
	}
	
	@Test 
	public void testEditButtonNameTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientName = addClient_Panel.textBox("clientName_TF");
		clientName.text().compareTo("0");
	}
	
	@Test 
	public void testEditButtonVatCodeTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientVat = addClient_Panel.textBox("clientVat_TF");
		clientVat.text().compareTo("1");
	}
	
	@Test 
	public void testEditButtonAddressTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientAddress = addClient_Panel.textBox("clientAddress_TF");
		clientAddress.text().compareTo("2");
	}
	
	@Test 
	public void testEditButtonCityTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientCity = addClient_Panel.textBox("clientCity_TF");
		clientCity.text().compareTo("3");
	}
	
	@Test 
	public void testEditButtonProvinceTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientProvince = addClient_Panel.textBox("clientProvince_TF");
		clientProvince.text().compareTo("4");
	}
	
	@Test 
	public void testEditButtonZipTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientZip = addClient_Panel.textBox("clientZip_TF");
		clientZip.text().compareTo("5");
	}
	
	@Test 
	public void testEditButtonCountryTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientCountry = addClient_Panel.textBox("clientCountry_TF");
		clientCountry.text().compareTo("6");
	}
	
	@Test 
	public void testEditButtonPhoneTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientPhone = addClient_Panel.textBox("clientPhone_TF");
		clientPhone.text().compareTo("7");
	}
	
	@Test 
	public void testEditButtonEmailTextField() {
		initTextFieldsForEditButtonAssertions();
		JTextComponentFixture clientEmail = addClient_Panel.textBox("clientEmail_TF");
		clientEmail.text().compareTo("");
	}
	
	@Test 
	public void testEditButtonNameModified() {
		checkTextfieldTextAfterClientSaved("clientName_TF", "edited");
	}
	
	@Test 
	public void testEditButtonVatModified() {
		checkTextfieldTextAfterClientSaved("clientVat_TF", "edited");
	}
	
	@Test 
	public void testEditButtonAddressModified() {
		checkTextfieldTextAfterClientSaved("clientAddress_TF", "edited");
	}
	
	@Test 
	public void testEditButtonCityModified() {
		checkTextfieldTextAfterClientSaved("clientCity_TF", "edited");
	}
	
	@Test 
	public void testEditButtonProvinceModified() {
		checkTextfieldTextAfterClientSaved("clientProvince_TF", "edited");
	}
	
	@Test 
	public void testEditButtonZipModified() {
		checkTextfieldTextAfterClientSaved("clientZip_TF", "edited");
	}
	
	@Test 
	public void testEditButtonCountryModified() {
		checkTextfieldTextAfterClientSaved("clientCountry_TF", "edited");
	}
	
	@Test 
	public void testEditButtonPhoneModified() {
		checkTextfieldTextAfterClientSaved("clientPhone_TF", "edited");
	}
	
	@Test 
	public void testEditButtonEmailModified() {
		checkTextfieldTextAfterClientSaved("clientEmail_TF", "edited");
	}
	
	
	
	// Help methods
	
	private void checkTextfieldTextAfterClientSaved(String textFieldName, String newText) {
		initTextFieldsForEditButtonAssertions();
		addClient_Panel.textBox(textFieldName).setText(newText);
		saveAdd_Button.click();
		editClient();
		JTextComponentFixture clientTextField = addClient_Panel.textBox(textFieldName);
		clientTextField.text().compareTo(newText);
	}
	
	private void editClient() {
		editClient_Button.click();
	}

	private void getSaveButton() {
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		saveAdd_Button = addClient_Panel.button("SaveButton");
	}

	private void setTextfieldsStrings(String string1, String string2, String string3, String string4, String string5,
			String string6, String string7, String string8, String string9) {
		addClient_Panel.textBox("clientName_TF").setText(string1);
		addClient_Panel.textBox("clientVat_TF").setText(string2);
		addClient_Panel.textBox("clientAddress_TF").setText(string3);
		addClient_Panel.textBox("clientCity_TF").setText(string4);
		addClient_Panel.textBox("clientProvince_TF").setText(string5);
		addClient_Panel.textBox("clientZip_TF").setText(string6);
		addClient_Panel.textBox("clientCountry_TF").setText(string7);
		addClient_Panel.textBox("clientPhone_TF").setText(string8);
		addClient_Panel.textBox("clientEmail_TF").setText(string9);
	}
	
	private void initTextFieldsForEditButtonAssertions() {
		addClient_Button.click();
		addClient_Panel = window.panel("AddClientPanel");
		setTextfieldsStrings("0", "1", "2", "3", "4", "5", "6", "7", "");
		saveAdd_Button = addClient_Panel.button("SaveButton");
		saveAdd_Button.click();
		editClient();
	}
}