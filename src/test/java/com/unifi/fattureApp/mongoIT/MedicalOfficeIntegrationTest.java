package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.App.MedicalOfficeController;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.helpTestTools.MongoTestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MedicalOfficeIntegrationTest {
	private MedicalOfficeController medicalController;

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
		medicalController = new MedicalOfficeController(database);
	}
	
	
	
	

	@Test
	public void testGetAllClientsWhenThereAreNoClients() {
		List<Client> allClients = medicalController.getAllClients();
		assertEquals(0, allClients.size());
	}

	@Test
	public void testGetAllClientsWhenThereIsOneClient() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		List<Client> allClients = medicalController.getAllClients();
		assertEquals(1, allClients.size());	
	}

	@Test
	public void testGetClientByIdWhenClientIsNotThere() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client client = medicalController.getClientId("2");
		assertNull(client);
	}

	@Test
	public void testGetClientByIdWhenClientIsThere() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("test", Client.getName());
	}
		
	@Test
	public void testGetClientByIdWithRightFiscalCode() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testFC", Client.getFiscalCode());
	}
	
	@Test
	public void testGetClientByIdWithWrongFiscalCode() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestFC", Client.getFiscalCode());
	}
	
	@Test
	public void testGetClientByIdWithRightCityResidence() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testCR", Client.getCityResidence());
	}
	
	@Test
	public void testGetClientByIdWithWrongCityResidence() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestCR", Client.getCityResidence());
	}
	
	@Test
	public void testGetClientByIdWithRightPhone() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testPhone", Client.getPhone());
	}
	
	@Test
	public void testGetClientByIdWithWrongPhone() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestPhone", Client.getPhone());
	}
	
	@Test
	public void testGetClientByIdWithRightEmail() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testEmail", Client.getEmail());
	}
	
	@Test
	public void testGetClientByIdWithWrongEmail() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testPhone","testEmail"/*,"testBD"*/);
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestEmail", Client.getEmail());
	}
	
/*
	@Test
	public void testGetClientByIdWithRightBirthDay() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testBD", Client.getBirthDate());
	}
	*/
	
	/*
	@Test
	public void testGetClientByIdWithWrongBirthDay() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestBD", Client.getBirthDate());
	}
	*/
	
	
	//////////////////////////////company
	
	@Test
	public void testGetAllCompaniesWhenThereAreNoCompanies() {
		List<Company> allCompanies = medicalController.getAllCompany();
		assertEquals(0, allCompanies.size());
	}

	@Test
	public void testGetAllCompaniesWhenThereIsOneCompany() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		List<Company> allCompanies = medicalController.getAllCompany();
		assertEquals(1, allCompanies.size());	
	}

	@Test
	public void testGetCompanyByIdWhenCompanyIsNotThere() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
	}

	@Test
	public void testGetCompanyByIdWhenCompanyIsThere() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("nameC1", company.getName());
	}
		
	@Test
	public void testGetCompanyByIdWithRightVatCode() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("vatCode1", company.getVatCode());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongVatCode() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongvatCode", company.getVatCode());
	}
	
	@Test
	public void testGetCompanyByIdWithRightCity() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("city1", company.getCity());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongCity() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongcity", company.getCity());
	}
	
	@Test
	public void testGetCompanyByIdWithRightProvince() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("province1", company.getProvince());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongProvince() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongprovince", company.getProvince());
	}
	
	@Test
	public void testGetCompanyByIdWithRightZipCode() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("zipCode1", company.getZipCode());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongZipCode() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongzipCode", company.getZipCode());
	}
	
	@Test
	public void testGetCompanyByIdWithRightCountry() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("country1", company.getCountry());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongCountry() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongcountry", company.getCountry());
	}
	
	@Test
	public void testGetCompanyByIdWithRightPhone() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("phone1", company.getPhone());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongPhone() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongphone", company.getPhone());
	}
	
	@Test
	public void testGetCompanyByIdWithRightEmail() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertEquals("email1", company.getEmail());
	}
	
	@Test
	public void testGetCompanyByIdWithWrongEmail() {
		mongoTestHelper.addCompany("1", "nameC1", "vatCode1", "address1", "city1", "province1", "zipCode1", "country1", "phone1", "email1");
		Company company = medicalController.getCompanyId("1");
		assertNotNull(company);
		assertNotEquals("wrongemail", company.getEmail());
	}
	
	/////////invoice
	
	
	@Test
	public void testGetAllInvoicesWhenThereAreNoInvoices() {
		List<Invoice> allInvoices = medicalController.getAllInvoices();
		assertEquals(0, allInvoices.size());
	}
	
	
	@Test
	public void testGetAllInvoicesWhenThereIsOneInvoice() {
		mongoTestHelper.addInvoice("1", "nameI1", "100", "basic invoice type1");
		List<Invoice> allInvoices = medicalController.getAllInvoices();
		assertEquals(1, allInvoices.size());	
	}

	@Test
	public void testGetInvoiceByIdWhenInvoiceIsNotThere() {
		mongoTestHelper.addInvoice("2", "nameI1", "100", "basic invoice type1");
		Invoice invoice = medicalController.getInvoiceId("1");
		assertNull(invoice);
	}
	
	

	
}