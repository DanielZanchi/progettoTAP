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
		redisTestHelper = new TestHelperTool();
		redisDatabase=redisTestHelper.usingRedis();
	}

	@Test
	public void testGetAllInvoicesEmpty() {
		assertTrue(redisDatabase.getAllInvoicesList().isEmpty());
	}

	@Test
	public void testGetAllInvoicesNotEmpty() {
		redisTestHelper.addInvoice("1", "first", "10", "description 1");
		redisTestHelper.addInvoice("2", "second", "20", "description 2");
		assertEquals(2, redisDatabase.getAllInvoicesList().size());
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