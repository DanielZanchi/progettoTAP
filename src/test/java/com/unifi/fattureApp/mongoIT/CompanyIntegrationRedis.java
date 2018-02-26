package com.unifi.fattureApp.mongoIT;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import com.unifi.fattureApp.App.AppController;
import com.unifi.fattureApp.App.Client;
import com.unifi.fattureApp.App.Company;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.App.Invoice;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.MongoWrapper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import redis.embedded.RedisServer;

public class CompanyIntegrationRedis extends AbstractCompanyIntegration{
	
	private RedisServer redisServer;
	
	@Override
	public void init() {
		try {
			redisServer = new RedisServer(6379);
			redisServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mongoTestHelper = new TestHelperTool();
		Database database=mongoTestHelper.usingRedis();
		companyController = new AppController(database);
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.stop();
		
	}
	
	@After
	public void stopDBServer() {
		redisServer.stop();
	}

	

}
