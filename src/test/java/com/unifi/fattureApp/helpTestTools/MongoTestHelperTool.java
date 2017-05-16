package com.unifi.fattureApp.helpTestTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoTestHelperTool {
	private DBCollection patients;

	public MongoTestHelperTool (MongoClient mongoClient) {
		// make sure to drop the patients table for testing
		DB db = mongoClient.getDB("medicalOffice");
		db.getCollection("patient").drop();
		patients = db.getCollection("patient");
	}

	public void addPatient(String id, String name,String fiscalCode,String cityResidence,String birthDay) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("fiscalCode", fiscalCode);
		document.put("cityResidence", cityResidence);
		document.put("birthDay", birthDay);

		patients.insert(document);
	}

	public boolean containsPatient(String id, String name,String fiscalCode,String cityResidence,String birthDay) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("fiscalCode", fiscalCode);
		query.put("cityResidence", cityResidence);
		query.put("birthDay", birthDay);
		return patients.find(query).hasNext();
	}
}