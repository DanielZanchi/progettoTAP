package com.unifi.fattureApp.App;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class User implements Serializable{
	private static final long serialVersionUID = 5897984558305166044L;
	public String id;
	public String name;
	
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
		Field[] fields=this.getClass().getDeclaredFields();
		int result = 1;
		for(Field field : fields) {
			field.setAccessible(true);
			try {
				result = primeNumber * result + ((field.get(this)== null) ? 0 : field.get(this).hashCode());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		result=primeNumber*result+((getId()==null) ? 0:getId().hashCode());
		result=primeNumber*result+((getName()==null) ? 0 : getName().hashCode());
		return result;
	}
	
}