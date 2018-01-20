package com.unifi.fattureApp.mongoDB;

import java.net.UnknownHostException;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;


public class CouchdbWrapperTests extends CouchdbWrapperTestAbstract{

	@Override
	public CouchDbClient createCouchDbClient() throws UnknownHostException {
	
		CouchDbProperties properties = new CouchDbProperties()
				  .setDbName("testdb")
				  .setCreateDbIfNotExist(true)
				  .setProtocol("http")
				  .setHost("localhost")
				  .setPort(5984)
				  .setMaxConnections(30).setConnectionTimeout(10);
		CouchDbClient couchDbClient=new CouchDbClient(properties);
		return couchDbClient;
	}
}