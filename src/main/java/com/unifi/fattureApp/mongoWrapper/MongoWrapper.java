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

public class MongoWrapper implements Database{
	private MongoCollection clients;

	public MongoWrapper(MongoClient mc) throws UnknownHostException {
		DB db = mc.getDB("medicalOffice");

		Jongo jongo = new Jongo(db);
		clients = jongo.getCollection("client");
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
	public void save(Client client) {
		clients.save(client);
	}
}