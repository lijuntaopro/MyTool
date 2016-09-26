package com.li.tools.utils.jedis.datacache.template;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.operater.JedisStringOperater;

/**
 * @author lijuntao
 * @date 2016-9-20
 */
@Component("redisStringTemplate")
public class RedisStringTemplate extends RedisTemplateBase{
    protected JedisStringOperater jedisStringOperater = new JedisStringOperater();
    public <T extends CacheEntity<?>> Set<T> getCacheEntitySet(String key,Class<T> class1){
	return jedisStringOperater.getCacheEntitySet(ThreadLocalPool.thresdJedis.get(), key, class1);
    }
    public <T extends CacheEntity<?>> String saveCacheEntitySet(
	    String key, Set<T> set) {
	return jedisStringOperater.saveCacheEntitySet(ThreadLocalPool.thresdJedis.get(), key, set);
    }

/***			以上是对CacheEntity的操作			***/    
    public String set(String key,String value){
	return jedisStringOperater.set(ThreadLocalPool.thresdJedis.get(), key, value);
    }
    public String get(String key){
	return jedisStringOperater.get(ThreadLocalPool.thresdJedis.get(), key);
    }
    public String getRange(String key,int start,int end){
	return jedisStringOperater.getRange(ThreadLocalPool.thresdJedis.get(), key, start, end);
    }
    public String getSet(String key,String value){
	return jedisStringOperater.getSet(ThreadLocalPool.thresdJedis.get(), key, value);
    }
    public List<String> getValues(String[] keys){
	return jedisStringOperater.getValues(ThreadLocalPool.thresdJedis.get(), keys);
    }
    public String SetExpire(String key,int seconds,String value){
	return jedisStringOperater.SetExpire(ThreadLocalPool.thresdJedis.get(), key, seconds, value);
    }
    public long SetIfNotExists(String key,String value){
	return jedisStringOperater.SetIfNotExists(ThreadLocalPool.thresdJedis.get(), key, value);
    }
    //offset替换的位置，0代表替换第一个字符
    public long SetRange(String key,int offset,String value){
	return jedisStringOperater.SetRange(ThreadLocalPool.thresdJedis.get(), key, offset, value);
    }
    public long getLength(String key){
	return jedisStringOperater.getLength(ThreadLocalPool.thresdJedis.get(), key);
    }
    public String setValues(String[] keyAndValuePair){
	return jedisStringOperater.setValues(ThreadLocalPool.thresdJedis.get(), keyAndValuePair);
    }
    public long setValuesIfNotExists(String[] keyAndValuePair){
	return jedisStringOperater.setValuesIfNotExists(ThreadLocalPool.thresdJedis.get(), keyAndValuePair);
    }
    public long append(String key,String value){
	return jedisStringOperater.append(ThreadLocalPool.thresdJedis.get(), key, value);
    }
    //redis2.6+
    /*public long setValuesExpireWithMilliSeconds(String key,int milliSeconds,String value){
	return jedis.psetex(key,milliSeconds,value);
    }*/
    /***			对数字进行操作			***/
    
    public long incraInt(String key){
	return jedisStringOperater.incraInt(ThreadLocalPool.thresdJedis.get(), key);
    }
    public long incrIntBy(String key,int number){
	return jedisStringOperater.incrIntBy(ThreadLocalPool.thresdJedis.get(), key, number);
    }
    //redis2.6+
    /*public long incrFloatBy(String key,float number){
	return jedis.incrbyfloat(key,number);
    }*/
    public long decraInt(String key){
	return jedisStringOperater.decraInt(ThreadLocalPool.thresdJedis.get(), key);
    }
    public long decrIntBy(String key,int number){
	return jedisStringOperater.decrIntBy(ThreadLocalPool.thresdJedis.get(), key, number);
    }
}
