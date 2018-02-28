package com.unifi.fattureApp.App;

public class Client extends User {	
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
		primeNumber=5;
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;

		return ( compareFieldsForEqualsMethod(getId(), other.getId())&&compareFieldsForEqualsMethod(getName(), other.getName())&&compareFieldsForEqualsMethod(getFiscalCode(), other.getFiscalCode())&&
				compareFieldsForEqualsMethod(getCityResidence(), other.getCityResidence())&&compareFieldsForEqualsMethod(getCity(), other.getCity())&&compareFieldsForEqualsMethod(getProvince(), other.getProvince())&&
				compareFieldsForEqualsMethod(getZip(), other.getZip())&&compareFieldsForEqualsMethod(getCountry(), other.getCountry())&&compareFieldsForEqualsMethod(getPhone(), other.getPhone())&&
				compareFieldsForEqualsMethod(getEmail(), other.getEmail()));
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

}