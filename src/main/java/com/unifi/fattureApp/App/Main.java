package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		
		MongoUiComunication myMongoUiComunication = new MongoUiComunication();
		
		//Launch UI
		//MainWindowUI window = new MainWindowUI(myMongoUiComunication);
		
		
		System.out.println("Fatture-app terminates");
		

		
	}
}