package com.unifi.fattureApp.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;

public abstract class AbstractWrapperTest {
	protected Database database;
	public abstract void init() throws UnknownHostException;
	protected TestHelperTool testHelper;

	@Before
	public void initDB() throws UnknownHostException {
		init();
	}

	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(database.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		testHelper.addTwoClients();
		assertEquals(2, database.getAllClientsList().size());
	}

	@Test
	public void testFindClientByIdNotFound() {
		testHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		assertNull(database.findClientById("2"));
	}

	@Test
	public void testClientIsSaved() {
		database.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertTrue(testHelper.containsClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
	}

	@Test
	public void testClientIsNotSaved() {
		database.saveClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertFalse(testHelper.containsClient("2", "test2", "testFC2", "testCR2", "testCity2", "testProvince2", "testZip2", "testCountry2", "testPhone2", "testEmail2"));
	}

	@Test
	public void testFindClientByIdFound() {
		testHelper.addTwoClients();
		Client findClientById = database.findClientById("2");
		assertNotNull(findClientById);
		assertEquals("2", findClientById.getId());
		assertEquals("second", findClientById.getName());
	}

	@Test 
	public void testRemoveClientByIdFromDB() {
		testHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		database.removeClientById("1");
		assertEquals(0, database.getAllClientsList().size());
	}

	@Test 
	public void testRemoveClientByIdFromDBWithMoreClients() {
		testHelper.addTwoClients();
		database.removeClientById("2");
		assertTrue(testHelper.containsClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail"));
	}	

	//Company
	@Test
	public void testGetAllCompaniesEmpty() {
		assertTrue(database.getAllCompaniesList().isEmpty());
	}

	@Test
	public void testGetAllCompaniesNotEmpty() {
		testHelper.addTwoCompanies();
		assertEquals(2, database.getAllCompaniesList().size());
	}

	@Test
	public void testFindCompanyByIdNotFound() {
		testHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		assertNull(database.findCompanyById("2"));
	}

	@Test
	public void testCompanyIsSaved() {
		database.saveCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		assertTrue(testHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}

	@Test
	public void testFindCompanyByIdFound() {
		testHelper.addTwoCompanies();
		Company findCompanyById = database.findCompanyById("2");
		assertNotNull(findCompanyById);
		assertEquals("2", findCompanyById.getId());
		assertEquals("nameC2", findCompanyById.getName());
	}	

	@Test 
	public void testRemoveCompanyByIdFromDB() {
		testHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		database.removeCompanyById("1");
		assertEquals(0, database.getAllCompaniesList().size());
	}

	@Test 
	public void testRemoveCompanyByIdFromDBWithMoreCompanies() {
		testHelper.addTwoCompanies();
		database.removeCompanyById("2");
		assertTrue(testHelper.containsCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
	}	

	//Invoice
	@Test
	public void testGetAllInvoicesEmpty() {
		assertTrue(database.getAllInvoicesList().isEmpty());
	}

	@Test
	public void testGetAllInvoicesNotEmpty() {
		testHelper.addTwoInvoices();
		assertEquals(2, database.getAllInvoicesList().size());
	}

	@Test
	public void testFindInvoiceByIdNotFound() {
		testHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		assertNull(database.findInvoiceById("2"));
	}

	@Test
	public void testInvoiceIsSaved() {
		database.saveInvoice(new Invoice("1", "nameI1", "100", "basic invoice type1"));
		assertTrue(testHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}

	@Test
	public void testFindInvoiceByIdFound() {
		testHelper.addTwoInvoices();
		Invoice findInvoiceById = database.findInvoiceById("2");
		assertNotNull(findInvoiceById);
		assertEquals("2", findInvoiceById.getId());
		assertEquals("nameI2", findInvoiceById.getName());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDB() {
		testHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		database.removeInvoiceById("1");
		assertEquals(0, database.getAllInvoicesList().size());
	}

	@Test 
	public void testRemoveInvoiceByIdFromDBWithMoreInvoices() {
		testHelper.addTwoInvoices();
		database.removeInvoiceById("2");
		assertTrue(testHelper.containsInvoice("1", "nameI1", "100", "basic invoice type1"));
	}	
}
