package com.unifi.fattureApp.mongoDB;

import java.io.IOException;
import java.net.UnknownHostException;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;

public class RedisWrapperTests extends RedisWrapperAbstract{

	@Override
	public void createRedisClient() throws UnknownHostException {
		RedisExecProvider customProvider = RedisExecProvider.defaultProvider();
		try {
			RedisServer redisServer = new RedisServer(customProvider, 6379);
			redisServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
