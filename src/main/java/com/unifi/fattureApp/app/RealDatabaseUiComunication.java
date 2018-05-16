package com.unifi.fattureApp.app;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.wrappers.MongoWrapper;

public class RealDatabaseUiComunication extends DatabaseUiComunication {
	public RealDatabaseUiComunication(String[] args, boolean usingMongodb) throws UnknownHostException {
		super(args, usingMongodb);
	}

	@Override
	void settingUpMongodb(String[] args) throws UnknownHostException {
		if (args!=null && args.length > 0) {
			mongoHost = args[0];			
		}

		MongoClient mongoClient = null;
		mongoClient = new MongoClient(mongoHost, 27017);
		database = new MongoWrapper(mongoClient);
	}
}