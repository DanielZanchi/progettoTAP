package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

import com.unifi.fattureApp.UI.MainWindowUI;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		
		//Launch UI
		new MainWindowUI(new MongoUiComunication());
		
		System.out.println("Fatture-app terminates");
		

		
	}
}