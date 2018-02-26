package com.unifi.fattureApp.mongoIT;

import java.net.UnknownHostException;

import org.junit.After;
import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.AppController;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class CompanyIntegrationMongoDB extends AbstractCompanyIntegration{
	
	@Override
	public void init() {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		
		mongoTestHelper = new TestHelperTool();
		mongoTestHelper.setUpMongoClient(mongoClient);

		Database database=null;
		try {
			database = new MongoWrapper(mongoClient);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		companyController = new AppController(database);
		
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("com.mongodb.FongoDBCollection");
		rootLogger.setLevel(Level.OFF);
		
	}

}
