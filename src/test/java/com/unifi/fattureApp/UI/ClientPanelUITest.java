package com.unifi.fattureApp.UI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientPanelUITest {
	private FrameFixture window;
	private JPanelFixture addClient_Panel;
	private JButtonFixture cancelAdd_Button;
	private JButtonFixture saveAdd_Button;
	private JButtonFixture addClient_Button;


	@Before
	public void setUp() {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame = new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		addClient_Button = window.panel("ClientPanel").button("AddClientButton");
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
		setTextfieldsStrings("0","1","2","3","4","5","6","","");
		saveAdd_Button.click();
		addClient_Panel.requireNotVisible();
	}
	
	
	@Test
	public void testSaveButtonWithWrongInputsAction() {
		getSaveButton();
		setTextfieldsStrings("0","1","2","3","4","5","","","");
		saveAdd_Button.requireDisabled();
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
	
}