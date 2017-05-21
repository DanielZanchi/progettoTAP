package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.MedicalOfficeController;
import com.unifi.fattureApp.App.Client;
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
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		List<Client> allClients = medicalController.getAllClients();
		assertEquals(1, allClients.size());	
	}

	@Test
	public void testGetClientByIdWhenClientIsNotThere() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client client = medicalController.getClientId("2");
		assertNull(client);
	}

	@Test
	public void testGetClientByIdWhenClientIsThere() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("test", Client.getName());
	}
		
	@Test
	public void testGetClientByIdWithRightFiscalCode() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testFC", Client.getFiscalCode());
	}
	
	@Test
	public void testGetClientByIdWithWrongFiscalCode() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestFC", Client.getFiscalCode());
	}
	
	@Test
	public void testGetClientByIdWithRightCityResidence() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertEquals("testCR", Client.getCityResidence());
	}
	
	@Test
	public void testGetClientByIdWithWrongCityResidence() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestCR", Client.getCityResidence());
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
	
	@Test
	public void testGetClientByIdWithWrongBirthDay() {
		mongoTestHelper.addClient("1", "test","testFC","testCR","testBD");
		Client Client = medicalController.getClientId("1");
		assertNotNull(Client);
		assertNotEquals("wrongtestBD", Client.getBirthDate());
	}
}