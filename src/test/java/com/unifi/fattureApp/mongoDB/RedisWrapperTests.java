package com.unifi.fattureApp.mongoDB;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.ServerAddress;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;

public class RedisWrapperTests extends RedisWrapperTestAbstract{

	private RedisServer redisServer;
	
	@Override
	public void createRedis() throws UnknownHostException {
		
		try {
			redisServer = new RedisServer(6379);
			redisServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void stopRedis() {
		redisServer.stop();
	}

}
