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
public class RedisListDao extends RedisCollectionDao<RedisListDao> implements ICacheEntityDao<RedisListDao>{
    private final static int SAVE_ONCE_SIZE_MAX = 300;
    public long add(Jedis jedis, byte[] bs, byte[][] bbs) {
	return jedis.rpush(bs, bbs);
    }
    @Override
    public <T extends CacheEntity<?>> Set<T> getAllElements(Jedis jedis,
	    String key, Class<T> class1) {
	return getRangeElements(jedis,key,0,-1,class1);
    }
    
    public <T extends CacheEntity<?>> Set<T> getRangeElements(Jedis jedis,String key,int start,int end,Class<T> class1){
	List<byte[]> list = jedis.lrange(SerializeUtil.serialize(key), start, end);
	Set<T> set2 = DaoSerializeUtil.valuesBytesToObjects(list, class1);
	return set2;
    }
    public String setElementbByIndexInList(Jedis jedis,String key,int index,CacheEntity<?> entity){
	String string = jedis.lset(SerializeUtil.serialize(key), index, SerializeUtil.serialize(entity));
	return string;
    }
    public <T extends CacheEntity<?>> T getElementByIndexInList(Jedis jedis,String key,int index,Class<T> class1){
	byte[] bs = jedis.lindex(SerializeUtil.serialize(key), index);
	T unserialize = (T) SerializeUtil.unserialize(bs);
	return unserialize;
    }
    public <T extends CacheEntity<?>> T getAndrRemoveFirstElementInList(Jedis jedis,String key,Class<T> class1){
	byte[] bs = jedis.lpop(SerializeUtil.serialize(key));
	T unserialize = (T) SerializeUtil.unserialize(bs);
	return unserialize;
    }
    public <T extends CacheEntity<?>> T getAndrRemoveLastElementInList(Jedis jedis,String key,Class<T> class1){
	byte[] bs = jedis.rpop(SerializeUtil.serialize(key));
	T unserialize = (T) SerializeUtil.unserialize(bs);
	return unserialize;
    }
    public String removeRangeElementsInList(Jedis jedis,String key,int start,int end){
	String string = jedis.ltrim(SerializeUtil.serialize(key), start, end);
	return string;
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param count：表示要移除的个数。当count=0：移除全部相等的元素。count正负代表：正从头到尾，负从尾到头，删除count的绝对值个数
     * @param entity
     * @return
     */
    public long removeElementsByValueInList(Jedis jedis,String key,int count,CacheEntity<?> entity){
	Long lrem = jedis.lrem(SerializeUtil.serialize(key), count, SerializeUtil.serialize(entity));
	return lrem;
    }
}