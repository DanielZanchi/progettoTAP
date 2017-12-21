package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

import com.unifi.fattureApp.UI.MainWindowUI;

public class Main {
	public static void main(String[] args) throws UnknownHostException {
		MongoUiComunication mongoUiComunication = new MongoUiComunication(false, args);

		//Launch UI
		MainWindowUI mainWindowUI = null;
		try {
			mainWindowUI=new MainWindowUI(mongoUiComunication);
		}catch (Exception e) {
			System.out.println("In docker container, gui not running");
		}		
		System.out.println("Fatture-app terminates");
	}
}