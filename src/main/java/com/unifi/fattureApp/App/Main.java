package com.unifi.fattureApp.App;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.UI.MainWindowUI;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

import com.mongodb.MongoClient;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		
		MongoUiComunication myMongoUiComunication = new MongoUiComunication();
		
		//Launch UI
		//MainWindowUI window = new MainWindowUI(myMongoUiComunication);
		
		
		System.out.println("Fatture-app terminates");
		

		
	}
}