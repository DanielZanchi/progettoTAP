package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.fattureApp.Database;

public class MedicalOfficeIntegrationTest {
	
	private Database database;
	
	@Before 
	public void init() throws Exception{
		Fongo fongo=new Fongo("mongo server 1");
		MongoClient myMongoClient=fongo.getMongo();
		
		DB db=myMongoClient.getDB("medicalOffice");
		db.getCollection("patients").drop();
		
		
		
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
