package com.li.tools.utils.jedis.datacache.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import com.li.tools.utils.jedis.RedisPoolUtil;
import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;
import com.li.tools.utils.jedis.datacache.ChannelCacheEntity;
import com.li.tools.utils.jedis.datacache.dao.Interfaces.ICacheEntityDao;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
@Component("iCacheEntityDao")
public class RedisMapDao implements ICacheEntityDao<RedisMapDao>{
    /***					HashMap操作起始线				***/
    /**
     * 
     * @param jedis
     * @param key
     * @param cacheSet
     * @return
     * 把cacheSet存放在redis的map集合
     */
    public <T extends CacheEntity<?>> String save(Jedis jedis,String key,CacheSet<T> cacheSet){
	Map<byte[],byte[]> map = DaoSerializeUtil.cacheSetToMap(cacheSet);
	return jedis.hmset(SerializeUtil.serialize(key),map);
    }
    public <T extends CacheEntity<?>> Set<T> get(Jedis jedis,String key,Class<T> class1){
	return getAllValuesCacheSetInHashMap(jedis,key,class1);
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param mapKeys
     * @param class1
     * @return
     * 从redis获取map的多个values值，存放在CacheSet
     */
    public <T extends CacheEntity<?>> Set<T> getSomeValuesBykeysInHashMap(Jedis jedis,String key,Object[] mapKeys,Class<T> class1){
	List<byte[]> list = jedis.hmget(SerializeUtil.serialize(key),DaoSerializeUtil.objectsToBytess(mapKeys));
	if(list==null||list.size()==0)
	    return null;
	Set<T> set = new HashSet<T>();
	for(byte[] bs:list){
	    T object = (T)SerializeUtil.unserialize(bs);
	    set.add(object);
	}
	return set;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param class1
     * @return
     * 从redis获取map全部的键值对
     */
    public <T extends CacheEntity<K>,K> Map<K,T> getAllKeyAndValuesCacheSetInHashMap(Jedis jedis,String key,Class<T> class1){
	Map<byte[], byte[]> all = jedis.hgetAll(SerializeUtil.serialize(key));
	return DaoSerializeUtil.bytesMapToObjMap(all,class1);
    }
    public void test(){
	Map<String, ChannelCacheEntity> map = getAllKeyAndValuesCacheSetInHashMap(RedisPoolUtil.getInstance().getResource(),"123",ChannelCacheEntity.class);
	
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param class1
     * @return
     * 从redis获取map全部的key值
     */
    public <T extends CacheEntity<K>,K> Set<K> getAllKeysCacheSetInHashMap(Jedis jedis,String key,Class<T> class1){
	Set<byte[]> set = jedis.hkeys(SerializeUtil.serialize(key));
	Set<K> set2 = DaoSerializeUtil.keysBytesToObjects(set,class1);
	return set2;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param class1
     * @return
     * 从redis获取map全部的values值
     */
    public <T extends CacheEntity<?>> Set<T> getAllValuesCacheSetInHashMap(Jedis jedis,String key,Class<T> class1){
	List<byte[]> list = jedis.hvals(SerializeUtil.serialize(key));
	Set<T> set = DaoSerializeUtil.valuesBytesToObjects(list,class1);
	return set;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @return
     * 从redis获取map的长度
     */
    public long getMapLengthInHashMap(Jedis jedis,String redisKey){
	return jedis.hlen(SerializeUtil.serialize(redisKey));
    }
    /**
     * 
     * @param jedis
     * @param key
     * @return
     * 判断redis获取某个map里的键(redis称其为field)是否存在
     */
    public boolean existsMapKeyInHashMap(Jedis jedis,String redisKey,String mapKey){
	return jedis.hexists(SerializeUtil.serialize(redisKey), SerializeUtil.serialize(mapKey));
    }
    
}
