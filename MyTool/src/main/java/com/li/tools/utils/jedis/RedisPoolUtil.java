package com.li.tools.utils.jedis;
import java.util.Locale;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {
	private static final JedisPool pool;
	private static ResourceBundle prop = ResourceBundle.getBundle("redis", Locale.getDefault());
	static{
		pool = new JedisPool(getConfig(), prop.getString("redis.host"), Integer.parseInt(prop.getString("redis.port")));
	}
	public static JedisPool getInstance(){
		return pool;
	}
	private static JedisPoolConfig getConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(prop.getString("redis.maxIdle")));
		config.setMaxWait(Integer.parseInt(prop.getString("redis.maxWait")));
		config.setMaxActive(Integer.parseInt(prop.getString("redis.maxActive")));
		config.setMinIdle(Integer.parseInt(prop.getString("redis.minIdle")));
		return config;
	}
}
