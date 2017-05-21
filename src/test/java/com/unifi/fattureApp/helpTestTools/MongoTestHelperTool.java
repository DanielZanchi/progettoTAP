package com.unifi.fattureApp.helpTestTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoTestHelperTool {
	private DBCollection clients;
	private DBCollection companies;


	public MongoTestHelperTool (MongoClient mongoClient) {
		// make sure to drop the patients table for testing
		DB db = mongoClient.getDB("medicalOffice");
		db.getCollection("client").drop();
		db.getCollection("companies").drop();
		clients = db.getCollection("client");
		companies=db.getCollection("companies");
	}

	public void addClient(String id, String name,String fiscalCode,String cityResidence,String birthDay) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("fiscalCode", fiscalCode);
		document.put("cityResidence", cityResidence);
		document.put("birthDay", birthDay);

		clients.insert(document);
	}

	public boolean containsClient(String id, String name,String fiscalCode,String cityResidence,String birthDay) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("fiscalCode", fiscalCode);
		query.put("cityResidence", cityResidence);
		query.put("birthDay", birthDay);
		return clients.find(query).hasNext();
	}
	
	
	public void addCompany(String id, String name,String vatCode,
			String address,String city,String province,String zipCode,String country,String phone,String email) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("vatCode", vatCode);
		document.put("address", address);
		document.put("city", city);
		document.put("province", province);
		document.put("zipCode", zipCode);
		document.put("country", country);
		document.put("phone", phone);
		document.put("email", email);

		companies.insert(document);
	}

	public boolean containsCompany(String id, String name,String vatCode,
			String address,String city,String province,String zipCode,String country,String phone,String email) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("vatCode", vatCode);
		query.put("address", address);
		query.put("city", city);
		query.put("province", province);
		query.put("zipCode", zipCode);
		query.put("country", country);
		query.put("phone", phone);
		query.put("email", email);
		return companies.find(query).hasNext();
	}
	
	
	
	
}