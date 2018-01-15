package com.unifi.fattureApp.mongoDB;

import java.net.UnknownHostException;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;


public class CouchdbWrapperTests extends CouchdbWrapperTestAbstract{

	@Override
	public CouchDbClient createCouchDbClient() throws UnknownHostException {
		CouchDbClient couchDbClient=new CouchDbClient(new CouchDbProperties().setDbName("testcompany").setPort(2017).setHost("localhost"));
		return couchDbClient;
	}

}
