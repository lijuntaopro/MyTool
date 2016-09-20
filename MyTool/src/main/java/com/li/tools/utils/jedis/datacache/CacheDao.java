package com.li.tools.utils.jedis.datacache;

import javax.annotation.Resource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.li.tools.utils.jedis.RedisPoolUtil;
import com.li.tools.utils.jedis.datacache.dao.RedisListDao;
import com.li.tools.utils.jedis.datacache.dao.RedisMapDao;
import com.li.tools.utils.jedis.datacache.dao.RedisSetDao;
import com.li.tools.utils.jedis.datacache.dao.RedisSortedSetDao;
import com.li.tools.utils.jedis.datacache.dao.RedisStringDao;

/**
 * @author lijuntao
 * @date 2016-9-18
 */
public class CacheDao {
    private final static int REDIS_DAO_FALG_STRING = 1;
    private final static int REDIS_DAO_FALG_HASHSET = 2;
    private final static int REDIS_DAO_FALG_SORTEDSET = 3;
    private final static int REDIS_DAO_FALG_ARRAYLIST = 4;
    private final static int REDIS_DAO_FALG_HASHMAP = 5;
    private JedisPool pool = RedisPoolUtil.getInstance();
    @Resource
    private CacheEntityOperate cacheEntityOperate;
    @Resource
    private RedisListDao redisListDao;
    @Resource
    private RedisMapDao redisMapDao;
    @Resource
    private RedisSetDao redisSetDao;
    @Resource
    private RedisSortedSetDao redisSortedSetDao;
    @Resource
    private RedisStringDao redisStringDao;
    @Resource
    public void setCacheEntityOperate(CacheEntityOperate cacheEntityOperate) {
	this.cacheEntityOperate = cacheEntityOperate;
    }
    public String save(CacheSet<CacheEntity<?>> cacheSet){
	return setCacheSaveInRedis(cacheSet,REDIS_DAO_FALG_STRING,1);
    }
    public String setCacheSetInHashSet(CacheSet<CacheEntity<?>> cacheSet){
	return setCacheSaveInRedis(cacheSet,REDIS_DAO_FALG_HASHSET,1);
    }
    public String setCacheSetInSortedSet(CacheSet<CacheEntity<?>> cacheSet,int range){
	return setCacheSaveInRedis(cacheSet,REDIS_DAO_FALG_SORTEDSET,range);
    }
    public String setCacheSetInArrayList(CacheSet<CacheEntity<?>> cacheSet){
	return setCacheSaveInRedis(cacheSet,REDIS_DAO_FALG_ARRAYLIST,1);
    }
    public String setCacheSetInHashMap(CacheSet<CacheEntity<?>> cacheSet){
	return setCacheSaveInRedis(cacheSet,REDIS_DAO_FALG_HASHMAP,1);
    }
    public String setCacheSaveInRedis(CacheSet<CacheEntity<?>> cacheSet,int flag,int range){
	String key = cacheSet.getPrefix()+cacheSet.getKey()+cacheSet.getSuffix();
	Jedis jedis = null;
	String str = null;
	try{
	    jedis = pool.getResource();
	    switch(flag){
        	case REDIS_DAO_FALG_STRING:str = redisStringDao.save(jedis,key,cacheSet) ;break;
        	case REDIS_DAO_FALG_HASHSET:str = redisSetDao.save(jedis,key,cacheSet) ;break;
        	case REDIS_DAO_FALG_ARRAYLIST:str = redisListDao.save(jedis,key,cacheSet) ;break;
	    	case REDIS_DAO_FALG_HASHMAP:str = redisMapDao.save(jedis,key,cacheSet) ;break;
	    	default:str = "flag数值出错";
	    }
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis!=null)
		pool.returnResource(jedis);
	}
	return str;
    }
    public String getCacheSaveInRedis(CacheSet<CacheEntity<?>> cacheSet,int flag,int range){
	String key = cacheSet.getPrefix()+cacheSet.getKey()+cacheSet.getSuffix();
	Jedis jedis = null;
	String str = null;
	try{
	    jedis = pool.getResource();
	    switch(flag){
        	case REDIS_DAO_FALG_STRING:str = redisStringDao.save(jedis,key,cacheSet) ;break;
        	case REDIS_DAO_FALG_HASHSET:str = redisSetDao.save(jedis,key,cacheSet) ;break;
        	case REDIS_DAO_FALG_ARRAYLIST:str = redisListDao.save(jedis,key,cacheSet) ;break;
	    	case REDIS_DAO_FALG_HASHMAP:str = redisMapDao.save(jedis,key,cacheSet) ;break;
	    	default:str = "flag数值出错";
	    }
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    if(jedis!=null)
		pool.returnResource(jedis);
	}
	return str;
    }
}
