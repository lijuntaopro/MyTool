package com.li.tools.utils.jedis.datacache.service;

import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.dao.RedisMapDao;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
@Service("redisCacheMapService")
public class RedisCacheMapService extends RedisCacheService<RedisMapDao>{
    @Resource(name="iCacheEntityDao")
    private RedisMapDao redisMapDao;
    public <T extends CacheEntity<?>> Set<T> getSomeValuesBykeys(String key,Object[] mapKeys,Class<T> class1){
	Set<T> set = null;
	Jedis jedis2 = pool.getResource();
	try{
	  set = redisMapDao.getSomeValuesBykeysInHashMap(jedis2, key, mapKeys, class1);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
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
    public <T extends CacheEntity<K>,K> Map<K,T> getAllKeyAndValuesCacheSetInHashMap(String key,Class<T> class1){
	Map<K,T> all = null;
	Jedis jedis2 = pool.getResource();
	try{
	    all = redisMapDao.getAllKeyAndValuesCacheSetInHashMap(jedis2, key, class1);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return all;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param class1
     * @return
     * 从redis获取map全部的key值
     */
    public <T extends CacheEntity<K>,K> Set<K> getAllKeys(String key,Class<T> class1){
	Set<K> set2 = null;
	Jedis jedis2 = pool.getResource();
	try{
	    set2 = redisMapDao.getAllKeysCacheSetInHashMap(jedis2, key, class1);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
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
    public <T extends CacheEntity<?>> Set<T> getAllValues(String key,Class<T> class1){
	Set<T> set2 = null;
	Jedis jedis2 = pool.getResource();
	try{
	    set2 = redisMapDao.getAllValuesCacheSetInHashMap(jedis2, key, class1);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return set2;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @return
     * 从redis获取map的长度
     */
    public long getMapLength(String redisKey){
	Jedis jedis2 = pool.getResource();
	long length = 0L;
	try{
	    length = redisMapDao.getMapLengthInHashMap(jedis2,redisKey);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return length;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @return
     * 判断redis获取某个map里的键(redis称其为field)是否存在
     */
    public boolean exitMapKey(String redisKey,String mapKey){
	Jedis jedis2 = pool.getResource();
	try{
	    return redisMapDao.existsMapKeyInHashMap(jedis2, redisKey, mapKey);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return false;
    }
}
