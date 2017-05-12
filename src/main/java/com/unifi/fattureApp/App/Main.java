package com.unifi.fattureApp.App;


import java.net.UnknownHostException;
import java.util.List;

import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.MedicalOfficeController;
import com.unifi.fattureApp.App.Patient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		String mongoHost = "localhost";
		if (args.length > 0)
			mongoHost = args[0];
		Database database = new MongoWrapper(new MongoClient(mongoHost));
		MedicalOfficeController myMedicalController = new MedicalOfficeController(database);

		System.out.println("Adding a patient...");
		myMedicalController.addPatient(new Patient("1", "Daniele Landi"));
		System.out.println("Adding a patient...");
		myMedicalController.addPatient(new Patient("2", "Daniel Zanchi"));

		System.out.println("In the medical Office there are:");
		List<Patient> patients = myMedicalController.getAllPatients();
		patients.
			stream().
			forEach(
				patient -> System.out.println
					("Patient: " + patient.getId() + " - " + patient.getName())
			);
		System.out.println("Fatture-app terminates.");
	}
}
