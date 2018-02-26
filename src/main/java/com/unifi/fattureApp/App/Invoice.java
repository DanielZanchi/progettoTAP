package com.unifi.fattureApp.App;

public class Invoice extends User {
	private static final long serialVersionUID = 6436363366345956837L;	
	private String price;
	private String description;

	public Invoice() {
	}

	public Invoice(String id, String name, String price, String description) {
		this.setPrice(price);
		this.setDescription(description);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
		result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
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

		Invoice other = (Invoice) obj;
		if(!compareFieldsForEqualsMethod(getPrice(), other.getPrice())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getDescription(), other.getDescription())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getId(), other.getId())) {
			return false;
		}
		if(!compareFieldsForEqualsMethod(getName(), other.getName())) {
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
		return "Invoice [id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", price=" + getPrice() + "]";
	}  
}