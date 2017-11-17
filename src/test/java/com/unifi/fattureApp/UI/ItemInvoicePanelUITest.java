package com.unifi.fattureApp.UI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemInvoicePanelUITest {
	private FrameFixture window;
	private JPanelFixture addInvoice_Panel;
	private JButtonFixture cancelAdd_Button;
	private JButtonFixture saveAdd_Button;
	private JButtonFixture addInvoice_Button;
	
	@Before
	public void setUp() {
		MainWindowUI frame = new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		addInvoice_Button = window.panel("InvoicePanel").button("AddInvoiceButton");
	}
	
	@After
	public void tearDown() {
		window.cleanUp();
	}
	
	@Test
	public void testAddButtonAction() {
		addInvoice_Button.click();
		addInvoice_Panel = window.panel("AddInvoicePanel");
		addInvoice_Panel.requireVisible();
	}
	
	@Test
	public void testCancelButtonText() {
		addInvoice_Button.click();
		addInvoice_Panel = window.panel("AddInvoicePanel");
		cancelAdd_Button = addInvoice_Panel.button("CancelButton");
		cancelAdd_Button.requireText("Cancel");
	}
	
	@Test
	public void testCancelButtonAction() {
		addInvoice_Button.click();
		addInvoice_Panel = window.panel("AddInvoicetPanel");
		cancelAdd_Button = addInvoice_Panel.button("CancelButton");
		cancelAdd_Button.click();
		addInvoice_Panel.requireNotVisible();
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
	
	/*
	@Test
	public void testSaveButtonWithInputsAction() {
		getSaveButton();
		setTextfieldsStrings("0","1","2","");
		saveAdd_Button.click();
		addInvoice_Panel.requireNotVisible();
	}
	*/
	
	@Test
	public void testSaveButtonWithWrongInputsAction() {
		getSaveButton();
		setTextfieldsStrings("0","1","2","");
		saveAdd_Button.requireDisabled();
	}

	private void getSaveButton() {
		addInvoice_Button.click();
		addInvoice_Panel = window.panel("AddInvoicePanel");
		saveAdd_Button = addInvoice_Panel.button("SaveButton");
	}
	
	
	
	private void setTextfieldsStrings(String string1, String string2, String string3, String string4) {
		
		addInvoice_Panel.textBox("invoiceID_TF").setText(string1);
		addInvoice_Panel.textBox("invoiceName_TF").setText(string2);
		addInvoice_Panel.textBox("invoicePrice_TF").setText(string3);
		addInvoice_Panel.textBox("invoiceDescription_TF").setText(string4);
				
	}
	
}