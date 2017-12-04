package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.CompanyController;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.MongoTestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class CompanyIntegrationTest {
	private CompanyController companyController;

	// helper for testing with Mongo
	private MongoTestHelperTool mongoTestHelper;

	@Before
	public void setUp() throws Exception {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		mongoTestHelper = new MongoTestHelperTool(mongoClient);

		// we don't mock the database:
		// we use a real instance of MongoDatabaseWrapper
		Database database = new MongoWrapper(mongoClient);
		companyController = new CompanyController(database);
	}

	private Client addTestClientToDB() {
		mongoTestHelper.addClient("1", "test", "testFC", "testCR", "testCity", "testProvince", "testZip", "testCountry", "testPhone", "testEmail");
		Client Client = companyController.getClientId("1");
		assertNotNull(Client);
		return Client;
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
		Client Client = addTestClientToDB();
		assertEquals("test", Client.getName());
	}

	@Test
	public void testGetClientByIdWithRightFiscalCode() {
		Client Client = addTestClientToDB();
		assertEquals("testFC", Client.getFiscalCode());
	}

	@Test
	public void testGetClientByIdWithWrongFiscalCode() {
		Client Client = addTestClientToDB();
		assertNotEquals("wrongtestFC", Client.getFiscalCode());
	}

	@Test
	public void testGetClientByIdWithRightCityResidence() {
		Client Client = addTestClientToDB();
		assertEquals("testCR", Client.getCityResidence());
	}

	@Test
	public void testGetClientByIdWithWrongCityResidence() {
		Client Client = addTestClientToDB();
		assertNotEquals("wrongtestCR", Client.getCityResidence());
	}

	@Test
	public void testGetClientByIdWithRightPhone() {
		Client Client = addTestClientToDB();
		assertEquals("testPhone", Client.getPhone());
	}

	@Test
	public void testGetClientByIdWithWrongPhone() {
		Client Client = addTestClientToDB();
		assertNotEquals("wrongtestPhone", Client.getPhone());
	}

	@Test
	public void testGetClientByIdWithRightEmail() {
		Client Client = addTestClientToDB();
		assertEquals("testEmail", Client.getEmail());
	}

	@Test
	public void testGetClientByIdWithWrongEmail() {
		Client Client = addTestClientToDB();
		assertNotEquals("wrongtestEmail", Client.getEmail());
	}

	/*
	@Test
	public void testGetClientByIdWithRightBirthDay() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = companyController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testBD", Client.getBirthDate());
	}
	 */

	/*
	@Test
	public void testGetClientByIdWithWrongBirthDay() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = companyController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestBD", Client.getBirthDate());
	}
	 */

	//      company	
	private Company addTestCompanyToDB() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = companyController.getCompanyId("1");
		assertNotNull(company);
		return company;
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
		addTestCompanyToDB();
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
		assertNotEquals("wrongvatCode", company.getVatCode());
	}

	@Test
	public void testGetCompanyByIdWithRightCity() {
		Company company = addTestCompanyToDB();
		assertEquals("city1", company.getCity());
	}

	@Test
	public void testGetCompanyByIdWithWrongCity() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongcity", company.getCity());
	}

	@Test
	public void testGetCompanyByIdWithRightProvince() {
		Company company = addTestCompanyToDB();
		assertEquals("province1", company.getProvince());
	}

	@Test
	public void testGetCompanyByIdWithWrongProvince() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongprovince", company.getProvince());
	}

	@Test
	public void testGetCompanyByIdWithRightZipCode() {
		Company company = addTestCompanyToDB();
		assertEquals("zipCode1", company.getZipCode());
	}

	@Test
	public void testGetCompanyByIdWithWrongZipCode() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongzipCode", company.getZipCode());
	}

	@Test
	public void testGetCompanyByIdWithRightCountry() {
		Company company = addTestCompanyToDB();
		assertEquals("country1", company.getCountry());
	}

	@Test
	public void testGetCompanyByIdWithWrongCountry() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongcountry", company.getCountry());
	}

	@Test
	public void testGetCompanyByIdWithRightPhone() {
		Company company = addTestCompanyToDB();
		assertEquals("phone1", company.getPhone());
	}

	@Test
	public void testGetCompanyByIdWithWrongPhone() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongphone", company.getPhone());
	}

	@Test
	public void testGetCompanyByIdWithRightEmail() {
		Company company = addTestCompanyToDB();
		assertEquals("email1", company.getEmail());
	}

	@Test
	public void testGetCompanyByIdWithWrongEmail() {
		Company company = addTestCompanyToDB();
		assertNotEquals("wrongemail", company.getEmail());
	}

	/////////invoice
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
}