package com.unifi.fattureApp.App;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoUiComunication {
	Database database;
	String mongoHost = "localhost";
	MedicalOfficeController myMedicalController;
	
	
	public MongoUiComunication(String[] args){
		if (args.length > 0)
			mongoHost = args[0];
		Database database = null;
		try {
			database = new MongoWrapper(new MongoClient(mongoHost));
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		myMedicalController = new MedicalOfficeController(database);
		
		try{
			database = new MongoWrapper(new MongoClient(mongoHost, 27017));
		}catch(Exception e){
			System.out.println("Error while connecting to mongoHost");
			e.printStackTrace();
		}
	
		myMedicalController = new MedicalOfficeController(database);
	}
	
	
	public boolean addClientToDatabase(String name, String fiscalCode, String residence, String phone, String email){
		String currentId=String.valueOf(this.getClientsCount()+1);
		return myMedicalController.addClient(new Client(currentId,name,fiscalCode,residence,phone,email));
	}
	
	public boolean addCompanyToDatabase(String name, String vat, String address, String city, String province, String zip, String country, String phone, String email){
		String currentId=String.valueOf(this.getCompaniesCount()+1);
		return myMedicalController.addCompany
		(new Company(currentId, name, vat, address, city, province, zip,
				country, phone, email));
	}
	
	public int getCompaniesCount(){
		List<Company> companies = myMedicalController.getAllCompany();
		return companies.size();
	}
	
	public int getClientsCount(){
		List<Client> clients = myMedicalController.getAllClients();
		return clients.size();
	}
	
	
	
	
	
	//   Just console prints!!!
	
	public void printAllClients(){
		System.out.println("In the database Clients:");
		List<Client> patients = myMedicalController.getAllClients();
		patients.
			stream().
			forEach(
				patient -> System.out.println
					("Patient Name : " + patient.getId() + " - " + patient.getName())
			);
		System.out.println("--------/Clients---------");
	}
	
	
	public void printAllCompanies(){
		System.out.println("In the database Companies:");
		List<Company> companies = myMedicalController.getAllCompany();
		companies.
			stream().
			forEach(
				company -> System.out.println
					("Company Name : " + company.getId() + " - " + company.getName())
			);
		System.out.println("--------/Companies---------");

	}
	
	
	
	
}
