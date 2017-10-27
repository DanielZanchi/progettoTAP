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

public class MainWindowUITest {
	private FrameFixture window;
	
	private JButtonFixture addCompanyButton;
	private JPanelFixture addCompanyPanel;
	
	private JPanelFixture clientPanel;	
	private JButtonFixture addClient_Button;

	
	@Before
	public void setUp() {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame=new MainWindowUI();
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
		JButtonFixture editButton=window.button("EditCompanyButton");
		JLabelFixture currentCompanySelected=window.label("currentSelectedCompanyLabel");
		assertEquals(currentCompanySelected.text(), "My Company");
		editButton.requireDisabled();
	}
	
	
	@Test 
	public void testEditButtonWithNoClientSelected() {
		JComboBoxFixture clientComboBox=window.comboBox("clientsComboBox");
		assertEquals(clientComboBox.selectedItem(),null);
	}
	
	@Test 
	public void testEditButtonWithNoInvoiceSelected() {
		JComboBoxFixture invoiceComboBox=window.comboBox("invoicesComboBox");
		assertEquals(invoiceComboBox.selectedItem(),null);
	}
	
	@Test
	public void testClientsComboBoxWithNoClients(){
		JComboBoxFixture clientComboBox=window.comboBox("clientsComboBox");
		clientComboBox.requireItemCount(0);
	}
	
	@Test
	public void testInvoicesComboBoxWithNoInvoices(){
		JComboBoxFixture invoiceComboBox=window.comboBox("invoicesComboBox");
		invoiceComboBox.requireItemCount(0);
	}
	
	
	//Client panel

	
	private void showAddCompanyPanel() {
		addCompanyButton.click();
		addCompanyPanel = window.panel("AddCompanyPanel");
	}
	
}