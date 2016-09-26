package com.li.tools.utils.jedis.datacache.operater;

import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;

/**
 * @author lijuntao
 * @date 2016-9-20
 */
public class JedisStringOperater extends JedisOperaterBase{
    public <T extends CacheEntity<?>> Set<T> getCacheEntitySet(Jedis jedis,String key,Class<T> class1){
	byte[] bs = jedis.get(SerializeUtil.serialize(key));
	Set<T> unserialize = (Set<T>) SerializeUtil.unserialize(bs);
	return unserialize;
    }
    public <T extends CacheEntity<?>> String saveCacheEntitySet(Jedis jedis,
	    String key, Set<T> set) {
	return jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(set));
    }

/***			以上是对CacheEntity的操作			***/    
    public String set(Jedis jedis,String key,String value){
	return jedis.set(key,value);
    }
    public String get(Jedis jedis,String key){
	return jedis.get(key);
    }
    public String getRange(Jedis jedis,String key,int start,int end){
	return jedis.getrange(key, start, end);
    }
    public String getSet(Jedis jedis,String key,String value){
	return jedis.getSet(key,value);
    }
    public List<String> getValues(Jedis jedis,String[] keys){
	return jedis.mget(keys);
    }
    public String SetExpire(Jedis jedis,String key,int seconds,String value){
	return jedis.setex(key, seconds, value);
    }
    public long SetIfNotExists(Jedis jedis,String key,String value){
	return jedis.setnx(key, value);
    }
    //offset替换的位置，0代表替换第一个字符
    public long SetRange(Jedis jedis,String key,int offset,String value){
	return jedis.setrange(key, offset, value);
    }
    public long getLength(Jedis jedis,String key){
	return jedis.strlen(key);
    }
    public String setValues(Jedis jedis,String[] keyAndValuePair){
	return jedis.mset(keyAndValuePair);
    }
    public long setValuesIfNotExists(Jedis jedis,String[] keyAndValuePair){
	return jedis.msetnx(keyAndValuePair);
    }
    public long append(Jedis jedis,String key,String value){
	return jedis.append(key, value);
    }
    //redis2.6+
    /*public long setValuesExpireWithMilliSeconds(Jedis jedis,String key,int milliSeconds,String value){
	return jedis.psetex(key,milliSeconds,value);
    }*/
    /***			对数字进行操作			***/
    
    public long incraInt(Jedis jedis,String key){
	return jedis.incr(key);
    }
    public long incrIntBy(Jedis jedis,String key,int number){
	return jedis.incrBy(key,number);
    }
    //redis2.6+
    /*public long incrFloatBy(Jedis jedis,String key,float number){
	return jedis.incrbyfloat(key,number);
    }*/
    public long decraInt(Jedis jedis,String key){
	return jedis.decr(key);
    }
    public long decrIntBy(Jedis jedis,String key,int number){
	return jedis.decrBy(key,number);
    }
    
}
