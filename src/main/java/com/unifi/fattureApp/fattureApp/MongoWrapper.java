package com.unifi.fattureApp.fattureApp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.jongo.Jongo;
import org.jongo.MongoCollection;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoWrapper implements Database{

	private MongoCollection patients;

	public MongoWrapper(MongoClient myMongoClient) {
		// TODO Auto-generated constructor stub
		DB db=myMongoClient.getDB("medicalOffice");
		Jongo myJongo=new Jongo(db);
		patients=myJongo.getCollection("patients");
	}

	public List<Patients> getAllPatients() {
		Iterable<Patients> iterable=patients.find().as(Patients.class);
		return StreamSupport.stream(iterable.spliterator(),false).collect(Collectors.toList());
	}

	

}
