package com.unifi.fatture.app;

public class AddCompanyToDatabaseParameter {
	public String address;
	public String city;
	public String zip;
	public String country;

	public AddCompanyToDatabaseParameter(String address, String city, String zip, String country) {
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
}