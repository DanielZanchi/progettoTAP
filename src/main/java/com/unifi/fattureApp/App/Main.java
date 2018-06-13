package com.unifi.fattureApp.App;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.UI.MainWindowUI;
import com.unifi.fattureApp.wrappers.MongoWrapper;
import com.unifi.fattureApp.wrappers.RedisWrapper;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	public static void main(String[] args) throws IOException {
		DatabaseUiComunication mongoUiComunication;
		String mongoHost = "localhost";

		if(args.length==1 && args[0].equals("redis")) {//redis
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("spring-configuration.xml").getPath());
			Database database = (RedisWrapper)context.getBean("redisWrapper");
			context.close();
			mongoUiComunication = new DatabaseUiComunication(database);

		} else {//mongo
			if (args.length > 0) {
				mongoHost = args[0];			
			}
			MongoClient mongoClient = new MongoClient(mongoHost, 27017);
			Database database = new MongoWrapper(mongoClient);
			mongoUiComunication = new DatabaseUiComunication(database);
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