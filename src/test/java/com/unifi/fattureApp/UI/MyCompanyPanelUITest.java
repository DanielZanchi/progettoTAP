/**
 * May 20, 2017
 * @author Danny
 *
 */
package com.unifi.fattureApp.UI;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyCompanyPanelUITest {
	private FrameFixture window;
	private JPanelFixture myCompany_Panel;
	private JPanelFixture addCompany_Panel;
	private JButtonFixture addCompany_Button;
	private JButtonFixture cancelAdd_Button;
	private JButtonFixture saveAdd_Button;

	@Before
	public void setUp() {
		//MainWindowUI frame = GuiActionRunner.execute(() -> new MainWindowUI());
		MainWindowUI frame = new MainWindowUI();
		window = new FrameFixture(frame.getMainFrame());
		window.show();
		myCompany_Panel = window.panel("CompanyPanel");
		addCompany_Button = window.panel("CompanyPanel").button("AddCompanyButton");
		
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void testCompanyPanelBackground() {
		myCompany_Panel.background().requireEqualTo(new java.awt.Color(226, 244, 252));
	}
	
	@Test
	public void testAddButtonText() {
		addCompany_Button.requireText("Add");
	}
	
	@Test
	public void testAddButtonAction() {		
		showAddCompanyPanel();
		addCompany_Panel.requireVisible();
	}
	
	@Test
	public void testCancelButtonText() {
		showAddCompanyPanel();
		cancelAdd_Button = addCompany_Panel.button("CancelButton");
		cancelAdd_Button.requireText("Cancel");
	}
	
	@Test
	public void testCancelButtonAction() {
		showAddCompanyPanel();
		cancelAdd_Button = addCompany_Panel.button("CancelButton");
		cancelAdd_Button.click();
		addCompany_Panel.requireNotVisible();
	}
	
	@Test
	public void testSaveButtonText() {
		showAddCompanyPanel();
		saveAdd_Button = addCompany_Panel.button("SaveButton");
		saveAdd_Button.requireText("Save");
	}
	
	@Test
	public void testSaveButtonAction() {
		saveCompany();
		saveAdd_Button.requireDisabled();
	}
	
	@Test 
	public void testSaveResetNameTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		JTextComponentFixture companyName = addCompany_Panel.textBox("companyNameTextField");
		companyName.text().compareTo("");
	}
	
	@Test 
	public void testCancelResetNameTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		JTextComponentFixture companyName = addCompany_Panel.textBox("companyNameTextField");
		companyName.text().compareTo("");
	}
	
	@Test 
	public void testSaveResetVatTextField(){
		saveCompany();
		showAddCompanyPanel();
		
		JTextComponentFixture companyVat = addCompany_Panel.textBox("companyVatTextField");
		companyVat.text().isEmpty();	
	}
	
	@Test 
	public void testCancelResetVatTextField(){
		cancelCompany();
		showAddCompanyPanel();
		
		JTextComponentFixture companyVat = addCompany_Panel.textBox("companyVatTextField");
		companyVat.text().isEmpty();	
	}
	
	@Test 
	public void testSaveResetAddressTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyAddress = addCompany_Panel.textBox("companyAddressTextField");
		companyAddress.text().isEmpty();	
	}
	
	@Test 
	public void testCancelResetAddressTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyAddress = addCompany_Panel.textBox("companyAddressTextField");
		companyAddress.text().isEmpty();	
	}
	
	@Test 
	public void testSaveResetCityTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyCity = addCompany_Panel.textBox("companyCityTextField");
		companyCity.text().isEmpty();
	}
	
	@Test 
	public void testCancelResetCityTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyCity = addCompany_Panel.textBox("companyCityTextField");
		companyCity.text().isEmpty();
	}
	
	@Test 
	public void testSaveResetProvinceTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyProvince = addCompany_Panel.textBox("companyProvinceTextField");
		companyProvince.text().isEmpty();
	}
	
	@Test 
	public void testCancelResetProvinceTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyProvince = addCompany_Panel.textBox("companyProvinceTextField");
		companyProvince.text().isEmpty();
	}
	
	@Test 
	public void testSaveResetZipTextField(){
		saveCompany();
		
		showAddCompanyPanel();
	
		JTextComponentFixture companyZip= addCompany_Panel.textBox("companyZipTextField");
		companyZip.text().isEmpty();
	}
	
	@Test 
	public void testCancelResetZipTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
	
		JTextComponentFixture companyZip= addCompany_Panel.textBox("companyZipTextField");
		companyZip.text().isEmpty();
	}
	
	@Test 
	public void testSaveResetCountryTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyCountry = addCompany_Panel.textBox("companyCountryTextField");
		companyCountry.text().isEmpty();
	}
	
	@Test 
	public void testCancelResetCountryTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyCountry = addCompany_Panel.textBox("companyCountryTextField");
		companyCountry.text().isEmpty();
	}
	
	@Test 
	public void testSaveResetPhoneTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyPhone = addCompany_Panel.textBox("companyPhoneTextField");
		companyPhone.text().compareTo("");
	}
	
	@Test 
	public void testCancelResetPhoneTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyPhone = addCompany_Panel.textBox("companyPhoneTextField");
		companyPhone.text().compareTo("");
	}
	
	@Test 
	public void testSaveResetEmailTextField(){
		saveCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyEmail = addCompany_Panel.textBox("companyEmailTextField");
		companyEmail.text().compareTo("");
	}
	
	@Test 
	public void testCancelResetEmailTextField(){
		cancelCompany();
		
		showAddCompanyPanel();
		
		JTextComponentFixture companyEmail = addCompany_Panel.textBox("companyEmailTextField");
		companyEmail.text().compareTo("");
	}
	
	
	
	
	private void showAddCompanyPanel() {
		addCompany_Button.click();
		addCompany_Panel = window.panel("AddCompanyPanel");
	}

	private void saveCompany() {
		showAddCompanyPanel();
		saveAdd_Button = addCompany_Panel.button("SaveButton");
		saveAdd_Button.click();
	}
	
	private void cancelCompany(){
		showAddCompanyPanel();
		cancelAdd_Button = addCompany_Panel.button("CancelButton");
		cancelAdd_Button.click();
	}
}