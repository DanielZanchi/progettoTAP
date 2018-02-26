package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.AppController;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import redis.embedded.RedisServer;

public class CompanyIntegrationRedisTest {

	private AppController companyController;
	private TestHelperTool mongoTestHelper;
	private RedisServer redisServer;

	@Before
	public void setUp() throws Exception {
		
		try {
			redisServer = new RedisServer(6379);
			redisServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mongoTestHelper = new TestHelperTool();
		Database database=mongoTestHelper.usingRedis();

		companyController = new AppController(database);
		
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.stop();
		
	}

	private Client addTestClientToDB() {
		mongoTestHelper.addClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		Client client = companyController.getClientId("1");
		assertNotNull(client);
		return client;
	}

	@Test
	public void addTestClientToDBWhenClientAlreadyInDB() {
		companyController.addClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		boolean added = companyController.addClient(new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail"));
		assertEquals(false, added);
	}

	@Test
	public void testGetAllClientsWhenThereAreNoClients() {
		List<Client> allClients = companyController.getAllClients();
		assertEquals(0, allClients.size());
	}

	@Test
	public void testGetAllClientsWhenThereIsOneClient() {
		mongoTestHelper.addClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		List<Client> allClients = companyController.getAllClients();
		assertEquals(1, allClients.size());	
	}

	@Test
	public void testGetClientByIdWhenClientIsNotThere() {
		mongoTestHelper.addClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		Client client = companyController.getClientId("2");
		assertNull(client);
	}

	@Test
	public void testGetClientByIdWhenClientIsThere() {
		Client client = addTestClientToDB();
		assertEquals("test", client.getName());
	}

	@Test
	public void testGetClientByIdWithRightFiscalCode() {
		Client client = addTestClientToDB();
		assertEquals("testFC", client.getFiscalCode());
	}

	@Test
	public void testGetClientByIdWithWrongFiscalCode() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestFC", client.getFiscalCode());
	}

	@Test
	public void testGetClientByIdWithRightCityResidence() {
		Client client = addTestClientToDB();
		assertEquals("testCR", client.getCityResidence());
	}

	@Test
	public void testGetClientByIdWithWrongCityResidence() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestCR", client.getCityResidence());
	}

	@Test
	public void testGetClientByIdWithRightPhone() {
		Client client = addTestClientToDB();
		assertEquals("testPhone", client.getPhone());
	}

	@Test
	public void testGetClientByIdWithWrongPhone() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestPhone", client.getPhone());
	}

	@Test
	public void testGetClientByIdWithRightEmail() {
		Client client = addTestClientToDB();
		assertEquals("testEmail", client.getEmail());
	}

	@Test
	public void testGetClientByIdWithWrongEmail() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestEmail", client.getEmail());
	}

	@Test
	public void testGetClientByIdWithRightCity() {
		Client client = addTestClientToDB();
		assertEquals("testCity", client.getCity());
	}

	@Test
	public void testGetClientByIdWithWrongCity() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestCity", client.getCity());
	}

	@Test
	public void testGetClientByIdWithRightProvince() {
		Client client = addTestClientToDB();
		assertEquals("testProvince", client.getProvince());
	}

	@Test
	public void testGetClientByIdWithWrongProvince() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestProvince", client.getProvince());
	}

	@Test
	public void testGetClientByIdWithRightZip() {
		Client client = addTestClientToDB();
		assertEquals("testZip", client.getZip());
	}

	@Test
	public void testGetClientByIdWithWrongZip() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestZip", client.getZip());
	}

	@Test
	public void testGetClientByIdWithRightCountry() {
		Client client = addTestClientToDB();
		assertEquals("testCountry", client.getCountry());
	}

	@Test
	public void testGetClientByIdWithWrongCountry() {
		Client client = addTestClientToDB();
		assertNotEquals("wrongTestCountry", client.getCountry());
	}

	@Test
	public void testEditClientWhileNoClientsInDB() {
		Client client = new Client("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		boolean edited = companyController.editClient(client);
		assertFalse(edited);
	}

	@Test
	public void testEditClientName() {
		Client client = addTestClientToDB();
		client.setName("EditedName");
		companyController.editClient(client);
		assertEquals("EditedName", companyController.getClientId("1").getName());
	}

	@Test
	public void testEditClientNameWithTwoClientsInDb() {
		Client client = addTestClientToDB();
		mongoTestHelper.addClient("2", "test2", "testFC2", "testCR2", "testCity2", "testProvince2", "testZip2", "testCountry2", "testPhone2", "testEmail2");
		client.setName("EditedName");
		companyController.editClient(client);
		assertEquals("EditedName", companyController.getClientId("1").getName());
	}

	//      company	
	private Company addTestCompanyToDB() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = companyController.getCompanyId("1");
		assertNotNull(company);
		return company;
	}	

	@Test
	public void addTestCompanyToDBWhenCompanyAlreadyInDB() {
		companyController.addCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		boolean added=companyController.addCompany(new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1"));
		assertEquals(false, added);
	}

	@Test
	public void testGetAllCompaniesWhenThereAreNoCompanies() {
		List<Company> allCompanies = companyController.getAllCompany();
		assertEquals(0, allCompanies.size());
	}

	@Test
	public void testGetAllCompaniesWhenThereIsOneCompany() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		List<Company> allCompanies = companyController.getAllCompany();
		assertEquals(1, allCompanies.size());	
	}

	@Test
	public void testGetCompanyByIdWhenCompanyIsNotThere() {
		mongoTestHelper.addCompany("1", "testName", "testVat", "testAddress", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		Company company = companyController.getCompanyId("2");
		assertNull(company);
	}

	@Test
	public void testGetCompanyByIdWhenCompanyIsThere() {
		Company company = addTestCompanyToDB();
		assertEquals("nameC1", company.getName());
	}

	@Test
	public void testGetCompanyByIdWithRightVatCode() {
		Company company = addTestCompanyToDB();
		assertEquals("vatCode1", company.getVatCode());
	}

	@Test
	public void testGetCompanyByIdWithWrongVatCode() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongVatCode", company.getVatCode());
	}

	@Test
	public void testGetCompanyByIdWithRightCity() {
		Company company = addTestCompanyToDB();
		assertEquals("city1", company.getCity());
	}

	@Test
	public void testGetCompanyByIdWithWrongCity() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongCity", company.getCity());
	}

	@Test
	public void testGetCompanyByIdWithRightAddress() {
		Company company = addTestCompanyToDB();
		assertEquals("address1", company.getAddress());
	}

	@Test
	public void testGetCompanyByIdWithWrongAddress() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongAddress", company.getAddress());
	}

	@Test
	public void testGetCompanyByIdWithRightProvince() {
		Company company = addTestCompanyToDB();
		assertEquals("province1", company.getProvince());
	}

	@Test
	public void testGetCompanyByIdWithWrongProvince() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongProvince", company.getProvince());
	}

	@Test
	public void testGetCompanyByIdWithRightZipCode() {
		Company company = addTestCompanyToDB();
		assertEquals("zipCode1", company.getZipCode());
	}

	@Test
	public void testGetCompanyByIdWithWrongZipCode() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongZipCode", company.getZipCode());
	}

	@Test
	public void testGetCompanyByIdWithRightCountry() {
		Company company = addTestCompanyToDB();
		assertEquals("country1", company.getCountry());
	}

	@Test
	public void testGetCompanyByIdWithWrongCountry() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongCountry", company.getCountry());
	}

	@Test
	public void testGetCompanyByIdWithRightPhone() {
		Company company = addTestCompanyToDB();
		assertEquals("phone1", company.getPhone());
	}

	@Test
	public void testGetCompanyByIdWithWrongPhone() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongPhone", company.getPhone());
	}

	@Test
	public void testGetCompanyByIdWithRightEmail() {
		Company company = addTestCompanyToDB();
		assertEquals("email1", company.getEmail());
	}

	@Test
	public void testGetCompanyByIdWithWrongEmail() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongEmail", company.getEmail());
	}

	@Test
	public void testEditCompanyWhileNoCompaniesInDB() {
		Company company = new Company("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		boolean edited = companyController.editCompany(company);
		assertFalse(edited);
	}

	@Test
	public void testEditCompanyName() {
		Company company = addTestCompanyToDB();
		company.setName("EditedName");
		companyController.editCompany(company);
		assertEquals("EditedName", companyController.getCompanyId("1").getName());
	}

	@Test
	public void testEditCompanyNameWithTwoCompaniesInDb() {
		Company company = addTestCompanyToDB();
		mongoTestHelper.addCompany("2", "nameC2", "vatCode2", "address2", "city2", "province2", "zipCode2", "country2", "phone2", "email2");
		company.setName("EditedName");
		companyController.editCompany(company);
		assertEquals("EditedName", companyController.getCompanyId("1").getName());
	}

	/////////invoice
	private Invoice addTestInvoiceToDB() {
		mongoTestHelper.addInvoice("1", "testName", "testPrice", "testDescription");
		Invoice invoice = companyController.getInvoiceId("1");
		assertNotNull(invoice);
		return invoice;
	}

	@Test
	public void addTestInvoiceToDBWhenInvoiceAlreadyInDB() {
		companyController.addInvoice(new Invoice("1", "testName", "testPrice", "testDescription"));
		boolean added = companyController.addInvoice(new Invoice("1", "testName", "testPrice", "testDescription"));
		assertEquals(false, added);
	}

	@Test
	public void testGetAllInvoicesWhenThereAreNoInvoices() {
		List<Invoice> allInvoices = companyController.getAllInvoices();
		assertEquals(0, allInvoices.size());
	}

	@Test
	public void testGetAllInvoicesWhenThereIsOneInvoice() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		List<Invoice> allInvoices = companyController.getAllInvoices();
		assertEquals(1, allInvoices.size());	
	}

	@Test
	public void testGetInvoiceByIdWhenInvoiceIsNotThere() {
		mongoTestHelper.addInvoice("2", "nameI1", "100", "basic invoice type1");
		Invoice invoice = companyController.getInvoiceId("1");
		assertNull(invoice);
	}

	@Test
	public void testGetInvoiceByIdWithRightPrice() {
		Invoice invoice = addTestInvoiceToDB();
		assertEquals("testPrice", invoice.getPrice());
	}

	@Test
	public void testGetInvoiceByIdWithWrongPrice() {
		Invoice invoice = addTestInvoiceToDB();
		assertNotEquals("wrongTestPrice", invoice.getPrice());
	}

	@Test
	public void testGetInvoiceByIdWithRightDescription() {
		Invoice invoice = addTestInvoiceToDB();
		assertEquals("testDescription", invoice.getDescription());
	}

	@Test
	public void testGetInvoiceByIdWithWrongDescription() {
		Invoice invoice = addTestInvoiceToDB();
		assertNotEquals("wrongTestDescription", invoice.getDescription());
	}	
	
	@Test
	public void testEditInvoiceWhileNoInvoicesInDB() {
		Invoice invoice = new Invoice("1", "testName", "testPrice", "testDescription");
		boolean edited = companyController.editInvoice(invoice);
		assertFalse(edited);
	}

	@Test
	public void testEditInvoiceName() {
		Invoice invoice = addTestInvoiceToDB();
		invoice.setName("EditedName");
		companyController.editInvoice(invoice);
		assertEquals("EditedName", companyController.getInvoiceId("1").getName());
	}

	@Test
	public void testEditInvoiceNameWithTwoInvoicesInDb() {
		Invoice invoice = addTestInvoiceToDB();
		mongoTestHelper.addInvoice("2", "nameI2", "200", "basic invoice type2");
		invoice.setName("EditedName");
		companyController.editInvoice(invoice);
		assertEquals("EditedName", companyController.getInvoiceId("1").getName());
	}
	
	@After
	public void stopDBServer() {
		redisServer.stop();
	}

}
