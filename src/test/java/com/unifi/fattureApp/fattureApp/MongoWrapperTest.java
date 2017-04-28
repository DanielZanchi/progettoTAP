package com.unifi.fattureApp.fattureApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoWrapperTest {
	
	MongoWrapper myMongoWrapper; 
	DBCollection myPatients;
	
	@Before
	public void init(){
		
		Fongo fongo=new Fongo("mongo db 1");
		MongoClient myMongoClient=fongo.getMongo();
		
		DB db=myMongoClient.getDB("medicalOffice");
		db.getCollection("patients").drop();
		
		myMongoWrapper=new MongoWrapper(myMongoClient);
		myPatients=db.getCollection("patients");
	}
	

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
