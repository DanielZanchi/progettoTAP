package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.lightcouch.CouchDbClient;


import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.CouchWrapper;

public abstract class CouchdbWrapperTestAbstract {
	public abstract CouchDbClient createCouchDbClient() throws UnknownHostException;

	private CouchWrapper couchDatabase;

	private TestHelperTool mongoTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		CouchDbClient couchDbClient = createCouchDbClient();
		mongoTestHelper = new TestHelperTool();
		mongoTestHelper.setUpCouchClient(couchDbClient);
		couchDatabase = new CouchWrapper(couchDbClient);
	}	
	
	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(couchDatabase.getAllClientsList().isEmpty());
	}
	
	@Test
	public void testGetAllClientsNotEmpty() {
		mongoTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail"/*, "firstBD"*/);
		mongoTestHelper.addClient("2", "second", "secondFC", "secondCR", "secondCity", "secondProvince", "secondZip", "secondCountry", "secondPhone", "secondEmail");

		assertEquals(2, couchDatabase.getAllClientsList().size());
	}
}