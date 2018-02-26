package com.unifi.fattureApp.App;

import java.io.Serializable;

public class Client extends User implements Serializable {	
	private static final long serialVersionUID = 392147957593130325L;
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
		super(id, name);
		this.setFiscalCode(fiscalCode);
		this.setCityResidence(cityResidence);
		this.setCity(city);
		this.setProvince(province);
		this.setZip(zip);
		this.setCountry(country);
		this.setPhone(phone);
		this.setEmail(email);
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

	@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getFiscalCode() == null) ? 0 : getFiscalCode().hashCode());
		result = prime * result + ((getCityResidence() == null) ? 0 : getCityResidence().hashCode());
		result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
		result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
		result = prime * result + ((getZip() == null) ? 0 : getZip().hashCode());
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

		Client other = (Client) obj;
		if(!compareFieldsForEqualsMethod(getId(), other.getId())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getName(), other.getName())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getFiscalCode(), other.getFiscalCode())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getCityResidence(), other.getCityResidence())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getCity(), other.getCity())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getProvince(), other.getProvince())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getZip(), other.getZip())) {
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
		return "Client [id=" + getId() + ", name=" + getName() + ", fiscalCode=" + getFiscalCode() + ", cityResidence=" + getCityResidence() +", city="+getCity()+", province="+getProvince()
		+", zip="+getZip()+", country="+getCountry()+", phone="+getPhone()+", email="+getEmail()+ "]";
	} 
}