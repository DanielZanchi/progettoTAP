package com.unifi.fattureApp.helpTestTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoTestHelperTool {

	private DBCollection patients;

	public MongoTestHelperTool (MongoClient mongoClient) {
		// make sure to drop the students table for testing
		DB db = mongoClient.getDB("");
		db.getCollection("student").drop();
		patients = db.getCollection("student");
	}

	public void addStudent(String id, String name) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		patients.insert(document);
	}

	public boolean containsStudent(String id, String name) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		return patients.find(query).hasNext();
	}
	
}
