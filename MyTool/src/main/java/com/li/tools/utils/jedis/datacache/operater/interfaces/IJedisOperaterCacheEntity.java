package com.li.tools.utils.jedis.datacache.operater.interfaces;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.li.tools.utils.jedis.datacache.CacheEntity;

import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-20
 */
@Component("jedisStringOperater")
public interface IJedisOperaterCacheEntity{
    public <T extends CacheEntity<?>> Set<T> getCacheEntitySet(Jedis jedis,String key,Class<T> class1);
    public <T extends CacheEntity<?>> String saveCacheEntitySet(Jedis jedis, String key,Set<T> set);
}
