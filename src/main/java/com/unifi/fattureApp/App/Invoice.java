package com.unifi.fattureApp.App;

import java.io.Serializable;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 6436363366345956837L;
	private String id;
	private String name;
	private String price;
	private String description;

	public Invoice() {
	}

	public Invoice(String id, String name, String price, String description) {
		this.setId(id);
		this.setName(name);
		this.setPrice(price);
		this.setDescription(description);
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

		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());

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

		Invoice other = (Invoice) obj;
		if (price != other.price)
			return false;

		if (description == null) {
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;

		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;

		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Invoice [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}  
}