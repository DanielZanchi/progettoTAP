package com.unifi.fattureApp.mongoDB;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class MongodbWrapperTests extends MongoWrapperTestAbstract {
	@Override
	public MongoClient createMongoClient() {
		Fongo fongo = new Fongo("mongo server 1");
		MongoClient mongoClient = fongo.getMongo();
		return mongoClient;
	}
}