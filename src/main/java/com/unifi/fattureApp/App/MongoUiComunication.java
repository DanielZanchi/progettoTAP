package com.unifi.fattureApp.App;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

public class MongoUiComunication {
	Database database;
	String mongoHost = "localhost";
	CompanyController myCompanyController;

	private Company currentSelectedCompany;
	private Client currentSelectedClient;
	private Invoice currentSelectedInvoice;

	private JComboBox<String> clientsList;
	private JComboBox<String> invoicesList;
	private JLabel companyInfo;

	private int companyCounter = 0;
	private int invoiceCounter = 0;

	private JButton editCompanyButton;
	private LinkedList<JButton> editButtons;

	public MongoUiComunication(boolean testing, String args[]) {
		
		if (args!=null && args.length > 0)
			mongoHost = args[0];

		MongoClient mongoClient = null;
		if(testing) {
			Fongo fongo = new Fongo("mongo server 1");
			mongoClient = fongo.getMongo();
		}else {
			try {
				mongoClient = new MongoClient(mongoHost, 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		try {
			//database = new MongoWrapper(new MongoClient(mongoHost, 27017));
			database = new MongoWrapper(mongoClient);
		} catch (Exception e) {
			System.out.println("Error while connecting to mongoHost");
			e.printStackTrace();
		}

		myCompanyController = new CompanyController(database);
		editButtons = new LinkedList<>();
		
		editCompanyButton=new JButton();
	}

	public boolean addClientToDatabase(String name, String fiscalCode, String residence, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(this.getClientsCount() + 1);
		return myCompanyController.addClient(
				new Client(currentId, name, fiscalCode, residence, city, province, zip, country, phone, email));
	}

	public boolean addCompanyToDatabase(String name, String vat, String address, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(this.getCompaniesCount() + 1);
		return myCompanyController.addCompany(new Company(currentId, name, vat, address, city, province, zip, country, phone, email));
	}

	public boolean addInvoiceToDatabase(String name, String description, String price) {
		String currentId = String.valueOf(this.getInvoicesCount() + 1);
		return myCompanyController.addInvoice(new Invoice(currentId, name, price, description));
	}

	public int getCompaniesCount() {
		List<Company> companies = myCompanyController.getAllCompany();
		return companies.size();
	}

	public int getClientsCount() {
		List<Client> clients = myCompanyController.getAllClients();
		return clients.size();
	}

	public int getInvoicesCount() {
		List<Invoice> invoices = myCompanyController.getAllInvoices();
		return invoices.size();
	}

	public List<Company> getSavedCompanies() {
		return myCompanyController.getAllCompany();
	}

	public List<Client> getSavedClients() {
		return myCompanyController.getAllClients();
	}

	public List<Invoice> getSavedInvoices() {
		return myCompanyController.getAllInvoices();
	}

	
	public boolean printSelected() {
		if (currentSelectedClient != null && currentSelectedCompany != null && currentSelectedInvoice != null) {
			increseInvoiceNumber();
			System.out.println("Invoice Number: " + invoiceCounter);
			System.out.println(" Selected: ");
			System.out.println(" Company : " + currentSelectedCompany.getName());
			System.out.println(currentSelectedCompany.getAddress());
			System.out.println("");
			System.out.println(" Client : " + currentSelectedClient.getName());
			System.out.println(currentSelectedClient.getCityResidence());
			System.out.println("");
			System.out.println(" Invoice : " + currentSelectedInvoice.getName());
			System.out.println(currentSelectedInvoice.getPrice());
			System.out.println(" -------/Selected---------- ");

			new PDFCreator(currentSelectedCompany, currentSelectedClient, currentSelectedInvoice);
			return true;
		}
		return false;
	}

	private void  increseInvoiceNumber() {
		invoiceCounter++;
	}

	public Company getCurrentSelectedCompany() {
		return currentSelectedCompany;
	}

	public void setCurrentSelectedCompany(Company currentSelectedCompany) {
		this.currentSelectedCompany = currentSelectedCompany;
		this.enableEditCompanyButton();
	}

	public Invoice getCurrentSelectedInvoice() {
		return currentSelectedInvoice;
	}

	public void setCurrentSelectedInvoice(Invoice currentSelectedInvoice) {
		this.currentSelectedInvoice = currentSelectedInvoice;
	}

	public Client getCurrentSelectedClient() {
		return currentSelectedClient;
	}

	public void setCurrentSelectedClient(Client currentSelectedClient) {
		this.currentSelectedClient = currentSelectedClient;
	}

	public void updateAllReferences() {
		updateCompanyReference();
		updateClientsReferences();
		updateInvoicesReferences();
	}

	public void updateCompanyReference() {
		if (companyInfo.getText().equals("My Company")) {
			if (this.getSavedCompanies().size() > 0) {
				companyInfo.setText(this.getSavedCompanies().get(0).getName());
				this.setCurrentSelectedCompany(this.getSavedCompanies().get(0));
			}
		} else {
			if (this.getSavedCompanies().size() > 0) {
				companyInfo.setText(this.getSavedCompanies().get(companyCounter).getName());
				this.setCurrentSelectedCompany(this.getSavedCompanies().get(companyCounter));
			}
		}
	}

	public void updateClientsReferences() {
		clientsList.removeAllItems();
		for (int i = 0; i < this.getSavedClients().size(); i++) {
			clientsList.addItem(this.getSavedClients().get(i).getName());
		}
	}

	public void updateInvoicesReferences() {
		invoicesList.removeAllItems();
		for (int i = 0; i < this.getSavedInvoices().size(); i++) {
			invoicesList.addItem(this.getSavedInvoices().get(i).getName());
		}
	}

	public void setClientsList(JComboBox<String> clientsList) {
		this.clientsList = clientsList;
	}

	public void setInvoicesList(JComboBox<String> invoicesList) {
		this.invoicesList = invoicesList;
	}

	public void setCompanyInfo(JLabel companyInfo) {
		this.companyInfo = companyInfo;
	}

	public int getCompanyCounter() {
		return companyCounter;
	}

	public void setCompanyCounter(int companyCounter) {
		this.companyCounter = companyCounter;
		this.updateCompanyReference();
	}

	public void enableEditCompanyButton() {
		editCompanyButton.setEnabled(true);
	}

	public void seteditCompanyButton(JButton editMyCompany_Button) {
		editCompanyButton = editMyCompany_Button;
	}
}