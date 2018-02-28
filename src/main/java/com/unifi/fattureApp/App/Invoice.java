package com.unifi.fattureApp.App;

public class Invoice extends User {
	private static final long serialVersionUID = 6436363366345956837L;
	private String price;
	private String description;

	public Invoice() {
	}

	public Invoice(String id, String name, String price, String description) {
		super(id, name);
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
		primeNumber=31;
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

		Invoice other = (Invoice) obj;

		return (compareFieldsForEqualsMethod(getPrice(), other.getPrice())&&compareFieldsForEqualsMethod(getDescription(), other.getDescription())&&compareFieldsForEqualsMethod(getId(), other.getId())&&
				compareFieldsForEqualsMethod(getName(), other.getName()));
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