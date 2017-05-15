package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Patient;
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
		assertTrue(mongoDatabase.getAllPatientsList().isEmpty());
	}

	@Test
	public void testGetAllPatientsNotEmpty() {
		mongoTestHelper.addPatient("1", "first");
		mongoTestHelper.addPatient("2", "second");
	
		assertEquals(2, mongoDatabase.getAllPatientsList().size());
	}

	@Test
	public void testFindPatientByIdNotFound() {
		mongoTestHelper.addPatient("1", "first");
	
		assertNull(mongoDatabase.findPatientById("2"));
	}

	@Test
	public void testPatientIsSaved() {
		mongoDatabase.save(new Patient("1", "test"));
		assertTrue(mongoTestHelper.containsPatient("1", "test"));
	}

	@Test
	public void testFindPatientByIdFound() {
		mongoTestHelper.addPatient("1", "first");
		mongoTestHelper.addPatient("2", "second");
	
		Patient findPatientById = mongoDatabase.findPatientById("2");
		assertNotNull(findPatientById);
		assertEquals("2", findPatientById.getId());
		assertEquals("second", findPatientById.getName());
	}
}