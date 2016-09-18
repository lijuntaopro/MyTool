package com.li.tools.utils.cglib;

import org.springframework.stereotype.Component;
import com.li.tools.utils.cglib.proxy.TransactionManager;
import com.li.tools.utils.jedis.RedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

@Component("redisTransactionManager")
public class RedisTransactionManager implements TransactionManager{
	private Transaction transaction;
	private JedisPool jedisPool = RedisPoolUtil.getInstance();
	public void before(){
		Jedis jedis = jedisPool.getResource();
		transaction = jedis.multi();
		LocalJedisTx.jedisMap.set(transaction);
		System.out.println("before");
	}
	public void after(){
		LocalJedisTx.jedisMap.set(null);
		transaction.exec();
		System.out.println("after");
	}
}
