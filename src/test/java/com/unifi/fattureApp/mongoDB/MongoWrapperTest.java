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
	public void testGetAllStudentsEmpty() {
		assertTrue(mongoDatabase.getAllPatientsList().isEmpty());
	}

	@Test
	public void testGetAllStudentsNotEmpty() {
		mongoTestHelper.addStudent("1", "first");
		mongoTestHelper.addStudent("2", "second");
	
		assertEquals(2, mongoDatabase.getAllPatientsList().size());
	}

	@Test
	public void testFindStudentByIdNotFound() {
		mongoTestHelper.addStudent("1", "first");
	
		assertNull(mongoDatabase.findPatientById("2"));
	}

	@Test
	public void testStudentIsSaved() {
		mongoDatabase.save(new Patient("1", "test"));
		assertTrue(mongoTestHelper.containsStudent("1", "test"));
	}

	@Test
	public void testFindStudentByIdFound() {
		mongoTestHelper.addStudent("1", "first");
		mongoTestHelper.addStudent("2", "second");
	
		Patient findStudentById = mongoDatabase.findPatientById("2");
		assertNotNull(findStudentById);
		assertEquals("2", findStudentById.getId());
		assertEquals("second", findStudentById.getName());
	}


}
