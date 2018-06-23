package com.unifi.fatture.app;

public class AddClientToDatabaseParameter {
	private String residence;
	private String city;

	private String zip;
	private String country;

	public AddClientToDatabaseParameter(String residence, String city, String zip, String country) {
		this.setResidence(residence);
		this.setCity(city);
		this.setZip(zip);
		this.setCountry(country);
	}
	
	public String getResidence() {
		return this.residence;
	}
	
	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}