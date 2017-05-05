package com.unifi.fattureApp.helpTestTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.fattureApp.MongoWrapper;

public class MongoTestHelperTool {

	private DBCollection patients;
	
	public MongoTestHelperTool(MongoClient mongoClient){
		DB db=mongoClient.getDB("medicalOffice");
		db.getCollection("patients").drop();
		patients=db.getCollection("patients");
	}
	
	private void addPatientsToDB(String id, String name){
		BasicDBObject document=new BasicDBObject();
		document.put("id",id);
		document.put("name", name);
		patients.insert(document);
	}
	
}
