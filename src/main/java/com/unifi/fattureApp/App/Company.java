package com.unifi.fattureApp.App;

import java.awt.List;
import java.io.Serializable;
import java.util.LinkedList;

public class Company{
	private String id;
	private String name;
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

	public Company(String id, String name,String vatCode,
			String address,String city,String province,String zipCode,String country,String phone,String email){
				this.setId(id);
				this.setName(name);
				this.setVatCode(vatCode);
				this.setAddress(address);
				this.setCity(city);
				this.setProvince(province);
				this.setZipCode(zipCode);
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
	
}