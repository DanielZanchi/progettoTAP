package com.unifi.fattureApp.mongoDB;

import java.net.UnknownHostException;

import org.junit.Before;
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
}