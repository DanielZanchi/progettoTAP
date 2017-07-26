package com.unifi.fattureApp.App;

public class Client {
		private String id;
		private String name;
		private String fiscalCode;
		private String cityResidence;
		//private String birthDate;
		private String phone;
		private String email;
		
		public Client() {

		}

		public Client(String id, String name,String fiscalCode,String cityResidence,String phone,String email/*,String birthDate*/) {
			this.id = id;
			this.name = name;
			this.setFiscalCode(fiscalCode);
			this.setCityResidence(cityResidence);
			this.setPhone(phone);
			this.setEmail(email);
			//this.setBirthDate(birthDate);
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

		/*
		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
		*/
}