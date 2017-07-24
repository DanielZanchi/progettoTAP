package com.unifi.fattureApp.App;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.MedicalOfficeController;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.UI.MainWindowUI;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;


import com.mongodb.MongoClient;

public class Main {
	
	public static void main(String[] args) throws UnknownHostException {
		Logger logger = Logger.getLogger(Main.class);
		BasicConfigurator.configure();
				
		String mongoHost = "127.0.0.1";
		MainWindowUI window = new MainWindowUI();
		
		if (args.length > 0)
			mongoHost = args[0];
		
		Database database = new MongoWrapper(new MongoClient(mongoHost, 27017));
		
		MedicalOfficeController myMedicalController = new MedicalOfficeController(database);
		System.out.println("Adding a patient...");
		myMedicalController.addPatient(new Client("1", "Daniele Land","landiFiscalCode","landiResidence"/*,"landiHappyDay"*/));
		System.out.println("Adding a patient...");
		myMedicalController.addPatient(new Client("2", "Daniel Zanchi","zanchiFiscalCode","zanchiResidence"/*,"zanchiHappyDay"*/));

		System.out.println("In the medical Office there are:");
		List<Client> patients = myMedicalController.getAllClients();
		patients.
			stream().
			forEach(
				patient -> System.out.println
					("Patient: " + patient.getId() + " - " + patient.getName())
			);
		
		
		
		System.out.println("Adding a company");
		myMedicalController.addCompany
		(new Company("1", "Guerri & Co.", "guerrivatCode", "guerriaddress", "guerricity", "guerriprovince", "guerrizipCode",
				"guerricountry", "guerriphone", "guerriemail"));

		System.out.println("Adding a company...");
		myMedicalController.addCompany
		(new Company("2", "Guerri & Co2.", "vatCodeguerri2", "addressguerri2", "cityguerri2", "provinceguerri2", "zipCodeguerri2",
				"countryguerri2", "phoneguerri2", "emailguerri2"));

		
		System.out.println("In the database there are:");
		List<Company> companies = myMedicalController.getAllCompany();
		companies.
			stream().
			forEach(
				company -> System.out.println
					("Company: " + company.getId() + " - " + company.getName())
			);
		
		
		// Launch the UI
		window.main(null);
//		System.out.println("Fatture-app terminates.");
		logger.info("Fatture-app terminates.");
	}
}