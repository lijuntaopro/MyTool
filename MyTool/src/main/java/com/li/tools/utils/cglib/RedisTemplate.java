package com.li.tools.utils.cglib;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.li.tools.utils.jedis.RedisPoolUtil;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

@Component("redisTemplate")
public class RedisTemplate implements JedisCommands{
	private JedisPool pool = RedisPoolUtil.getInstance();
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
	public JedisPool getPool() {
		return pool;
	}
	public String set(String key, String value) {
	    String string = null;
		Transaction transaction = LocalJedisTx.jedisMap.get();
		if(transaction!=null)
			transaction.set(key, value);
		else{
			Jedis jedis = pool.getResource();
			try{
				string = jedis.set(key, value);
			}catch(Exception e){}
			finally{
				if(jedis!=null)
					pool.returnResource(jedis);
			}
		}
		return string;
	}
	public String get(String key) {
	    String string = null;
		Transaction transaction = LocalJedisTx.jedisMap.get();
		if(transaction!=null)
			transaction.get(key);
		else{
			Jedis jedis = pool.getResource();
			try{
				string = jedis.get(key);
			}catch(Exception e){}
			finally{
				if(jedis!=null)
					pool.returnResource(jedis);
			}
		}
		return string;
	}
	public Boolean exists(String key) {
	    
	    return null;
	}
	public String type(String key) {
	    
	    return null;
	}
	public Long expire(String key, int seconds) {
	    
	    return null;
	}
	public Long expireAt(String key, long unixTime) {
	    
	    return null;
	}
	public Long ttl(String key) {
	    
	    return null;
	}
	public Boolean setbit(String key, long offset, boolean value) {
	    
	    return null;
	}
	public Boolean getbit(String key, long offset) {
	    
	    return null;
	}
	public Long setrange(String key, long offset, String value) {
	    
	    return null;
	}
	public String getrange(String key, long startOffset, long endOffset) {
	    
	    return null;
	}
	public String getSet(String key, String value) {
	    
	    return null;
	}
	public Long setnx(String key, String value) {
	    
	    return null;
	}
	public String setex(String key, int seconds, String value) {
	    
	    return null;
	}
	public Long decrBy(String key, long integer) {
	    
	    return null;
	}
	public Long decr(String key) {
	    
	    return null;
	}
	public Long incrBy(String key, long integer) {
	    
	    return null;
	}
	public Long incr(String key) {
	    
	    return null;
	}
	public Long append(String key, String value) {
	    
	    return null;
	}
	public String substr(String key, int start, int end) {
	    
	    return null;
	}
	public Long hset(String key, String field, String value) {
	    
	    return null;
	}
	public String hget(String key, String field) {
	    
	    return null;
	}
	public Long hsetnx(String key, String field, String value) {
	    
	    return null;
	}
	public String hmset(String key, Map<String, String> hash) {
	    
	    return null;
	}
	public List<String> hmget(String key, String... fields) {
	    
	    return null;
	}
	public Long hincrBy(String key, String field, long value) {
	    
	    return null;
	}
	public Boolean hexists(String key, String field) {
	    
	    return null;
	}
	public Long hdel(String key, String... field) {
	    
	    return null;
	}
	public Long hlen(String key) {
	    
	    return null;
	}
	public Set<String> hkeys(String key) {
	    
	    return null;
	}
	public List<String> hvals(String key) {
	    
	    return null;
	}
	public Map<String, String> hgetAll(String key) {
	    
	    return null;
	}
	public Long rpush(String key, String... string) {
	    
	    return null;
	}
	public Long lpush(String key, String... string) {
	    
	    return null;
	}
	public Long llen(String key) {
	    
	    return null;
	}
	public List<String> lrange(String key, long start, long end) {
	    
	    return null;
	}
	public String ltrim(String key, long start, long end) {
	    
	    return null;
	}
	public String lindex(String key, long index) {
	    
	    return null;
	}
	public String lset(String key, long index, String value) {
	    
	    return null;
	}
	public Long lrem(String key, long count, String value) {
	    
	    return null;
	}
	public String lpop(String key) {
	    
	    return null;
	}
	public String rpop(String key) {
	    
	    return null;
	}
	public Long sadd(String key, String... member) {
	    
	    return null;
	}
	public Set<String> smembers(String key) {
	    
	    return null;
	}
	public Long srem(String key, String... member) {
	    
	    return null;
	}
	public String spop(String key) {
	    
	    return null;
	}
	public Long scard(String key) {
	    
	    return null;
	}
	public Boolean sismember(String key, String member) {
	    
	    return null;
	}
	public String srandmember(String key) {
	    
	    return null;
	}
	public Long zadd(String key, double score, String member) {
	    
	    return null;
	}
	public Long zadd(String key, Map<Double, String> scoreMembers) {
	    
	    return null;
	}
	public Set<String> zrange(String key, long start, long end) {
	    
	    return null;
	}
	public Long zrem(String key, String... member) {
	    
	    return null;
	}
	public Double zincrby(String key, double score, String member) {
	    
	    return null;
	}
	public Long zrank(String key, String member) {
	    
	    return null;
	}
	public Long zrevrank(String key, String member) {
	    
	    return null;
	}
	public Set<String> zrevrange(String key, long start, long end) {
	    
	    return null;
	}
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
	    
	    return null;
	}
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
	    
	    return null;
	}
	public Long zcard(String key) {
	    
	    return null;
	}
	public Double zscore(String key, String member) {
	    
	    return null;
	}
	public List<String> sort(String key) {
	    
	    return null;
	}
	public List<String> sort(String key, SortingParams sortingParameters) {
	    
	    return null;
	}
	public Long zcount(String key, double min, double max) {
	    
	    return null;
	}
	public Long zcount(String key, String min, String max) {
	    
	    return null;
	}
	public Set<String> zrangeByScore(String key, double min, double max) {
	    
	    return null;
	}
	public Set<String> zrangeByScore(String key, String min, String max) {
	    
	    return null;
	}
	public Set<String> zrevrangeByScore(String key, double max, double min) {
	    
	    return null;
	}
	public Set<String> zrangeByScore(String key, double min, double max,
		int offset, int count) {
	    
	    return null;
	}
	public Set<String> zrevrangeByScore(String key, String max, String min) {
	    
	    return null;
	}
	public Set<String> zrangeByScore(String key, String min, String max,
		int offset, int count) {
	    
	    return null;
	}
	public Set<String> zrevrangeByScore(String key, double max, double min,
		int offset, int count) {
	    
	    return null;
	}
	public Set<Tuple> zrangeByScoreWithScores(String key, double min,
		double max) {
	    
	    return null;
	}
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
		double min) {
	    
	    return null;
	}
	public Set<Tuple> zrangeByScoreWithScores(String key, double min,
		double max, int offset, int count) {
	    
	    return null;
	}
	public Set<String> zrevrangeByScore(String key, String max, String min,
		int offset, int count) {
	    
	    return null;
	}
	public Set<Tuple> zrangeByScoreWithScores(String key, String min,
		String max) {
	    
	    return null;
	}
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
		String min) {
	    
	    return null;
	}
	public Set<Tuple> zrangeByScoreWithScores(String key, String min,
		String max, int offset, int count) {
	    
	    return null;
	}
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
		double min, int offset, int count) {
	    
	    return null;
	}
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max,
		String min, int offset, int count) {
	    
	    return null;
	}
	public Long zremrangeByRank(String key, long start, long end) {
	    
	    return null;
	}
	public Long zremrangeByScore(String key, double start, double end) {
	    
	    return null;
	}
	public Long zremrangeByScore(String key, String start, String end) {
	    
	    return null;
	}
	public Long linsert(String key, LIST_POSITION where, String pivot,
		String value) {
	    
	    return null;
	}
	public Long lpushx(String key, String string) {
	    
	    return null;
	}
	public Long rpushx(String key, String string) {
	    
	    return null;
	}
	
} 
