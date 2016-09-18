package com.li.tools.utils.jedis;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {
	private static final JedisPool pool;
	private static final Properties prop = new Properties();
	static{
		loadResource();
		pool = new JedisPool(getConfig(), prop.getProperty("redis.host"), Integer.parseInt(prop.getProperty("redis.port")));
	}
	public static JedisPool getInstance(){
		return pool;
	}
	private static JedisPoolConfig getConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(prop.getProperty("redis.maxIdle")));
		config.setMaxWait(Integer.parseInt(prop.getProperty("redis.maxWait")));
		config.setMaxActive(Integer.parseInt(prop.getProperty("redis.maxActive")));
		config.setMinIdle(Integer.parseInt(prop.getProperty("redis.minIdle")));
		return config;
	}
	private static void loadResource(){
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/redis.properties");
			if(is==null){
				is = new FileInputStream("G:/workspace3/uums-uias/src/main/resources/redis.properties");
			}
			prop.load(is);
		} catch (IOException e) {
			System.out.println("加载redis资源文件失败");
		}
	}
}
