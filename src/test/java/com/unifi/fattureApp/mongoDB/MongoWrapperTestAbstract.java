package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public abstract class MongoWrapperTestAbstract { 
	private MongoWrapper mongoDatabase;
	public abstract MongoClient createMongoClient() throws UnknownHostException;
	private TestHelperTool mongoTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		MongoClient mongoClient = createMongoClient();
		mongoTestHelper = new TestHelperTool();
		mongoTestHelper.setUpMongoClient(mongoClient);
		mongoDatabase = new MongoWrapper(mongoClient);
	}

	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(mongoDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		mongoTestHelper.addTwoClient();
		assertEquals(2, mongoDatabase.getAllClientsList().size());
	}

	@Test
	public void testFindClientByIdNotFound() {
		mongoTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");

		assertNull(mongoDatabase.findClientById("2"));
	}

	@Test
	public void testClientIsSaved() {
		mongoDatabase.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertTrue(mongoTestHelper.containsClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
	}

	@Test
	public void testClientIsNotSaved() {
		mongoDatabase.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertFalse(mongoTestHelper.containsClient("2", "test2", "testFC2", "testCR2", "testCity2", "testProvince2", "testZip2", "testCountry2", "testPhone2", "testEmail2"));
	}

	@Test
	public void testFindClientByIdFound() {
		mongoTestHelper.addTwoClient();
		Client findClientById = mongoDatabase.findClientById("2");
		assertNotNull(findClientById);
		assertEquals("2", findClientById.getId());
		assertEquals("second", findClientById.getName());
	}

	@Test 
	public void testRemoveClientByIdFromDB() {
		mongoTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		mongoDatabase.removeClientById("1");
		assertEquals(0, mongoDatabase.getAllClientsList().size());
	}

	@Test 
	public void testRemoveClientByIdFromDBWithMoreClients() {
		mongoTestHelper.addTwoClient();
		mongoDatabase.removeClientById("2");
		assertTrue(mongoTestHelper.containsClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail"));
	}	

	//Company
	@Test
	public void testGetAllCompaniesEmpty() {
		assertTrue(mongoDatabase.getAllCompaniesList().isEmpty());
	}

	@Test
	public void testGetAllCompaniesNotEmpty() {
		mongoTestHelper.addTwoCompany();
		assertEquals(2, mongoDatabase.getAllCompaniesList().size());
	}

	@Test
	public void testFindCompanyByIdNotFound() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		assertNull(mongoDatabase.findCompanyById("2"));
	}

	@Test
	public void testCompanyIsSaved() {
		mongoDatabase.saveCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		assertTrue(mongoTestHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}

	@Test
	public void testFindCompanyByIdFound() {
		mongoTestHelper.addTwoCompany();
		Company findCompanyById = mongoDatabase.findCompanyById("2");
		assertNotNull(findCompanyById);
		assertEquals("2", findCompanyById.getId());
		assertEquals("nameC2", findCompanyById.getName());
	}	

	@Test 
	public void testRemoveCompanyByIdFromDB() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		mongoDatabase.removeCompanyById("1");
		assertEquals(0, mongoDatabase.getAllCompaniesList().size());
	}

	@Test 
	public void testRemoveCompanyByIdFromDBWithMoreCompanies() {
		mongoTestHelper.addTwoCompany();
		mongoDatabase.removeCompanyById("2");
		assertTrue(mongoTestHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}	

	//Invoice
	@Test
	public void testGetAllInvoicesEmpty() {
		assertTrue(mongoDatabase.getAllInvoicesList().isEmpty());
	}

	@Test
	public void testGetAllInvoicesNotEmpty() {
		mongoTestHelper.addTwoInvoice();
		assertEquals(2, mongoDatabase.getAllInvoicesList().size());
	}

	@Test
	public void testFindInvoiceByIdNotFound() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		assertNull(mongoDatabase.findInvoiceById("2"));
	}

	@Test
	public void testInvoiceIsSaved() {
		mongoDatabase.saveInvoice(new Invoice("1", "nameI1", "100", "basic invoice type1"));
		assertTrue(mongoTestHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}

	@Test
	public void testFindInvoiceByIdFound() {
		mongoTestHelper.addTwoInvoice();
		Invoice findInvoiceById = mongoDatabase.findInvoiceById("2");
		assertNotNull(findInvoiceById);
		assertEquals("2", findInvoiceById.getId());
		assertEquals("nameI2", findInvoiceById.getName());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDB() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		mongoDatabase.removeInvoiceById("1");
		assertEquals(0, mongoDatabase.getAllInvoicesList().size());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDBWithMoreInvoices() {
		mongoTestHelper.addTwoInvoice();
		mongoDatabase.removeInvoiceById("2");
		assertTrue(mongoTestHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}	
}