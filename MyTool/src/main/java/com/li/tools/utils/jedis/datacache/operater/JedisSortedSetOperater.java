package com.li.tools.utils.jedis.datacache.operater;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-27
 * 正常排序从小到大
 */
public class JedisSortedSetOperater extends JedisOperaterBase{
    public long add(Jedis jedis,String key,String value,double score){
	return jedis.zadd(key,score,value);
    }
    public long add(Jedis jedis,byte[] key,byte[] value,double score){
	return jedis.zadd(key,score,value);
    }
    public long addAllWithMap(Jedis jedis,String key,Map<Double,String> scoreMembers){
	return jedis.zadd(key, scoreMembers);
    }
    public long addAllWithMap(Jedis jedis,byte[] key,Map<Double,byte[]> scoreMembers){
	return jedis.zadd(key, scoreMembers);
    }
    public long size(Jedis jedis,String key){
	return jedis.zcard(key);
    }
    public long size(Jedis jedis,byte[] key){
	return jedis.zcard(key);
    }
    public long countBetweenScore(Jedis jedis,String key,double min,double max){
	return jedis.zcount(key, min, max);
    }
    public long countBetweenScore(Jedis jedis,byte[] key,double min,double max){
	return jedis.zcount(key, min, max);
    }
    public long countBetweenDictionary(Jedis jedis,String key,String min,String max){
	return jedis.zcount(key, min, max);
    }
    public long countBetweenDictionary(Jedis jedis,byte[] key,byte[] min,byte[] max){
	return jedis.zcount(key, min, max);
    }
    public double scoreIncr(Jedis jedis,String key,double score,String value){
	return jedis.zincrby(key, score, value);
    }
    public double scoreIncr(Jedis jedis,byte[] key,double score,byte[] value){
	return jedis.zincrby(key, score, value);
    }
    //按顺序从小到大排，获取从起始到结束
    public Set<String> getRange(Jedis jedis,String key ,long start,long end){
	return jedis.zrange(key, start, end);
    }
    public Set<byte[]> getRange(Jedis jedis,byte[] key ,int start,int end){
	return jedis.zrange(key, start, end);
    }
    public Set<String> getRangeDesc(Jedis jedis,String key ,long start,long end){
	return jedis.zrevrange(key, start, end);
    }
    public Set<byte[]> getRangeDesc(Jedis jedis,byte[] key ,int start,int end){
	return jedis.zrevrange(key, start, end);
    }
    //按字典排序 ZRANGEBYLEX myzset [aaa (g

    //按score排，获取从在score之间
    public Set<String> getRangeBySocre(Jedis jedis,String key ,double min,double max){
	return jedis.zrangeByScore(key, min, max);
    }
    public Set<byte[]> getRangeBySocre(Jedis jedis,byte[] key ,double min,double max){
	return jedis.zrangeByScore(key, min, max);
    }
    public Set<String> getRangeBySocreDesc(Jedis jedis,String key ,double min,double max){
	return jedis.zrevrangeByScore(key, min, max);
    }
    public Set<byte[]> getRangeBySocreDesc(Jedis jedis,byte[] key ,double min,double max){
	return jedis.zrevrangeByScore(key, min, max);
    }
    //按分数值递增(从小到大)顺序排列，获取排名
    public long getRank(Jedis jedis,String key,String value){
	return jedis.zrank(key, value);
    }
    public long getRank(Jedis jedis,byte[] key,byte[] value){
	return jedis.zrank(key, value);
    }
    public long getRankDesc(Jedis jedis,String key,String value){
	return jedis.zrevrank(key, value);
    }
    public long getRankDesc(Jedis jedis,byte[] key,byte[] value){
	return jedis.zrevrank(key, value);
    }
    public double getScore(Jedis jedis,String key,String value){
	return jedis.zscore(key, value);
    }
    public double getScore(Jedis jedis,byte[] key,byte[] value){
	return jedis.zscore(key, value);
    }
    public long removeValues(Jedis jedis, String key,String... values ){
	return jedis.zrem(key, values);
    }
    public long removeValues(Jedis jedis, byte[] key,byte[]... values ){
	return jedis.zrem(key, values);
    }
    public long removeBySocre(Jedis jedis, String key,double start,double end){
	return jedis.zremrangeByScore(key, start, end);
    }
    public long removeBySocre(Jedis jedis, byte[] key,double start,double end){
	return jedis.zremrangeByScore(key, start, end);
    }
    public long removeByRank(Jedis jedis, String key,long start,long end){
	return jedis.zremrangeByRank(key, start, end);
    }
    public long removeByRank(Jedis jedis, byte[] key,int start,int end){
	return jedis.zremrangeByRank(key, start, end);
    }
    
    public long interStore(Jedis jedis,String targetKey,String... keys){
	return jedis.zinterstore(targetKey, keys);
    }
    public long interStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedis.zinterstore(targetKey, keys);
    }
    public long unionStore(Jedis jedis,String targetKey,String... keys){
   	return jedis.zunionstore(targetKey, keys);
    }
    public long unionStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedis.zunionstore(targetKey, keys);
    }
}
