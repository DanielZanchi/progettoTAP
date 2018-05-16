package com.unifi.fattureApp.databaseIT;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;

import org.junit.Test;

import com.unifi.fattureApp.app.FakeDatabaseUiComunication;
import com.unifi.fattureApp.app.RealDatabaseUiComunication;
import com.unifi.fattureApp.wrappers.MongoWrapper;

public class FakeDatabaseUiComunicationMongoTest extends AbstractDatabaseUiComunicationTest {	
	@Override
	public void setUpDatabase() throws UnknownHostException {
		String args[] = null;
		myDatabaseUiComunication = new FakeDatabaseUiComunication(args, true);
	}

	@Test
	public void testDatabaseIsInstanceOfMongoWrapper() {
		assertEquals(MongoWrapper.class, myDatabaseUiComunication.getCurrentDatabaseClass());
	}

	@Test
	public void testMongoHostGivenAsParameter() throws UnknownHostException {
		String[] args = {"testMongoHost"};
		new RealDatabaseUiComunication(args, true);
	}
}