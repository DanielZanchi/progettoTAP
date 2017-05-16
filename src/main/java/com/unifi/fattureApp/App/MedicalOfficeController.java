package com.unifi.fattureApp.App;

import java.util.List;

public class MedicalOfficeController {
		private Database database;

		public MedicalOfficeController(Database database) {
			this.database = database;
		}

		public List<Patient> getAllPatients() {
			return database.getAllPatientsList();
		}

		public Patient getPatientId(String id) {
			return database.findPatientById(id);
		}
		
	

		public boolean addPatient(Patient patient) {
			if (getPatientId(patient.getId()) != null)
				return false;

			database.save(patient);
			return true;
		}
}