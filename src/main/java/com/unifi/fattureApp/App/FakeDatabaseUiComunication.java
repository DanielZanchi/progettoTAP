package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.wrappers.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class FakeDatabaseUiComunication extends DatabaseUiComunication{

	public FakeDatabaseUiComunication(String[] args, boolean usingMongodb) throws UnknownHostException {
		super(args, usingMongodb);
		
	}

	@Override
	void settingUpMongodb(String[] args) throws UnknownHostException {
		if (args!=null && args.length > 0)
			mongoHost = args[0];

		MongoClient mongoClient = null;
		Fongo fongo = new Fongo("mongo server 1");
		mongoClient = fongo.getMongo();
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("com.mongodb.FongoDBCollection");
		rootLogger.setLevel(Level.OFF);
		//mongoClient = new MongoClient(mongoHost, 27017);
		database = new MongoWrapper(mongoClient);

	}

}
