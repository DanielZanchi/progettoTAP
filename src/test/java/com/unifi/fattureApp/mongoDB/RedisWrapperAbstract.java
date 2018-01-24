package com.unifi.fattureApp.mongoDB;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.unifi.fattureApp.helpTestTools.TestHelperTool;
import com.unifi.fattureApp.mongoWrapper.RedisWrapper;

public abstract class RedisWrapperAbstract {
	private RedisWrapper redisDatabase;

	public abstract MongoClient createRedisClient() throws UnknownHostException;

	private TestHelperTool mongoTestHelper;
}
