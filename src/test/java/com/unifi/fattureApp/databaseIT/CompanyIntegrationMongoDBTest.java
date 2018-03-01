package com.unifi.fattureApp.databaseIT;

import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.AppController;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.wrappers.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class CompanyIntegrationMongoDBTest extends AbstractCompanyIntegrationTest {
	public void init() {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();

		testHelper = new TestHelperTool();
		testHelper.setUpMongoClient(mongoClient);

		Database database = null;
		database = new MongoWrapper(mongoClient);

		companyController = new AppController(database);

		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("com.mongodb.FongoDBCollection");
		rootLogger.setLevel(Level.OFF);	
	}
}