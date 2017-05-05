package com.unifi.fattureApp.App;

import java.util.List;

public interface Database {
	public List<Patient> getAllPatientsList();

	public Patient findPatientById(String id);

	public void save(Patient patient);
}