package com.unifi.fattureApp.App;

import java.util.List;

public class MedicalOfficeController {
		private Database database;

		public MedicalOfficeController(Database database) {
			this.database = database;
		}

		public List<Client> getAllClients() {
			return database.getAllClientsList();
		}

		public Client getClientId(String id) {
			return database.findClientById(id);
		}
		
		public boolean addPatient(Client client) {
			if (getClientId(client.getId()) != null)
				return false;

			database.saveClient(client);
			return true;
		}
		
		
		public List<Company> getAllCompany() {
			return database.getAllCompaniesList();
		}

		public Company getCompanyId(String id) {
			return database.findCompanyById(id);
		}
		
		public boolean addCompany(Company company) {
			if (getCompanyId(company.getId()) != null)
				return false;

			database.saveCompany(company);
			return true;
		}
		
		
}