package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.RedisWrapper;

public abstract class RedisWrapperTestAbstract {
	private RedisWrapper redisDatabase;
	public abstract void createRedis() throws UnknownHostException;
	public abstract void stopRedis() throws UnknownHostException;
	private TestHelperTool redisTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		createRedis();
		redisDatabase = new RedisWrapper();
		redisTestHelper = new TestHelperTool();
		redisTestHelper.usingRedis(redisDatabase);
	}

	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(redisDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		redisTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		redisTestHelper.addClient("2", "second", "secondFC", "secondCR", "secondCity", "secondProvince", "secondZip", "secondCountry", "secondPhone", "secondEmail");
		assertEquals(2, redisDatabase.getAllClientsList().size());
	}

	@After
	public void stopDBServer() {
		try {
			stopRedis();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}