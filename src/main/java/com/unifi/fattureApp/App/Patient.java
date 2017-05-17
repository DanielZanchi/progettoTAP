package com.unifi.fattureApp.App;

public class Patient {
		private String id;
		private String name;
		private String fiscalCode;
		private String cityResidence;
		private String birthDate;
		
		public Patient() {

		}

		public Patient(String id, String name,String fiscalCode,String cityResidence,String birthDate) {
			this.id = id;
			this.name = name;
			this.setFiscalCode(fiscalCode);
			this.setCityResidence(cityResidence);
			this.setBirthDate(birthDate);
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

		public String getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
		}
}