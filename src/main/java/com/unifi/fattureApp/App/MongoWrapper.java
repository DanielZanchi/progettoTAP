package com.unifi.fattureApp.App;

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

	public List<Patient> getAllPatients() {
		Iterable<Patient> iterable=patients.find().as(Patient.class);
		return StreamSupport.stream(iterable.spliterator(),false).collect(Collectors.toList());
	}

	public Patient findPatientId(String id) {
		// TODO Auto-generated method stub
		return patients.findOne("{id:#}",id).as(Patient.class);
	}

	

}
