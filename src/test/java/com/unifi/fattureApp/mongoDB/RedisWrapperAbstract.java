package com.unifi.fattureApp.mongoDB;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.RedisWrapper;

public abstract class RedisWrapperAbstract {
	private RedisWrapper redisDatabase;
	public abstract void createRedisClient() throws UnknownHostException;
	private TestHelperTool mongoTestHelper;
	
	@Before
	public void initDB() throws UnknownHostException {
		createRedisClient();
		mongoTestHelper=new TestHelperTool();
		mongoTestHelper.usingRedis(redisDatabase);
		redisDatabase = new RedisWrapper();
	}
	
	@Test
	public void testGetAllClientsEmpty() {
		assertTrue(redisDatabase.getAllClientsList().isEmpty());
	}

	@Test
	public void testGetAllClientsNotEmpty() {
		mongoTestHelper.addClient("1", "first", "firstFC", "firstCR", "firstCity", "firstProvince", "firstZip", "firstCountry", "firstPhone", "firstEmail");
		mongoTestHelper.addClient("2", "second", "secondFC", "secondCR", "secondCity", "secondProvince", "secondZip", "secondCountry", "secondPhone", "secondEmail");
		assertEquals(2, redisDatabase.getAllClientsList().size());
	}
}
