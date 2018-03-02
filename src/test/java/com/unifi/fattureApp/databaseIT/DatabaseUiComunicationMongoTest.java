package com.unifi.fattureApp.databaseIT;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.unifi.fattureApp.App.DatabaseUiComunication;
import com.unifi.fattureApp.wrappers.MongoWrapper;

public class DatabaseUiComunicationMongoTest extends AbstractDatabaseUiComunicationTest{	

	@Override
	public void setUpDatabase() {
		String args[] = null;
		myDatabaseUiComunication = new DatabaseUiComunication(true, args, true);
	}
	
	@Test
	public void testDatabaseIsInstanceOfMongoWrapper() {
		assertEquals(MongoWrapper.class, myDatabaseUiComunication.getCurrentDatabaseClass());
	}

	
	
	
}