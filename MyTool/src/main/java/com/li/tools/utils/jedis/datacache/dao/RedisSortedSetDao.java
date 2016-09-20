package com.li.tools.utils.jedis.datacache.dao;

import java.util.List;
import java.util.Set;

import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;

import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public class RedisSortedSetDao extends RedisCollectionDao<RedisSortedSetDao>{
    @Override
    public long add(Jedis jedis, byte[] bs, byte[][] bbs) {
	return 0L;
    }
    public <T extends CacheEntity<?>> Set<T> getAllElements(Jedis jedis,String key,Class<T> class1){
	Set<byte[]> set = jedis.smembers(SerializeUtil.serialize(key));
	Set<T> set2 = DaoSerializeUtil.valuesBytesToObjects(set, class1);
	return set2;
    }
    public <T extends CacheEntity<?>> Set<T> getRangeElements(Jedis jedis,String key,int start,int stop,Class<T> class1){
	Set<byte[]> set = jedis.zrange(SerializeUtil.serialize(key),start,stop);
	Set<T> set2 = DaoSerializeUtil.valuesBytesToObjects(set, class1);
	return set2;
    }
    public <T extends CacheEntity<?>> Set<T> get(Jedis jedis, String key,
	    Class<T> class1) {
	return null;
    }
    public long getElementsSize(Jedis jedis,String key){
   	return jedis.zcard(SerializeUtil.serialize(key));
    }
    public long getElementsSizeByRange(Jedis jedis,String key,int min,int max){
	return jedis.zcount(SerializeUtil.serialize(key),min,max);
    }
    public long removeElements(Jedis jedis,String key,CacheSet<?> set){
	byte[][] collection = DaoSerializeUtil.serializeCollection(set.getEntitySet());
   	return jedis.zrem(SerializeUtil.serialize(key), collection);
    }
}
