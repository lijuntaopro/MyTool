package com.li.tools.utils.jedis.datacache.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.li.tools.utils.jedis.RedisPoolUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;
import com.li.tools.utils.jedis.datacache.dao.RedisStringDao;
import com.li.tools.utils.jedis.datacache.dao.Interfaces.ICacheEntityDao;
import com.li.tools.utils.jedis.datacache.service.interfaces.IRedisCacheService;

/**
 * @author lijuntao
 * @date 2016-9-20
 */
@Service("redisStringService")
public class RedisStringService implements IRedisCacheService<RedisStringDao>{
    @Resource
    protected RedisStringDao redisStringDao;
    protected JedisPool pool = RedisPoolUtil.getInstance();
    public <T extends CacheEntity<?>> String save(CacheSet<T> cacheSet) {
	Jedis jedis2 = pool.getResource();
	String string = null;
	String key = cacheSet.getPrefix() + cacheSet.getKey() + cacheSet.getSuffix();
	try{
	    string = redisStringDao.save(jedis2, key, cacheSet);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return string;
    }
    public <T extends CacheEntity<?>> Set<T> get(String key,Class<T> class1) {
	Jedis jedis2 = pool.getResource();
	Set<T> set = null;
	try{
	    set = redisStringDao.get(jedis2, key, class1);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis2!=null)
		pool.returnResource(jedis2);
	}
	return set;
    }
}
