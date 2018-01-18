package com.unifi.fattureApp.helpTestTools;

import static org.junit.Assert.assertNotNull;

import org.jongo.MongoCollection;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Params;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;

public class TestHelperTool {
	private DBCollection clients;
	private DBCollection companies;
	private DBCollection invoices;
	private boolean usingMongo=true;
	
	private CouchDbClient couchDbClient;
	public TestHelperTool () {

	}

	public void setUpCouchClient(CouchDbClient couchDbClient) {
		usingMongo=false;
		couchDbClient = new CouchDbClient(new CouchDbProperties().setPort(27017).setHost("localhost").setDbName("testcompany"));
	}
	

	public void setUpMongoClient(MongoClient mongoClient) {
		DB db = mongoClient.getDB("company");
		db.getCollection("client").drop();
		db.getCollection("companies").drop();
		db.getCollection("invoices").drop();
		db.getCollection("printedInvoices").drop();
		clients = db.getCollection("client");
		companies = db.getCollection("companies");
		invoices = db.getCollection("invoices");
	}

	public void addClient(String id, String name, String fiscalCode, String cityResidence, String city, String province, String zip, String country, String phone, String email/*, String birthDay*/) {
		if(usingMongo) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("fiscalCode", fiscalCode);
		document.put("cityResidence", cityResidence);
		document.put("city", city);
		document.put("province", province);
		document.put("zip", zip);
		document.put("country", country);
		document.put("phone", phone);
		document.put("email", email);

		clients.insert(document);
		}else {
			couchDbClient.save(new Client(id, name, fiscalCode, cityResidence, city, province, zip, country, phone, email));
		}
	}

	public boolean containsClient(String id, String name, String fiscalCode, String cityResidence, String city, String province, String zip, String country, String phone, String email/*, String birthDay*/) {
		if(usingMongo) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("fiscalCode", fiscalCode);
		query.put("cityResidence", cityResidence);
		query.put("city", city);
		query.put("province", province);
		query.put("zip", zip);
		query.put("country", country);
		query.put("phone", phone);
		query.put("email", email);

		return clients.find(query).hasNext();
		}else {
		  return couchDbClient.find(Client.class, id)!=null;
		}
	}

	public void addCompany(String id, String name, String vatCode,
			String address, String city, String province, String zipCode, String country, String phone, String email) {
		if(usingMongo) {
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
		}else {
			couchDbClient.save(new Company(id, name, vatCode, address, city, province, zipCode, country, phone, email));
		}
	}

	public boolean containsCompany(String id, String name, String vatCode,
			String address, String city, String province, String zipCode, String country, String phone, String email) {
		if(usingMongo) {
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
		}else {
			return couchDbClient.find(Company.class, id)!=null;
		}
	}

	public void addInvoice(String id, String name, String price, String description) {
		if(usingMongo) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("price", price);
		document.put("description", description);
		invoices.insert(document);
		}else {
			couchDbClient.save(new Invoice(id, name, price, description));
		}
		
	}

	public boolean containsInvoice(String id, String name, String price, String description) {
		if(usingMongo) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("price", price);
		query.put("description", description);
		return invoices.find(query).hasNext();
		}else {
			return couchDbClient.find(Invoice.class,id)!=null;
		}
	}
}