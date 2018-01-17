package com.unifi.fattureApp.UI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JComboBoxFixture;
import org.assertj.swing.fixture.JLabelFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class MainWindowUITest {
	private FrameFixture window;

	private JButtonFixture addCompanyButton;
	private JPanelFixture addCompanyPanel;

	private JPanelFixture clientPanel;	
	private JButtonFixture addClient_Button;

	@Before
	public void setUp() throws IOException {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame = new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();

		addCompanyButton = window.panel("CompanyPanel").button("AddCompanyButton");
		addClient_Button = window.panel("ClientPanel").button("AddClientButton");
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void testApplicationName() {
		window.requireTitle("Fatture App");
	}

	@Test
	public void testWindowIsEnable() {
		window.requireVisible();
	}

	@Test
	public void testWindowIsVisible() {
		window.isEnabled();
	}

	@Test
	public void testWindowBckgColor() {
		window.background().requireEqualTo(java.awt.Color.LIGHT_GRAY);
	}

	//Company panel
	@Test
	public void testAddButtonText() {
		addCompanyButton.requireText("Add");
	}

	@Test 
	public void testAddButtonAction() {
		showAddCompanyPanel();
		addCompanyPanel.requireVisible();
	}

	@Test
	public void testEditButtonWithNoCompanySelected() {
		JButtonFixture editButton = window.button("EditCompanyButton");
		JLabelFixture currentCompanySelected = window.label("currentSelectedCompanyLabel");
		assertEquals("My Company", currentCompanySelected.text());
		editButton.requireDisabled();
	}

	//Client panel
	@Test 
	public void testEditButtonWithNoClientSelected() {
		JComboBoxFixture clientComboBox = window.comboBox("clientsComboBox");
		assertEquals(null, clientComboBox.selectedItem());
		JButtonFixture editClientButton=window.button("editClientButton");
		editClientButton.requireDisabled();
	}

	@Test
	public void testClientsComboBoxWithNoClients(){
		JComboBoxFixture clientComboBox = window.comboBox("clientsComboBox");
		clientComboBox.requireItemCount(0);
	}

	//Invoice panel
	@Test 
	public void testEditButtonWithNoInvoiceSelected() {
		JComboBoxFixture invoiceComboBox = window.comboBox("invoicesComboBox");
		assertEquals(null, invoiceComboBox.selectedItem());
		JButtonFixture editInvoiceButton=window.button("editInvoiceButton");
		editInvoiceButton.requireDisabled();
	}

	@Test
	public void testInvoicesComboBoxWithNoInvoices(){
		JComboBoxFixture invoiceComboBox = window.comboBox("invoicesComboBox");
		invoiceComboBox.requireItemCount(0);
	}

	private void showAddCompanyPanel() {
		addCompanyButton.click();
		addCompanyPanel = window.panel("AddCompanyPanel");
	}
}