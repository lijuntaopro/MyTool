package com.li.tools.utils.jedis.datacache.dao.Interfaces;

import java.util.Set;

import redis.clients.jedis.Jedis;

import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public interface ICacheEntityDao<T>{
    public <K extends CacheEntity<?>> String save(Jedis jedis,String key,CacheSet<K> cacheSet);
    public <K extends CacheEntity<?>> Set<K> get(Jedis jedis,String key,Class<K> class1);
}
