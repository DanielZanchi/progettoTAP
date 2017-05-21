package com.unifi.fattureApp.mongoWrapper;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;

public class MongoWrapper implements Database{
	private MongoCollection clients;
	private MongoCollection companies;


	public MongoWrapper(MongoClient mc) throws UnknownHostException {
		DB db = mc.getDB("medicalOffice");

		Jongo jongo = new Jongo(db);
		clients = jongo.getCollection("client");
		companies=jongo.getCollection("companies");
	}
	
	@Override
	public List<Client> getAllClientsList() {
		Iterable<Client> iterable = clients.find().as(Client.class);
		return StreamSupport.
			stream(iterable.spliterator(), false).
			collect(Collectors.toList());
	}
	
	@Override
	public Client findClientById(String id) {
		return clients.findOne("{id: #}", id).as(Client.class);
	}

	@Override
	public void saveClient(Client client) {
		clients.save(client);
	}
	
	
	@Override
	public List<Company> getAllCompaniesList() {
		Iterable<Company> iterable = companies.find().as(Company.class);
		return StreamSupport.
			stream(iterable.spliterator(), false).
			collect(Collectors.toList());
	}
	
	@Override
	public Company findCompanyById(String id) {
		return companies.findOne("{id: #}", id).as(Company.class);
	}

	@Override
	public void saveCompany(Company company) {
		companies.save(company);
	}
	
	
	
}