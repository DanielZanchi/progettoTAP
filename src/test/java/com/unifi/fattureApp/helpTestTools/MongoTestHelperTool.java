package com.unifi.fattureApp.helpTestTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.App.PrintedInvoice;

public class MongoTestHelperTool {
	private DBCollection clients;
	private DBCollection companies;
	private DBCollection invoices;
	private DBCollection printedInvoices;

	public MongoTestHelperTool (MongoClient mongoClient) {
		// make sure to drop the clients table for testing
		DB db = mongoClient.getDB("company");
		db.getCollection("client").drop();
		db.getCollection("companies").drop();
		db.getCollection("invoices").drop();
		db.getCollection("printedInvoices").drop();
		clients = db.getCollection("client");
		companies = db.getCollection("companies");
		invoices = db.getCollection("invoices");
		printedInvoices = db.getCollection("printedInvoices");
	}

	public void addClient(String id, String name, String fiscalCode, String cityResidence, String city, String province, String zip, String country, String phone, String email/*, String birthDay*/) {
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
		//document.put("birthDay", birthDay);

		clients.insert(document);
	}

	public boolean containsClient(String id, String name, String fiscalCode, String cityResidence, String city, String province, String zip, String country, String phone, String email/*, String birthDay*/) {
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
		//query.put("birthDay", birthDay);

		return clients.find(query).hasNext();
	}

	public void addCompany(String id, String name, String vatCode,
			String address, String city, String province, String zipCode, String country, String phone, String email) {
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

	public boolean containsCompany(String id, String name, String vatCode,
			String address, String city, String province, String zipCode, String country, String phone, String email) {
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

	public void addInvoice(String id, String name, String price, String description) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		document.put("price", price);
		document.put("description", description);
		invoices.insert(document);
	}

	public boolean containsInvoice(String id, String name, String price, String description) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("name", name);
		query.put("price", price);
		query.put("description", description);
		return invoices.find(query).hasNext();
	}

	public void addPrintedInvoice(PrintedInvoice printedInvoice) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", printedInvoice.getPrintedId());
		document.put("printedCompany", printedInvoice.getPrintedCompany());
		document.put("printedClient", printedInvoice.getPrintedClient());
		document.put("printedInvoice", printedInvoice.getPrintedInvoice());
		printedInvoices.insert(document);
	}

	public boolean containsPrintedInvoice(String id, Company printedCompany, Client printedClient, Invoice printedInvoice) {
		BasicDBObject query = new BasicDBObject();
		query.put("id", id);
		query.put("printedCompany", printedCompany);
		query.put("printedClient", printedClient);
		query.put("printedInvoice", printedInvoice);
		return printedInvoices.find(query).hasNext();
	}

	public PrintedInvoice createPrintedInvoice(String id) {
		Company company = new Company(id, "companyName"+id, "vat"+id, "address"+id, "city", "province"+id, "zip"+id, "country"+id, ""+id, ""+id);
		Client client = new Client(id, "clientName"+id, "fiscal"+id, "residence"+id, "city"+id, "province"+id, "zip"+id, "country"+id, ""+id, ""+id);
		Invoice invoice = new Invoice(id, "invoiceName"+id, "description"+id, "price"+id);
		return new PrintedInvoice(company, client, invoice, id);
	}
}