package com.unifi.fattureApp.mongoDB;

import java.net.UnknownHostException;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;


public class CouchdbWrapperTests extends CouchdbWrapperTestAbstract{

	@Override
	public CouchDbClient createCouchDbClient() throws UnknownHostException {
		CouchDbProperties properties = new CouchDbProperties()
				  .setDbName("lightcouch-db-load")
				  .setCreateDbIfNotExist(true)
				  .setProtocol("http")
				  .setHost("127.0.0.1")
				  .setPort(5984)
				  .setMaxConnections(20);
		//CouchDbClient couchDbClient = new CouchDbClient("testcompany",true,"http","127.0.0.1",5984,"username","password");
		CouchDbClient couchDbClient=new CouchDbClient(properties);
		return couchDbClient;
	}
}