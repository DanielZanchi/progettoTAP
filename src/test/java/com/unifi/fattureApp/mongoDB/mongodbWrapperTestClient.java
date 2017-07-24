package com.unifi.fattureApp.mongoDB;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class mongodbWrapperTestClient extends MongoWrapperTest {

	@Override
	public MongoClient createMongoClient() {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		return mongoClient;
	}

}
