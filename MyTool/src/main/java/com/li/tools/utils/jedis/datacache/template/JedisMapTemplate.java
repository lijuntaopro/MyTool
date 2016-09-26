package com.li.tools.utils.jedis.datacache.template;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.li.tools.utils.jedis.datacache.operater.JedisMapOperater;


/**
 * @author lijuntao
 * @date 2016-9-23
 */
public class JedisMapTemplate{
    protected JedisMapOperater jedisMapOperater = new JedisMapOperater();
    public long set(String key,String field,String value){
	return jedisMapOperater.set(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public long set(byte[] key,byte[] field,byte[] value){
	return jedisMapOperater.set(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public long setValuesIfNotExists(Jedis jedis,String key,String field,String value){
	return jedisMapOperater.setValuesIfNotExists(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public long setValuesIfNotExists(Jedis jedis,byte[] key,byte[] field,byte[] value){
	return jedisMapOperater.setValuesIfNotExists(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public String setMap(String key,Map<String,String> map){
	return jedisMapOperater.setMap(ThreadLocalPool.thresdJedis.get(),key, map);
    }
    public long setMap(byte[] key,byte[] field,byte[] value){
	return jedisMapOperater.setMap(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public String get(String key,String field){
	return jedisMapOperater.get(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public byte[] get(byte[] key,byte[] field){
	return jedisMapOperater.get(ThreadLocalPool.thresdJedis.get(),key,field);
    }
    public List<String> getSomeByfields(String key,String field){
	return jedisMapOperater.getSomeByfields(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public List<byte[]> getSomeByfields(byte[] key,byte[] field){
	return jedisMapOperater.getSomeByfields(ThreadLocalPool.thresdJedis.get(),key,field);
    }
    public Map<String, String> getWholeMapAll(String key){
	return jedisMapOperater.getWholeMapAll(ThreadLocalPool.thresdJedis.get(),key);
    }
    public Map<byte[],byte[]> getWholeMapAll(byte[] key){
	return jedisMapOperater.getWholeMapAll(ThreadLocalPool.thresdJedis.get(),key);
    }
    public long del(String key,String... field){
	return jedisMapOperater.del(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public long del(byte[] key,byte[]... field){
	return jedisMapOperater.del(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public Set<String> getAllFieldKey(String key){
	return jedisMapOperater.getAllFieldKey(ThreadLocalPool.thresdJedis.get(),key);
    }
    public Set<byte[]> getAllFieldKey(byte[] key){
	return jedisMapOperater.getAllFieldKey(ThreadLocalPool.thresdJedis.get(),key);
    }
    public boolean existsField(String key,String field){
	return jedisMapOperater.existsField(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public boolean existsField(byte[] key,byte[] field){
	return jedisMapOperater.existsField(ThreadLocalPool.thresdJedis.get(),key, field);
    }
    public long getLength(String key){
	return jedisMapOperater.getLength(ThreadLocalPool.thresdJedis.get(),key);
    }
    public long getLength(byte[] key){
	return jedisMapOperater.getLength(ThreadLocalPool.thresdJedis.get(),key);
    }
    public List<String> getAllValues(String key){
	return jedisMapOperater.getAllValues(ThreadLocalPool.thresdJedis.get(),key);
    }
    public List<byte[]> getAllValues(byte[] key){
	return jedisMapOperater.getAllValues(ThreadLocalPool.thresdJedis.get(),key);
    }
    /****			只对map的value值为数字操作				***/
    public long incrBy(Jedis jedis,String key,String field,long value){
	return jedisMapOperater.incrBy(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
    public long incrBy(Jedis jedis,byte[] key,byte[] field,long value){
	return jedisMapOperater.incrBy(ThreadLocalPool.thresdJedis.get(),key, field, value);
    }
}
