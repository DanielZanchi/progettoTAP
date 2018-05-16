package com.unifi.fattureApp.databaseIT;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.unifi.fattureApp.app.RealDatabaseUiComunication;
import com.unifi.fattureApp.wrappers.RedisWrapper;

import ch.qos.logback.classic.LoggerContext;
import redis.embedded.RedisServer;

public class DatabaseUiComunicationRedisTest extends AbstractDatabaseUiComunicationTest{
	private RedisServer redisServer;

	@Override
	public void setUpDatabase() throws UnknownHostException {
		try {
			redisServer = new RedisServer(6379);
			redisServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.stop();
		String args[] = null;
		myDatabaseUiComunication = new RealDatabaseUiComunication(args, false);
	}

	@Test
	public void testDatabaseIsInstanceOfRedisWrapper() {
		assertEquals(RedisWrapper.class, myDatabaseUiComunication.getCurrentDatabaseClass());
	}

	@After
	public void stopRedis() {
		redisServer.stop();
	}
}