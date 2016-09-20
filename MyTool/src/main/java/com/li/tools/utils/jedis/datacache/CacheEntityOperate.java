package com.li.tools.utils.jedis.datacache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.dao.DaoSerializeUtil;
import com.li.tools.utils.jedis.RedisPoolUtil;

/**
 * @author lijuntao
 * @date 2016-9-18
 */
public class CacheEntityOperate {
    public String setCacheSetInArrayList(Jedis jedis,String key,CacheSet<CacheEntity<?>> cacheSet){
	int i = 0 ;
	for(CacheEntity<?> entity:cacheSet.getEntitySet()){
	    jedis.rpush(SerializeUtil.serialize(key), SerializeUtil.serialize(entity));
	    i++;
	}
	return i+"";
    }
    public String setCacheSetInHashSet(Jedis jedis,String key,CacheSet<CacheEntity<?>> cacheSet){
	int i = 0 ;
	for(CacheEntity<?> entity:cacheSet.getEntitySet()){
	    jedis.sadd(SerializeUtil.serialize(key), SerializeUtil.serialize(entity));
	    i++;
	}
	return i+"";
    }
    public String setCacheSetInSortedSet(Jedis jedis,String key,CacheSet<CacheEntity<?>> cacheSet,int range){
	int i = 0 ;
	for(CacheEntity<?> entity:cacheSet.getEntitySet()){
	    jedis.zadd(SerializeUtil.serialize(key), range,SerializeUtil.serialize(entity));
	    i++;
	}
	return i+"";
    }

    public String saveCacheSet(Jedis jedis,String key,CacheSet<CacheEntity<?>> cacheSet){
	return jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(cacheSet));
    }
    public <T> CacheSet<CacheEntity<T>> getCacheSet(Jedis jedis,String key,Class<? extends CacheEntity<T>> class1){
	return (CacheSet<CacheEntity<T>>) SerializeUtil.unserialize(jedis.get(SerializeUtil.serialize(key)));
    }
}
