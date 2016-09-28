package com.li.tools.utils.jedis.datacache.operater;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-27
 */
public class JedisSetOperater extends JedisOperaterBase{
    public long add(Jedis jedis,String key,String... values){
	return jedis.sadd(key, values);
    }
    public long add(Jedis jedis,byte[] key,byte[]... values){
	return jedis.sadd(key, values);
    }
    public long size(Jedis jedis,String key){
	return jedis.scard(key);
    }
    public long size(Jedis jedis,byte[] key){
	return jedis.scard(key);
    }
    public boolean isContained(Jedis jedis,String key,String value){
   	return jedis.sismember(key, value);
    }
    public boolean isContained(Jedis jedis,byte[] key,byte[] value){
	return jedis.sismember(key, value);
    }
    public Set<String> getAllValues(Jedis jedis,String key){
	return jedis.smembers(key);
    }
    public Set<byte[]> getAllValues(Jedis jedis,byte[] key){
	return jedis.smembers(key);
    }
    public long moveToAnotherList(Jedis jedis,String key,String targetkey,String value){
	return jedis.smove(key, targetkey, value);
    }
    public long moveToAnotherList(Jedis jedis,byte[] key,byte[] targetkey,byte[] value){
	return jedis.smove(key, targetkey, value);
    }
    public String popRandomValue(Jedis jedis,String key){
	return jedis.spop(key);
    }
    public byte[] popRandomValue(Jedis jedis,byte[] key){
	return jedis.spop(key);
    }
    public String getRandomValues(Jedis jedis,String key){
	return jedis.srandmember(key);
    }
    public byte[] getRandomValues(Jedis jedis,byte[] key){
	return jedis.srandmember(key);
    }
    public long removeByValues(Jedis jedis,String key,String... values){
	return jedis.srem(key, values);
    }
    public long removeByValues(Jedis jedis,byte[] key,byte[]... values){
	return jedis.srem(key, values);
    }
    //未实现
    public long getByMatch(Jedis jedis,String key){
	return 0;
    }
    /****			两个集合之间的操作			***/
    //求差集 ，相当于key1 - key2 -......
    public Set<String> diff(Jedis jedis,String... keys){
	return jedis.sdiff(keys);
    }
    public Set<byte[]> diff(Jedis jedis,byte[]... keys){
	return jedis.sdiff(keys);
    }
    public long diffStore(Jedis jedis,String targetKey,String... keys){
	return jedis.sdiffstore(targetKey, keys);
    }
    public long diffStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedis.sdiffstore(targetKey, keys);
    }
    //求交集
    public Set<String> inter(Jedis jedis,String... keys){
	return jedis.sinter(keys);
    }
    public Set<byte[]> inter(Jedis jedis,byte[]... keys){
	return jedis.sinter(keys);
    }
    public long interStore(Jedis jedis,String targetKey,String... keys){
	return jedis.sinterstore(targetKey, keys);
    }
    public long interStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedis.sinterstore(targetKey, keys);
    }
    //求并集
    public Set<String> union(Jedis jedis,String... keys){
	return jedis.sunion(keys);
    }
    public Set<byte[]> union(Jedis jedis,byte[]... keys){
	return jedis.sunion(keys);
    }
    public long unionStore(Jedis jedis,String targetKey,String... keys){
	return jedis.sunionstore(targetKey, keys);
    }
    public long unionStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedis.sunionstore(targetKey, keys);
    }
}
