package com.unifi.fattureApp.App;

import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = 1614942661460585115L;
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

	public Company(String id, String name, String vatCode, String address, String city, String province, String zipCode, String country, String phone, String email) {
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
	
	@Override
	public int hashCode(){
		final int prime = 23;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getVatCode() == null) ? 0 : getVatCode().hashCode());
		result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
		result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
		result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
		result = prime * result + ((getZipCode() == null) ? 0 : getZipCode().hashCode());
		result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Company other = (Company) obj;
		if(!compareFieldsForEqualsMethod(getId(), other.getId())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getName(), other.getName())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getVatCode(), other.getVatCode())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getAddress(), other.getAddress())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getCity(), other.getCity())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getProvince(), other.getProvince())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getZipCode(), other.getZipCode())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getCountry(), other.getCountry())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getPhone(), other.getPhone())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getEmail(), other.getEmail())) {
			return false;
		}
		return true;
	}
	
	private boolean compareFieldsForEqualsMethod(String field,String otherObjectField) {
		if (field == null) {
			if (otherObjectField != null) {
				return false;
			}
		}
		else if (!field.equals(otherObjectField)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return "Client [id=" + getId() + ", name=" + getName() + ", vatCode=" + getVatCode() + ", address=" + getAddress() +", city="+getCity()+", province="+getProvince()
		+", zip="+getZipCode()+", country="+getCountry()+", phone="+getPhone()+", email="+getEmail()+ "]";
	} 
}