package com.unifi.fattureApp.App;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.UI.MainWindowUI;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

import com.mongodb.MongoClient;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		Logger logger = Logger.getLogger(Main.class);
		
		MongoUiComunication myMongoUiComunication = new MongoUiComunication();
		
		//BasicConfigurator.configure();
				
		//String mongoHost = "127.0.0.1";
		MainWindowUI window = new MainWindowUI();
		/*
		if (args.length > 0)
			mongoHost = args[0];
		
		Database database = new MongoWrapper(new MongoClient(mongoHost, 27017));
		*/
		
		//myMongoUiComunication.addClientToDatabase("Daniele Landi","landiFiscalCode","landiResidence","landiPhone","landiEmail");
		
		//myMongoUiComunication.addClientToDatabase("Daniel Zanchi","zanchiFiscalCode","zanchiResidence","zanchiPhone","zanchiEmail");
				
		/*
		System.out.println("In the company there are:");
		List<Client> clients = myCompanyController.getAllClients();
		clients.
			stream().
			forEach(
				client -> System.out.println
					("Client: " + client.getId() + " - " + client.getName())
			);
		*/
		
		//System.out.println("Adding a company");
		/*
		 myCompanyController.addCompany
		(new Company("1", "Guerri & Co.", "guerrivatCode", "guerriaddress", "guerricity", "guerriprovince", "guerrizipCode",
				"guerricountry", "guerriphone", "guerriemail"));

*/
		myMongoUiComunication.addCompanyToDatabase("Guerri & Co.", "guerrivatCode", "guerriaddress", "guerricity", "guerriprovince", "guerrizipCode",
				"guerricountry", "guerriphone", "guerriemail");
		
		//System.out.println("Adding a company...");
		/*
		myCompanyController.addCompany
		(new Company("2", "Guerri & Co2.", "vatCodeguerri2", "addressguerri2", "cityguerri2", "provinceguerri2", "zipCodeguerri2",
				"countryguerri2", "phoneguerri2", "emailguerri2"));
*/
		
		myMongoUiComunication.addCompanyToDatabase("Guerri & Co2.", "vatCodeguerri2", "addressguerri2", "cityguerri2", "provinceguerri2", "zipCodeguerri2",
				"countryguerri2", "phoneguerri2", "emailguerri2");
		
		/*
		System.out.println("In the database there are:");
		List<Company> companies = myCompanyController.getAllCompany();
		companies.
			stream().
			forEach(
				company -> System.out.println
					("Company: " + company.getId() + " - " + company.getName())
			);
			*/
		
		myMongoUiComunication.printAllClients();
		myMongoUiComunication.printAllCompanies();
		
		
		// Launch the UI
		window.main(null);
		
		System.out.println("Fatture-app terminates.");
		logger.info("Fatture-app terminates.");
	}
}