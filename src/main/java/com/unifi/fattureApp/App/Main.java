package com.unifi.fattureApp.App;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.unifi.fattureApp.UI.MainWindowUI;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		DatabaseUiComunication mongoUiComunication;
		if(args.length>1) {//redis
			mongoUiComunication = new DatabaseUiComunication(false, args, false);
		}else {//mongo
			mongoUiComunication = new DatabaseUiComunication(false, args, true);
		}

		//Launch UI
		MainWindowUI mainWindowUI = null;
		try {
			mainWindowUI = new MainWindowUI(mongoUiComunication);
		}catch (Exception e) {
			LOGGER.info("In docker container, gui not running");
		}		
		System.out.println("Fatture-app terminates");
	}
}