package com.unifi.fattureApp.databaseIT;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.DatabaseUiComunication;
import com.unifi.fattureApp.App.Invoice;

public abstract class AbstractDatabaseUiComunicationTest {
	protected DatabaseUiComunication myDatabaseUiComunication;

	public abstract void setUpDatabase() throws UnknownHostException;

	@Before
	public void setUp() throws UnknownHostException {
		setUpDatabase();
	}

	@Test
	public void addClientToDbTest() {
		assertEquals(true, addTestClient());
	}

	@Test
	public void test2() {

	}

	@Test
	public void clientsCountWithNoClientsTest() {
		assertEquals(0, myDatabaseUiComunication.getClientsCount());
	}

	@Test
	public void clientsCountWithOneClientTest() {
		addTestClient();
		assertEquals(1, myDatabaseUiComunication.getClientsCount());
	}

	@Test
	public void testgetListSizeOfClientsWithNoClients() {
		assertEquals(0, myDatabaseUiComunication.getSavedClients().size());
	}

	@Test
	public void testgetListSizeOfClientsWithOneClient() {
		addTestClient();
		assertEquals(1, myDatabaseUiComunication.getSavedClients().size());
	}

	@Test
	public void testgetListOfClientsWithOneClientCheckName() {
		addTestClient();
		assertEquals("name", myDatabaseUiComunication.getSavedClients().get(0).getName());
	}


	@Test 
	public void testgetCurrentSelectedClientWithNoClientSelected() {
		assertEquals(null, myDatabaseUiComunication.getCurrentSelectedClient());
	}

	@Test 
	public void testgetCurrentSelectedClientWithClientSelected() {
		Client client = setTestClientToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient(), client);
	}

	@Test
	public void getCurrentSelectedClientWithClientSelectedNameTest() {
		Client client = setTestClientToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient().getName(), client.getName());
	}

	private boolean addTestClient() {
		return myDatabaseUiComunication.addClientToDatabase("name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
	}

	private Client setTestClientToCurrentSelected() {
		Client client = new Client("1", "name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myDatabaseUiComunication.setCurrentSelectedClient(client);
		return client;
	}


	//Company



	@Test 
	public void getCurrentSelectedCompanyWithNoCompanySelected() {
		assertEquals(null, myDatabaseUiComunication.getCurrentSelectedCompany());
	}

	@Test 
	public void getCurrentSelectedCompanyWithCompanySelected() {
		Company company = setTestCompanyToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedCompany(), company);
	}

	private Company setTestCompanyToCurrentSelected() {
		Company company = new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myDatabaseUiComunication.setCurrentSelectedCompany(company);
		return company;
	}

	@Test 
	public void getCurrentSelectedCompanyWithCompanySelectedNameTest() {
		Company company = setTestCompanyToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedCompany().getName(), company.getName());
	}

	@Test (expected = NullPointerException.class)
	public void editCompanyWhenNoCompanyInDBTest() {
		myDatabaseUiComunication.editCompanyFromDatabase("nameEdited", "vatCodeEdited", "addressEdited", "cityEdited", "provinceEdited", "zipCodeEdited", "countryEdited", "phoneEdited", "emailEdited");
	}

	@Test
	public void addCompanyToDbTest() {
		assertEquals(true, addTestCompany());
	}

	@Test
	public void companiesCountWithNoCompaniesTest() {
		assertEquals(0, myDatabaseUiComunication.getCompaniesCount());
	}

	@Test
	public void companiesCountWithOneCompanyTest() {
		addTestCompany();
		assertEquals(1, myDatabaseUiComunication.getCompaniesCount());
	}

	@Test
	public void testgetListSizeOfCompaniesWithNoCompanies() {
		assertEquals(0, myDatabaseUiComunication.getSavedCompanies().size());
	}

	@Test
	public void testgetListSizeOfCompaniesWithOneCompany() {
		addTestCompany();
		assertEquals(1, myDatabaseUiComunication.getSavedCompanies().size());
	}

	@Test
	public void testgetListOfCompaniesWithOneCompanyCheckName() {
		addTestCompany();
		assertEquals("name", myDatabaseUiComunication.getSavedCompanies().get(0).getName());
	}

	private boolean addTestCompany() {
		return myDatabaseUiComunication.addCompanyToDatabase("name", "vatCode", "address", "city", "province", "zip", "country", "phone", "email");
	}


	//Invoice
	@Test 
	public void getCurrentSelectedInvoiceWithNoInvoiceSelected() {
		assertEquals(null, myDatabaseUiComunication.getCurrentSelectedInvoice());
	}

	@Test 
	public void getCurrentSelectedInvoiceWithInvoiceSelected() {
		Invoice invoice = setTestInvoiceToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedInvoice(), invoice);
	}

	private Invoice setTestInvoiceToCurrentSelected() {
		Invoice invoice = new Invoice("1", "invoiceName", "15", "invoiceDescription");
		myDatabaseUiComunication.setCurrentSelectedInvoice(invoice);
		return invoice;
	}

	@Test 
	public void getCurrentSelectedInvoiceWithInvoiceSelectedNameTest() {
		Invoice invoice = setTestInvoiceToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedInvoice().getName(), invoice.getName());
	}

	@Test
	public void addInvoiceToDbTest() {
		assertEquals(true, addTestInvoice());
	}

	@Test
	public void invoicesCountWithNoInvoicesTest() {
		assertEquals(0, myDatabaseUiComunication.getInvoicesCount());
	}

	@Test
	public void invoicesCountWithOneInvoiceTest() {
		addTestInvoice();
		assertEquals(1, myDatabaseUiComunication.getInvoicesCount());
	}

	@Test
	public void testgetListSizeOfInvoicesWithNoInvoices() {
		assertEquals(0, myDatabaseUiComunication.getSavedInvoices().size());
	}

	@Test
	public void testgetListSizeOfInvoicesWithOneInvoice() {
		addTestInvoice();
		assertEquals(1, myDatabaseUiComunication.getSavedInvoices().size());
	}

	@Test
	public void testgetListOfInvoicesWithOneInvoiceCheckName() {
		addTestInvoice();
		assertEquals("name", myDatabaseUiComunication.getSavedInvoices().get(0).getName());
	}

	private boolean addTestInvoice() {
		return myDatabaseUiComunication.addInvoiceToDatabase("name", "description", "10");
	}

	@Test 
	public void printSelectedWithNoSelectedTest() throws Exception {
		assertEquals(false, myDatabaseUiComunication.printSelected());
	}

	@Test
	public void printSelectedWithSelectedTest() throws Exception {
		setTestCompanyToCurrentSelected();
		setTestClientToCurrentSelected();
		setTestInvoiceToCurrentSelected();
		boolean created = myDatabaseUiComunication.printSelected();
		assertEquals(true, created);
		if(created) {
			new File("Invoice.pdf").delete();
		}
	}	
}
