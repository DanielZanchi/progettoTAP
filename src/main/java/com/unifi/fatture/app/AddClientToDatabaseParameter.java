package com.unifi.fatture.app;

public class AddClientToDatabaseParameter {
	public String residence;
	public String city;
	public String zip;
	public String country;

	public AddClientToDatabaseParameter(String residence, String city, String zip, String country) {
		this.residence = residence;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
}