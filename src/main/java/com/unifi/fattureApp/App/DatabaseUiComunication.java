package com.unifi.fattureApp.App;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.wrappers.MongoWrapper;
import com.unifi.fattureApp.wrappers.RedisWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

public class DatabaseUiComunication {
	private static final Logger LOGGER = Logger.getLogger(DatabaseUiComunication.class);
	private Database database;
	private String mongoHost = "localhost";
	private AppController myCompanyController;

	private Company currentSelectedCompany;
	private Client currentSelectedClient;
	private Invoice currentSelectedInvoice;

	private JComboBox<String> clientsList;
	private JComboBox<String> invoicesList;
	private JLabel companyInfo;

	private int companyCounter = 0;

	private JButton editCompanyButton;
	private JButton editClientButton;
	private JButton editInvoiceButton;

	public DatabaseUiComunication(boolean testing, String[] args, boolean usingMongodb) throws UnknownHostException {	
		if(usingMongodb) {
			settingUpMongodb(args, testing);
		}else {
			setUpOtherdb();
		}

		myCompanyController = new AppController(database);
		editCompanyButton = new JButton();
		editClientButton = new JButton();
		editInvoiceButton = new JButton();
		companyInfo = new JLabel();
	}

	private void setUpOtherdb() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("spring-configuration.xml").getPath());
		database = (RedisWrapper)context.getBean("redisWrapper");
		context.close();
	}

	private void settingUpMongodb(String[] args, boolean testing) throws UnknownHostException {
		if (args!=null && args.length > 0)
			mongoHost = args[0];

		MongoClient mongoClient = null;
		if(testing) {
			Fongo fongo = new Fongo("mongo server 1");
			mongoClient = fongo.getMongo();

			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("com.mongodb.FongoDBCollection");
			rootLogger.setLevel(Level.OFF);
		}else {
			mongoClient = new MongoClient(mongoHost, 27017);
		}
		database = new MongoWrapper(mongoClient);
	}

	public boolean addClientToDatabase(String name, String fiscalCode, String residence, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(this.getClientsCount() + 1);
		return myCompanyController.addClient(
				new Client(currentId, name, fiscalCode, residence, city, province, zip, country, phone, email));
	}

	public boolean editClientFromDatabase(String name, String vat, String address, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(currentSelectedClient.getId());
		return myCompanyController.editClient(new Client(currentId, name, vat, address, city, province, zip, country, phone, email));
	}

	public boolean addCompanyToDatabase(String name, String vat, String address, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(this.getCompaniesCount() +1);
		return myCompanyController.addCompany(new Company(currentId, name, vat, address, city, province, zip, country, phone, email));
	}

	public boolean editCompanyFromDatabase(String name, String vat, String address, String city, String province,
			String zip, String country, String phone, String email) {
		String currentId = String.valueOf(currentSelectedCompany.getId());
		boolean saved =  myCompanyController.editCompany(new Company(currentId, name, vat, address, city, province, zip, country, phone, email));
		setCompanyCounter(getCompaniesCount()-1);
		return saved;
	}

	public boolean addInvoiceToDatabase(String name, String description, String price) {
		String currentId = String.valueOf(this.getInvoicesCount() + 1);
		return myCompanyController.addInvoice(new Invoice(currentId, name, price, description));
	}

	public boolean editInvoiceFromDatabase(String name, String price, String description) {
		String currentId = String.valueOf(currentSelectedInvoice.getId());
		return myCompanyController.editInvoice(new Invoice(currentId, name, price, description));
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

	public boolean printSelected(){
		if (currentSelectedClient != null && currentSelectedCompany != null && currentSelectedInvoice != null) {
			try {
				new PDFCreator(currentSelectedCompany, currentSelectedClient, currentSelectedInvoice);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
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
		this.enableEditInvoiceButton();
	}

	public Client getCurrentSelectedClient() {
		return currentSelectedClient;
	}

	public void setCurrentSelectedClient(Client currentSelectedClient) {
		this.currentSelectedClient = currentSelectedClient;
		this.enableEditClientButton();
	}

	public void updateAllReferences() {
		updateCompanyReference();
		updateClientsReferences();
		updateInvoicesReferences();
	}

	public void updateCompanyReference() {
		if (companyInfo.getText().equals("My Company")) {
			if (!this.getSavedCompanies().isEmpty()) {
				companyInfo.setText(this.getSavedCompanies().get(0).getName());
				this.setCurrentSelectedCompany(this.getSavedCompanies().get(0));
			}
		} else {
			if (!this.getSavedCompanies().isEmpty()) {
				companyInfo.setText(this.getSavedCompanies().get(companyCounter).getName());
				this.setCurrentSelectedCompany(this.getSavedCompanies().get(companyCounter));
			}
		}
	}

	public void updateClientsReferences() {
		clientsList.removeAllItems();
		for (int i = 0; i<this.getSavedClients().size(); i++) {
			clientsList.addItem(this.getSavedClients().get(i).getName());
		}
		clientsList.setSelectedIndex(this.getSavedClients().size()-1);
	}

	public void updateInvoicesReferences() {
		invoicesList.removeAllItems();
		for (int i = 0; i<this.getSavedInvoices().size(); i++) {
			invoicesList.addItem(this.getSavedInvoices().get(i).getName());
		}
		invoicesList.setSelectedIndex(this.getSavedInvoices().size()-1);
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

	public void setEditCompanyButton(JButton editMyCompanyButton) {
		editCompanyButton = editMyCompanyButton;
	}

	public void enableEditClientButton() {
		editClientButton.setEnabled(true);
	}

	public void seteditClientButton(JButton editClientButton) {
		this.editClientButton = editClientButton;
	}

	public void enableEditInvoiceButton() {
		editInvoiceButton.setEnabled(true);
	}

	public void seteditInvoiceButton(JButton editInvoiceButton) {
		this.editInvoiceButton = editInvoiceButton;
	}

	public Class<? extends Database> getCurrentDatabaseClass() {
		return database.getClass();
	}
}