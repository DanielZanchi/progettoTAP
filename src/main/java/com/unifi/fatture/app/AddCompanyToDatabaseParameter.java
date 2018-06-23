package com.unifi.fatture.app;

public class AddCompanyToDatabaseParameter {
	private String address;
	private String city;
	private String zip;
	private String country;

	public AddCompanyToDatabaseParameter(String address, String city, String zip, String country) {
		this.setAddress(address);
		this.setCity(city);
		this.setZip(zip);
		this.setCountry(country);
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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