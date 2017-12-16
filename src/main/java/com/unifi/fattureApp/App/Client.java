package com.unifi.fattureApp.App;

public class Client {
	private String id;
	private String name;
	private String fiscalCode;
	private String cityResidence;
	private String city;
	private String province;
	private String zip;
	private String country;
	private String phone;
	private String email;

	public Client() {
	}

	public Client(String id, String name, String fiscalCode, String cityResidence, String city, String province, String zip, String country, String phone, String email) {
		this.setId(id);
		this.setName(name);
		this.setFiscalCode(fiscalCode);
		this.setCityResidence(cityResidence);
		this.setCity(city);
		this.setProvince(province);
		this.setZip(zip);
		this.setCountry(country);
		this.setPhone(phone);
		this.setEmail(email);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getCityResidence() {
		return cityResidence;
	}

	public void setCityResidence(String cityResidence) {
		this.cityResidence = cityResidence;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	/*
		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
	 */
}