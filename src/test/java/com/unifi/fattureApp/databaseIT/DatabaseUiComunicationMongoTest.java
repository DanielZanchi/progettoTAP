package com.unifi.fattureApp.databaseIT;

import static org.junit.Assert.assertEquals;

import java.net.UnknownHostException;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.DatabaseUiComunication;
import com.unifi.fattureApp.wrappers.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class DatabaseUiComunicationMongoTest extends AbstractDatabaseUiComunicationTest {	
	@Override
	public void setUpDatabase() throws UnknownHostException {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("com.mongodb.FongoDBCollection");
		rootLogger.setLevel(Level.OFF);
		Database database = new MongoWrapper(mongoClient);
		myDatabaseUiComunication = new DatabaseUiComunication(database);
	}

	@Test
	public void testDatabaseIsInstanceOfMongoWrapper() {
		assertEquals(MongoWrapper.class, myDatabaseUiComunication.getCurrentDatabaseClass());
	}
}