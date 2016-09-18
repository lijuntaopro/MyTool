package com.li.tools.utils.cglib.proxy;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class ThreadMap {
	private static final Map<Thread,Jedis> map = new HashMap<Thread,Jedis>();
	public static final Jedis getJedis(){
		return map.get(Thread.currentThread());
	}
	public static final void setJedis(Jedis jedis){
		map.put(Thread.currentThread(),jedis);
	}
}
