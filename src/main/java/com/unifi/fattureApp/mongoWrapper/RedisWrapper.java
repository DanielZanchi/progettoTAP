package com.unifi.fattureApp.mongoWrapper;

import java.util.List;

import com.lambdaworks.redis.RedisClient;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class RedisWrapper implements Database{

	public  RedisWrapper(RedisClient rClient) {
	}

	@Override
	public List<Client> getAllClientsList() {
		return null;
	}

	@Override
	public Client findClientById(String id) {
		return null;
	}

	@Override
	public void saveClient(Client client) {		
	}

	@Override
	public List<Company> getAllCompaniesList() {
		return null;
	}

	@Override
	public Company findCompanyById(String id) {
		return null;
	}

	@Override
	public void saveCompany(Company company) {
	}

	@Override
	public List<Invoice> getAllInvoicesList() {
		return null;
	}

	@Override
	public Invoice findInvoiceById(String id) {
		return null;
	}

	@Override
	public void saveInvoice(Invoice invoice) {		
	}
}