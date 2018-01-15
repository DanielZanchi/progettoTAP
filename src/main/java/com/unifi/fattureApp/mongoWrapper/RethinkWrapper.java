package com.unifi.fattureApp.mongoWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.RethinkDBConnection;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;

public class RethinkWrapper implements Database { 
	
	private RethinkDB rethinkDB;
	private RethinkDBConnection connection;
	
	public RethinkWrapper(RethinkDB rethinkDB,RethinkDBConnection connection) {
		
		this.rethinkDB=rethinkDB;
		this.connection=connection;
		
		if(rethinkDB.dbList().run(connection).size()==0) {
			rethinkDB.dbCreate("company").run(connection);
			rethinkDB.db("company").tableCreate("companies").run(connection);	
			rethinkDB.db("company").tableCreate("clients").run(connection);	
			rethinkDB.db("company").tableCreate("invoices").run(connection);	
		}
		
		
	}
	
	@Override
	public List<Client> getAllClientsList() {
		
		return null;
	}

	@Override
	public Client findClientById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveClient(Client client) {
		
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
