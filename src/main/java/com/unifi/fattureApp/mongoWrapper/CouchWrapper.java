package com.unifi.fattureApp.mongoWrapper;

import java.util.List;

import org.lightcouch.CouchDbClient;

import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class CouchWrapper implements Database{

	private CouchDbClient couchDbClient;

	public  CouchWrapper(CouchDbClient couchDbClient) {
		this.couchDbClient=couchDbClient;
	}

	@Override
	public List<Client> getAllClientsList() {
		return null;
	}

	@Override
	public Client findClientById(String id) {
		return couchDbClient.find(Client.class, id);
	}

	@Override
	public void saveClient(Client client) {
		couchDbClient.save(client);
	}

	@Override
	public List<Company> getAllCompaniesList() {
		return null;
	}

	@Override
	public Company findCompanyById(String id) {
		return couchDbClient.find(Company.class, id);
	}

	@Override
	public void saveCompany(Company company) {
		couchDbClient.save(company);
	}

	@Override
	public List<Invoice> getAllInvoicesList() {
		return null;
	}

	@Override
	public Invoice findInvoiceById(String id) {
		return couchDbClient.find(Invoice.class, id);
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		couchDbClient.save(invoice);	
	}
}