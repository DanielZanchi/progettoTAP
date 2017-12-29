package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.App.MongoUiComunication;

public class MongoUiComunicationTest {	
	private MongoUiComunication myMongoUiCom;

	@Before
	public void setUp() {
		String args[] = null;
		myMongoUiCom = new MongoUiComunication(true, args);
	}

	@Test
	public void addClientToDbTest() {
		addTestClient();
	}

	private void addTestClient() {
		boolean added = myMongoUiCom.addClientToDatabase("name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		assertEquals(true, added);
	}

	@Test
	public void clientsCountWithNoClientsTest() {
		assertEquals(0, myMongoUiCom.getClientsCount());
	}

	@Test
	public void clientsCountWithOneClientTest() {
		addTestClient();
		assertEquals(1, myMongoUiCom.getClientsCount());
	}

	@Test
	public void getListSizeOfClientsWithNoClients() {
		assertEquals(0, myMongoUiCom.getSavedClients().size());
	}

	@Test
	public void getListSizeOfClientsWithOneClient() {
		addTestClient();
		assertEquals(1, myMongoUiCom.getSavedClients().size());
	}

	@Test
	public void getListOfClientsWithOneClientCheckName() {
		addTestClient();
		assertEquals("name", myMongoUiCom.getSavedClients().get(0).getName());
	}

	@Test 
	public void getCurrentSelectedClientWithNoClientSelected() {
		assertEquals(null, myMongoUiCom.getCurrentSelectedClient());
	}

	@Test 
	public void getCurrentSelectedClientWithClientSelected() {
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		assertEquals(myMongoUiCom.getCurrentSelectedClient(), client);
	}

	@Test 
	public void getCurrentSelectedClientWithClientSelectedNameTest() {
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		assertEquals(myMongoUiCom.getCurrentSelectedClient().getName(), client.getName());
	}

	@Test 
	public void printSelectedWithNoSelectedTest() {
		assertEquals(false, myMongoUiCom.printSelected());
	}

	@Test 
	public void printSelectedWithSelectedTest() {
		Company company = new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myMongoUiCom.setCurrentSelectedCompany(company);
		Client client = new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		Invoice invoice = new Invoice("1","invoiceName","15","invoiceDescription");
		myMongoUiCom.setCurrentSelectedInvoice(invoice);
		assertEquals(true, myMongoUiCom.printSelected());
	}
}