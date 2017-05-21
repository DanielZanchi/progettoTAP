package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.helpTestTools.MongoTestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public abstract class MongoWrapperTest { 
	private MongoWrapper mongoDatabase;

	public abstract MongoClient createMongoClient() throws UnknownHostException;

	private MongoTestHelperTool mongoTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		// in-memory java implementation of MongoDB
		// so that we don't need to install MongoDB in our computer
		MongoClient mongoClient = createMongoClient();
		mongoTestHelper = new MongoTestHelperTool(mongoClient);
	
		mongoDatabase = new MongoWrapper(mongoClient);
	}

	@Test
	public void testGetAllPatientsEmpty() {
		assertTrue(mongoDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstBD");
		mongoTestHelper.addClient("2", "second","secondFC","secondCR","secondBD");
	
		assertEquals(2, mongoDatabase.getAllClientsList().size());
	}

	@Test
	public void testFindPatientByIdNotFound() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstBD");
	
		assertNull(mongoDatabase.findClientById("2"));
	}

	@Test
	public void testPatientIsSaved() {
		mongoDatabase.save(new Client("1", "test","testFC","testCR","testBD"));
		assertTrue(mongoTestHelper.containsClient("1", "test","testFC","testCR","testBD"));
	}

	@Test
	public void testFindPatientByIdFound() {
		mongoTestHelper.addClient("1", "first","firstFC","firstCR","firstBD");
		mongoTestHelper.addClient("2", "second","secondFC","secondCR","secondBD");
	
		Client findClientById = mongoDatabase.findClientById("2");
		assertNotNull(findClientById);
		assertEquals("2", findClientById.getId());
		assertEquals("second", findClientById.getName());
	}
}