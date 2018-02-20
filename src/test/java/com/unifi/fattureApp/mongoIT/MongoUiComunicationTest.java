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
		addTestClient();
	}

	private void addTestClient() {
		boolean added = myDatabaseUiComunication.addClientToDatabase("name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		assertEquals(true, added);
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
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myDatabaseUiComunication.setCurrentSelectedClient(client);
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient(), client);
	}

	@Test 
	public void getCurrentSelectedClientWithClientSelectedNameTest() {
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myDatabaseUiComunication.setCurrentSelectedClient(client);
		assertEquals(myDatabaseUiComunication.getCurrentSelectedClient().getName(), client.getName());
	}

	//Company
	@Test 
	public void getCurrentSelectedCompanyWithNoCompanySelected() {
		assertEquals(null, myDatabaseUiComunication.getCurrentSelectedCompany());
	}

	@Test 
	public void getCurrentSelectedCompanyWithCompanySelected() {
		Company company = new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myDatabaseUiComunication.setCurrentSelectedCompany(company);
		assertEquals(myDatabaseUiComunication.getCurrentSelectedCompany(), company);
	}

	@Test 
	public void getCurrentSelectedCompanyWithCompanySelectedNameTest() {
		Company company = new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myDatabaseUiComunication.setCurrentSelectedCompany(company);
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
		Invoice invoice = new Invoice("1", "invoiceName", "15", "invoiceDescription");
		myDatabaseUiComunication.setCurrentSelectedInvoice(invoice);
		assertEquals(myDatabaseUiComunication.getCurrentSelectedInvoice(), invoice);
	}

	@Test 
	public void getCurrentSelectedInvoiceWithInvoiceSelectedNameTest() {
		Invoice invoice = new Invoice("1", "invoiceName", "15", "invoiceDescription");
		myDatabaseUiComunication.setCurrentSelectedInvoice(invoice);
		assertEquals(myDatabaseUiComunication.getCurrentSelectedInvoice().getName(), invoice.getName());
	}

	@Test 
	public void printSelectedWithNoSelectedTest() {
		assertEquals(false, myDatabaseUiComunication.printSelected());
	}

	@Test 
	public void printSelectedWithSelectedTest() {
		Company company = new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myDatabaseUiComunication.setCurrentSelectedCompany(company);
		Client client = new Client("1", "name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myDatabaseUiComunication.setCurrentSelectedClient(client);
		Invoice invoice = new Invoice("1", "invoiceName", "15", "invoiceDescription");
		myDatabaseUiComunication.setCurrentSelectedInvoice(invoice);
		assertEquals(true, myDatabaseUiComunication.printSelected());
	}
}