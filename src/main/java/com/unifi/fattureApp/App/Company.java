package com.unifi.fattureApp.App;

public class Company extends User {
	private static final long serialVersionUID = 1614942661460585115L;
	private String vatCode;
	private String address;
	private String city;
	private String province;
	private String zipCode;
	private String country;
	private String phone;
	private String email;

	public  Company() {
	}

	public Company(String id, String name, String vatCode, String address, String city, String province, String zipCode, String country, String phone, String email) {
		super(id, name);
		this.setVatCode(vatCode);
		this.setAddress(address);
		this.setCity(city);
		this.setProvince(province);
		this.setZipCode(zipCode);
		this.setCountry(country);
		this.setPhone(phone);
		this.setEmail(email);
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode(){
		primeNumber = 3;
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}