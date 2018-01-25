package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.RedisWrapper;

public abstract class RedisWrapperTestAbstract {
	private RedisWrapper redisDatabase;
	public abstract void createRedisClient() throws UnknownHostException;
	private TestHelperTool redisTestHelper;

	@Before
	public void initDB() throws UnknownHostException {
		createRedisClient();
		redisTestHelper = new TestHelperTool();
		redisTestHelper.usingRedis(redisDatabase);
		redisDatabase = new RedisWrapper();
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
}
