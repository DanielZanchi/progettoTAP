package com.unifi.fattureApp.App;

import java.io.Serializable;
import java.lang.reflect.Field;

public class User implements Serializable{
	private static final long serialVersionUID = 5897984558305166044L;
	private String id;
	private String name;
	
	protected int primeNumber=3;

	public User() {	
	}

	public User(String id, String name) {
		this.setId(id);
		this.setName(name);
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
	
	@Override
	public int hashCode() {
		Field[] fields=this.getClass().getFields();
		int result = 1;
		for(Field field : fields) {
			result = primeNumber * result + ((field == null) ? 0 : field.hashCode());
		}
		return super.hashCode();
	}
}