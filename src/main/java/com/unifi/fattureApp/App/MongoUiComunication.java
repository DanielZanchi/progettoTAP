package com.unifi.fattureApp.App;

import java.util.List;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoUiComunication {
	Database database;
	String mongoHost = "27017";
	CompanyController myCompanyController;
	
	public MongoUiComunication(){
//		if (args.length > 0)
//			mongoHost = args[0];
//		Database database = null;
//		try {
//			database = new MongoWrapper(new MongoClient(mongoHost));
//		} catch (UnknownHostException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		myCompanyController = new CompanyController(database);
		
		try{
			database = new MongoWrapper(new MongoClient(mongoHost, 27017));
		}catch(Exception e){
			System.out.println("Error while connecting to mongoHost");
			e.printStackTrace();
		}
	
		myCompanyController = new CompanyController(database);
	}
	
	public boolean addClientToDatabase(String name, String fiscalCode, String residence,String city,String province,String zip,String country, String phone, String email){
		String currentId=String.valueOf(this.getClientsCount()+1);
		return myCompanyController.addClient(new Client(currentId,name,fiscalCode,residence,city,province,zip,country,phone,email));
	}
	
	public boolean addCompanyToDatabase(String name, String vat, String address, String city, String province, String zip, String country, String phone, String email){
		String currentId=String.valueOf(this.getCompaniesCount()+1);
		return myCompanyController.addCompany
		(new Company(currentId, name, vat, address, city, province, zip,
				country, phone, email));
	}
	
	public int getCompaniesCount(){
		List<Company> companies = myCompanyController.getAllCompany();
		return companies.size();
	}
	
	public int getClientsCount(){
		List<Client> clients = myCompanyController.getAllClients();
		return clients.size();
	}
	
	//   Just console prints!!!
	
	public void printAllClients(){
		System.out.println("In the database Clients:");
		List<Client> clients = myCompanyController.getAllClients();
		clients.
			stream().
			forEach(
				client -> System.out.println
					("Client Name : " + client.getId() + " - " + client.getName())
			);
		System.out.println("--------/Clients---------");
	}
	
	public void printAllCompanies(){
		System.out.println("In the database Companies:");
		List<Company> companies = myCompanyController.getAllCompany();
		companies.
			stream().
			forEach(
				company -> System.out.println
					("Company Name : " + company.getId() + " - " + company.getName())
			);
		System.out.println("--------/Companies---------");
	}
}