package com.li.tools.utils.jedis.datacache.dao;

import java.util.List;
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
public abstract class RedisCollectionDao<T> implements ICacheEntityDao<T>{
    private final static int SAVE_ONCE_SIZE_MAX = 300;
    public abstract long add(Jedis jedis,byte[] bs, byte[][] bbs);
    public abstract <T extends CacheEntity<?>> Set<T> getAllElements(Jedis jedis,String key,Class<T> class1);
    public <T extends CacheEntity<?>> String save(Jedis jedis, String key,CacheSet<T> cacheSet) {
	Set<T> set = cacheSet.getEntitySet();
	List<byte[][]> collection = DaoSerializeUtil.serializeCollection(set,SAVE_ONCE_SIZE_MAX);
	Long rpush = 0L;
	for(byte[][] bss:collection){
	    rpush += jedis.sadd(SerializeUtil.serialize(key), bss);
	}
	return ""+rpush;
    }
    public <T extends CacheEntity<?>> Set<T> get(Jedis jedis,String key,Class<T> class1){
	return getAllElements(jedis,key,class1);
    }
}
