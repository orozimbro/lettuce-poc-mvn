package com.ms.poc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LettucePocMvnApplicationTests {

	@Test
	public void contextLoads() {
        // Syntax: redis://[password@]host[:port][/databaseNumber]
        RedisClient redisClient = RedisClient.create(RedisURI.create("redis://localhost:6379/0"));

        StatefulRedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");

        RedisCommands<String, String> sync = connection.sync();

        sync.set("foo", "bar");
        String value = sync.get("foo");
        System.out.println(value);

        connection.close();
        redisClient.shutdown();
	}

}

