package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.RedisWrapper;

public abstract class RedisWrapperTestAbstract {
	private RedisWrapper redisDatabase;
	public abstract void createRedis() throws UnknownHostException;
	public abstract void stopRedis() throws UnknownHostException;
	private TestHelperTool redisTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		createRedis();
		redisTestHelper = new TestHelperTool();
		redisDatabase = redisTestHelper.usingRedis();
	}

	//Client
	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(redisDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		redisTestHelper.addTwoClients();
		assertEquals(2, redisDatabase.getAllClientsList().size());
	}

	@Test
	public void testFindClientByIdNotFound() {
		redisTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");

		assertNull(redisDatabase.findClientById("2"));
	}

	@Test
	public void testClientIsSaved() {
		redisDatabase.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertTrue(redisTestHelper.containsClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
	}

	@Test
	public void testClientIsNotSaved() {
		redisDatabase.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertFalse(redisTestHelper.containsClient("2", "test2", "testFC2", "testCR2", "testCity2", "testProvince2", "testZip2", "testCountry2", "testPhone2", "testEmail2"));
	}

	@Test
	public void testFindClientByIdFound() {
		redisTestHelper.addTwoClients();
		Client findClientById = redisDatabase.findClientById("2");
		assertNotNull(findClientById);
		assertEquals("2", findClientById.getId());
		assertEquals("second", findClientById.getName());
	}

	@Test 
	public void testRemoveClientByIdFromDB() {
		redisTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		redisDatabase.removeClientById("1");
		assertEquals(0, redisDatabase.getAllClientsList().size());
	}

	@Test 
	public void testRemoveClientByIdFromDBWithMoreClients() {
		redisTestHelper.addTwoClients();
		redisDatabase.removeClientById("2");
		assertTrue(redisTestHelper.containsClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail"));
	}	

	//Company
	@Test
	public void testGetAllCompaniesEmpty() {
		assertTrue(redisDatabase.getAllCompaniesList().isEmpty());
	}

	@Test
	public void testGetAllCompaniesNotEmpty() {
		redisTestHelper.addTwoCompanies();
		assertEquals(2, redisDatabase.getAllCompaniesList().size());
	}

	@Test
	public void testFindCompanyByIdNotFound() {
		redisTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		assertNull(redisDatabase.findCompanyById("2"));
	}

	@Test
	public void testCompanyIsSaved() {
		redisDatabase.saveCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		assertTrue(redisTestHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}

	@Test
	public void testFindCompanyByIdFound() {
		redisTestHelper.addTwoCompanies();
		Company findCompanyById = redisDatabase.findCompanyById("2");
		assertNotNull(findCompanyById);
		assertEquals("2", findCompanyById.getId());
		assertEquals("nameC2", findCompanyById.getName());
	}	

	@Test 
	public void testRemoveCompanyByIdFromDB() {
		redisTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		redisDatabase.removeCompanyById("1");
		assertEquals(0, redisDatabase.getAllCompaniesList().size());
	}

	@Test 
	public void testRemoveCompanyByIdFromDBWithMoreCompanies() {
		redisTestHelper.addTwoCompanies();
		redisDatabase.removeCompanyById("2");
		assertTrue(redisTestHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}

	//Invoice
	@Test
	public void testGetAllInvoicesEmpty() {
		assertTrue(redisDatabase.getAllInvoicesList().isEmpty());
	}

	@Test
	public void testGetAllInvoicesNotEmpty() {
		redisTestHelper.addTwoInvoices();
		assertEquals(2, redisDatabase.getAllInvoicesList().size());
	}


	@Test
	public void testFindInvoiceByIdNotFound() {
		redisTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		assertNull(redisDatabase.findInvoiceById("2"));
	}

	@Test
	public void testInvoiceIsSaved() {
		redisDatabase.saveInvoice(new Invoice("1", "nameI1", "100", "basic invoice type1"));
		assertTrue(redisTestHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}

	@Test
	public void testFindInvoiceByIdFound() {
		redisTestHelper.addTwoInvoices();
		Invoice findInvoiceById = redisDatabase.findInvoiceById("2");
		assertNotNull(findInvoiceById);
		assertEquals("2", findInvoiceById.getId());
		assertEquals("nameI2", findInvoiceById.getName());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDB() {
		redisTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		redisDatabase.removeInvoiceById("1");
		assertEquals(0, redisDatabase.getAllInvoicesList().size());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDBWithMoreInvoices() {
		redisTestHelper.addTwoInvoices();
		redisDatabase.removeInvoiceById("2");
		assertTrue(redisTestHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}

	@After
	public void stopDBServer() {
		try {
			stopRedis();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}