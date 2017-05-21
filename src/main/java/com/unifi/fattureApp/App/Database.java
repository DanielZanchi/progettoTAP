package com.unifi.fattureApp.App;

import java.util.List;

public interface Database {
	public List<Client> getAllClientsList();

	public Client findClientById(String id);

	public void saveClient(Client client);
	
	
	public List<Company> getAllCompaniesList();

	public Company findCompanyById(String id);

	public void saveCompany(Company company);
}