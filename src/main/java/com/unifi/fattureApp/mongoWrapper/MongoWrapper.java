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
import com.unifi.fattureApp.App.Patient;

public class MongoWrapper implements Database{
	private MongoCollection patients;

	public MongoWrapper(MongoClient mc) throws UnknownHostException {
		DB db = mc.getDB("medicalOffice");

		Jongo jongo = new Jongo(db);
		patients = jongo.getCollection("patient");
	}
	
	@Override
	public List<Patient> getAllPatientsList() {
		Iterable<Patient> iterable = patients.find().as(Patient.class);
		return StreamSupport.
			stream(iterable.spliterator(), false).
			collect(Collectors.toList());
	}
	
	@Override
	public Patient findPatientById(String id) {
		return patients.findOne("{id: #}", id).as(Patient.class);
	}

	@Override
	public void save(Patient patient) {
		patients.save(patient);
	}
}