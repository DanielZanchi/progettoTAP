package com.unifi.fattureApp.App;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.unifi.fattureApp.UI.MainWindowUI;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	public static void main(String[] args) throws UnknownHostException {
		MongoUiComunication mongoUiComunication = new MongoUiComunication(false, args);

		//Launch UI
		MainWindowUI mainWindowUI = null;
		try {
			mainWindowUI = new MainWindowUI(mongoUiComunication);
		}catch (Exception e) {
			System.out.println("In docker container, gui not running");
		}		
//		System.out.println("Fatture-app terminates");
		String s = "Fatture-app terminates";
		LOGGER.debug(s);
	}
}