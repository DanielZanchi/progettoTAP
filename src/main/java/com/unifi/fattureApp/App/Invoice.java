package com.unifi.fattureApp.App;

public class Invoice {
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
}