package com.li.tools.utils.jedis.datacache.dao;

import java.util.Set;
import redis.clients.jedis.Jedis;
import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;
import com.li.tools.utils.jedis.datacache.dao.Interfaces.ICacheEntityDao;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public class RedisStringDao implements ICacheEntityDao<RedisStringDao>{
    public <T extends CacheEntity<?>> Set<T> get(Jedis jedis,String key,Class<T> class1){
	byte[] bs = jedis.get(SerializeUtil.serialize(key));
	Set<T> unserialize = (Set<T>) SerializeUtil.unserialize(bs);
	return unserialize;
    }
    public <T extends CacheEntity<?>> String save(Jedis jedis, String key,
	    CacheSet<T> cacheSet) {
	return jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(cacheSet));
    }
}
