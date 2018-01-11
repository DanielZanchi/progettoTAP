package com.unifi.fattureApp.mongoWrapper;

import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class OrientWrapper implements Database{
	
	
	public  OrientWrapper() {
		ODatabaseDocumentTx db = new ODatabaseDocumentTx("remote:localhost/company").open("admin", "admin");


	}

	@Override
	public List<Client> getAllClientsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findClientById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveClient(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Company> getAllCompaniesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company findCompanyById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCompany(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Invoice> getAllInvoicesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice findInvoiceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		
	}

}
