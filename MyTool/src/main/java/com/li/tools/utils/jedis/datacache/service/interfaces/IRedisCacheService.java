package com.li.tools.utils.jedis.datacache.service.interfaces;

import java.util.Set;

import redis.clients.jedis.Jedis;

import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public interface IRedisCacheService<K> {
    public <T extends CacheEntity<?>> String save(CacheSet<T> cacheSet);
    public <T extends CacheEntity<?>> Set<T> get(String key,Class<T> class1);
}
