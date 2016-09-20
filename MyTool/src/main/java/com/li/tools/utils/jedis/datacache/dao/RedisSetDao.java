package com.li.tools.utils.jedis.datacache.dao;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public class RedisSetDao extends RedisCollectionDao<RedisSetDao>{
    public long add(Jedis jedis, byte[] bs, byte[][] bbs) {
	return jedis.sadd(bs, bbs);
    }
    public <T extends CacheEntity<?>> Set<T> getAllElements(Jedis jedis,String key,Class<T> class1){
	Set<byte[]> set = jedis.smembers(SerializeUtil.serialize(key));
	Set<T> set2 = DaoSerializeUtil.valuesBytesToObjects(set, class1);
	return set2;
    }
    public <T extends CacheEntity<?>> Set<T> getRangeElements(Jedis jedis,
	    String key, int start, int stop, Class<T> class1) {
	return getAllElements(jedis,key,class1);
    }
    public <T extends CacheEntity<?>> CacheEntity<?> getRandomElementAndRemove(Jedis jedis,String key,Class<T> class1){
	byte[] bs = jedis.spop(SerializeUtil.serialize(key));
	T object = (T) SerializeUtil.unserialize(bs);
	return object;
    }
    public long getElementsSize(Jedis jedis,String key){
	return jedis.scard(SerializeUtil.serialize(key));
    }
    public boolean isContainElements(Jedis jedis,String key,CacheEntity<?> entity){
	return jedis.sismember(SerializeUtil.serialize(key),SerializeUtil.serialize(entity));
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param count
     * @param class1
     * @return
     * redis 2.6之前
     */
    public <T extends CacheEntity<?>> T getRandomElements(Jedis jedis,String key,int count,Class<T> class1){
	byte[] bs = jedis.srandmember(SerializeUtil.serialize(key));
	T t = (T)SerializeUtil.unserialize(bs);
	return t;
    }
    public long removeElements(Jedis jedis,String key,CacheSet<?> set){
	byte[][] collection = DaoSerializeUtil.serializeCollection(set.getEntitySet());
	return jedis.srem(SerializeUtil.serialize(key), collection);
    }
    
}
