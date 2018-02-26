package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.App.DatabaseUiComunication;

public class MongoUiComunicationTest {	
	private DatabaseUiComunication myDatabaseUiComunication;

	@Before
	public void setUp() throws IOException {
		String args[] = null;
		myDatabaseUiComunication = new DatabaseUiComunication(true, args, true);
	}

	@Test
	public void addClientToDbTest() {
		assertEquals(true, addTestClient());
	}

	private boolean addTestClient() {
		return myDatabaseUiComunication.addClientToDatabase("name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
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
	public void getListSizeOfClientsWithNoClients() {
		assertEquals(0, myDatabaseUiComunication.getSavedClients().size());
	}

	@Test
	public void getListSizeOfClientsWithOneClient() {
		addTestClient();
		assertEquals(1, myDatabaseUiComunication.getSavedClients().size());
	}

	@Test
	public void getListOfClientsWithOneClientCheckName() {
		addTestClient();
		assertEquals("name", myDatabaseUiComunication.getSavedClients().get(0).getName());
	}

	@Test 
	public void getCurrentSelectedClientWithNoClientSelected() {
		assertEquals(null, myDatabaseUiComunication.getCurrentSelectedClient());
	}

	@Test 
	public void getCurrentSelectedClientWithClientSelected() {
		Client client = setTestClientToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient(), client);
	}

	private Client setTestClientToCurrentSelected() {
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myDatabaseUiComunication.setCurrentSelectedClient(client);
		return client;
	}

	@Test 
	public void getCurrentSelectedClientWithClientSelectedNameTest() {
		Client client = setTestClientToCurrentSelected();
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient().getName(), client.getName());
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
	public void printSelectedWithNoSelectedTest() {
		assertEquals(false, myDatabaseUiComunication.printSelected());
	}

	@Test 
	public void printSelectedWithSelectedTest() {
		Company company = setTestCompanyToCurrentSelected();
		Client client = setTestClientToCurrentSelected();
		Invoice invoice = setTestInvoiceToCurrentSelected();
		assertEquals(true, myDatabaseUiComunication.printSelected());
	}
}