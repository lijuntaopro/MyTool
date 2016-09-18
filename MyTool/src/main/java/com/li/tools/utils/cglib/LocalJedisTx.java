package com.li.tools.utils.cglib;
import redis.clients.jedis.Transaction;

public class LocalJedisTx {
	public static ThreadLocal<Transaction> jedisMap = new ThreadLocal<Transaction>();
}
