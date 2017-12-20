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
		myMongoUiCom=new MongoUiComunication(true, args);
	}
	
	
	@Test
	public void addClientToDbTest() {
		addTestClient();
	}
	
	private void addTestClient() {
		boolean added=myMongoUiCom.addClientToDatabase("name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		assertEquals(added, true);
	}
	
	@Test
	public void clientsCountWithNoClientsTest() {
		assertEquals(myMongoUiCom.getClientsCount(),0);
	}
	
	@Test
	public void clientsCountWithOneClientTest() {
		addTestClient();
		assertEquals(myMongoUiCom.getClientsCount(),1);
	}
	
	
	@Test
	public void getListSizeOfClientsWithNoClients() {
		assertEquals(myMongoUiCom.getSavedClients().size(), 0);
	}
	
	@Test
	public void getListSizeOfClientsWithOneClient() {
		addTestClient();
		assertEquals(myMongoUiCom.getSavedClients().size(), 1);
	}
	
	@Test
	public void getListOfClientsWithOneClientCheckName() {
		addTestClient();
		assertEquals(myMongoUiCom.getSavedClients().get(0).getName(), "name");
	}
	
	@Test 
	public void getCurrentSelectedClientWithNoClientSelected() {
		assertEquals(myMongoUiCom.getCurrentSelectedClient(), null);
	}
	
	@Test 
	public void getCurrentSelectedClientWithClientSelected() {
		Client client=new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		assertEquals(myMongoUiCom.getCurrentSelectedClient(), client);
	}
	
	@Test 
	public void getCurrentSelectedClientWithClientSelectedNameTest() {
		Client client=new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		assertEquals(myMongoUiCom.getCurrentSelectedClient().getName(), client.getName());
	}
	
	
	@Test 
	public void printSelectedWithNoSelectedTest() {
		assertEquals(myMongoUiCom.printSelected(), false);
	}
	
	@Test 
	public void printSelectedWithSelectedTest() {
		Company company=new Company("1", "nameComp", "vatCodeComp", "addressComp", "cityComp", "provinceComp", "zipCodeComp", "countryComp", "phoneComp", "emailComp");
		myMongoUiCom.setCurrentSelectedCompany(company);
		Client client=new Client("1","name", "fiscalCode", "residence", "city", "province", "zip", "country", "phone", "email");
		myMongoUiCom.setCurrentSelectedClient(client);
		Invoice invoice=new Invoice("1","invoiceName","15","invoiceDescription");
		myMongoUiCom.setCurrentSelectedInvoice(invoice);
		assertEquals(myMongoUiCom.printSelected(), true);
	}

}
