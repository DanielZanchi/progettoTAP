package com.unifi.fattureApp.App;

import java.util.List;

public interface Database {
	public List<Client> getAllClientsList();

	public Client findClientById(String id);

	public void save(Client client);
}