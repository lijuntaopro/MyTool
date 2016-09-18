package com.li.tools.utils.cglib;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("jedisDao")
public class JedisDao {
	@Resource
	private RedisTemplate redisTemplate;
	public String set(String key,String value){
		return redisTemplate.set(key, value);
	}
	public String get(String key){
		return redisTemplate.get(key);
	}
}
