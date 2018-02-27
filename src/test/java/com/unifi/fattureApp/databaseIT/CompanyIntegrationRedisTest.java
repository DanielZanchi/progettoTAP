package com.unifi.fattureApp.databaseIT;

import java.io.IOException;

import org.junit.After;
import org.slf4j.LoggerFactory;

import com.unifi.fattureApp.App.AppController;
import com.unifi.fattureApp.App.Database;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;

import ch.qos.logback.classic.LoggerContext;
import redis.embedded.RedisServer;

public class CompanyIntegrationRedisTest extends AbstractCompanyIntegrationTest {
	private RedisServer redisServer;

	public void init() throws IOException {
		redisServer = new RedisServer(6379);
		redisServer.start();
		mongoTestHelper = new TestHelperTool();
		Database database = mongoTestHelper.usingRedis();
		companyController = new AppController(database);
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.stop();
	}
	
	@After
	public void stopDBServer() {
		redisServer.stop();
	}
}