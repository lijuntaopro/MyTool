package com.li.tools.utils.jedis.datacache.operater;
import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-23
 */
public class JedisMapOperater extends JedisOperaterBase{
    public long set(Jedis jedis,String key,String field,String value){
	return jedis.hset(key, field, value);
    }
    public long set(Jedis jedis,byte[] key,byte[] field,byte[] value){
	return jedis.hset(key, field, value);
    }
    public long setValuesIfNotExists(Jedis jedis,String key,String field,String value){
	return jedis.hsetnx(key, field, value);
    }
    public long setValuesIfNotExists(Jedis jedis,byte[] key,byte[] field,byte[] value){
	return jedis.hsetnx(key, field, value);
    }
    public String setMap(Jedis jedis,String key,Map<String,String> map){
	return jedis.hmset(key, map);
    }
    public long setMap(Jedis jedis,byte[] key,byte[] field,byte[] value){
	return jedis.hset(key, field, value);
    }
    public String get(Jedis jedis,String key,String field){
	return jedis.hget(key, field);
    }
    public byte[] get(Jedis jedis,byte[] key,byte[] field){
	return jedis.hget(key,field);
    }
    public List<String> getSomeByfields(Jedis jedis,String key,String field){
	return jedis.hmget(key, field);
    }
    public List<byte[]> getSomeByfields(Jedis jedis,byte[] key,byte[] field){
	return jedis.hmget(key,field);
    }
    public Map<String, String> getWholeMapAll(Jedis jedis,String key){
	return jedis.hgetAll(key);
    }
    public Map<byte[],byte[]> getWholeMapAll(Jedis jedis,byte[] key){
	return jedis.hgetAll(key);
    }
    public long del(Jedis jedis,String key,String... field){
	return jedis.hdel(key, field);
    }
    public long del(Jedis jedis,byte[] key,byte[]... field){
	return jedis.hdel(key, field);
    }
    public Set<String> getAllFieldKey(Jedis jedis,String key){
	return jedis.hkeys(key);
    }
    public Set<byte[]> getAllFieldKey(Jedis jedis,byte[] key){
	return jedis.hkeys(key);
    }
    public boolean existsField(Jedis jedis,String key,String field){
	return jedis.hexists(key, field);
    }
    public boolean existsField(Jedis jedis,byte[] key,byte[] field){
	return jedis.hexists(key, field);
    }
    public long getLength(Jedis jedis,String key){
	return jedis.hlen(key);
    }
    public long getLength(Jedis jedis,byte[] key){
	return jedis.hlen(key);
    }
    public List<String> getAllValues(Jedis jedis,String key){
	return jedis.hvals(key);
    }
    public List<byte[]> getAllValues(Jedis jedis,byte[] key){
	return jedis.hvals(key);
    }
    
    
    /****			只对map的value值为数字操作				***/
    public long incrBy(Jedis jedis,String key,String field,long value){
	return jedis.hincrBy(key, field, value);
    }
    public long incrBy(Jedis jedis,byte[] key,byte[] field,long value){
	return jedis.hincrBy(key, field, value);
    }
}
