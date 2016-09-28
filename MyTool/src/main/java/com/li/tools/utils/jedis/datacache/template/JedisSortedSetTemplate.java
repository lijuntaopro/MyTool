package com.li.tools.utils.jedis.datacache.template;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.li.tools.utils.jedis.datacache.operater.JedisSortedSetOperater;


/**
 * @author lijuntao
 * @date 2016-9-27
 * 正常排序从小到大
 */
public class JedisSortedSetTemplate {
    private JedisSortedSetOperater jedisSortedSetOperater = new JedisSortedSetOperater();
    public long add(Jedis jedis,String key,String value,double score){
	return jedisSortedSetOperater.add(ThreadLocalPool.thresdJedis.get(),key,value,score);
    }
    public long add(Jedis jedis,byte[] key,byte[] value,double score){
	return jedisSortedSetOperater.add(ThreadLocalPool.thresdJedis.get(),key,value,score);
    }
    public long addAllWithMap(Jedis jedis,String key,Map<Double,String> scoreMembers){
	return jedisSortedSetOperater.addAllWithMap(ThreadLocalPool.thresdJedis.get(),key, scoreMembers);
    }
    public long addAllWithMap(Jedis jedis,byte[] key,Map<Double,byte[]> scoreMembers){
	return jedisSortedSetOperater.addAllWithMap(ThreadLocalPool.thresdJedis.get(),key, scoreMembers);
    }
    public long size(Jedis jedis,String key){
	return jedisSortedSetOperater.size(ThreadLocalPool.thresdJedis.get(),key);
    }
    public long size(Jedis jedis,byte[] key){
	return jedisSortedSetOperater.size(ThreadLocalPool.thresdJedis.get(),key);
    }
    public long countBetweenScore(Jedis jedis,String key,double min,double max){
	return jedisSortedSetOperater.countBetweenScore(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public long countBetweenScore(Jedis jedis,byte[] key,double min,double max){
	return jedisSortedSetOperater.countBetweenScore(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public long countBetweenDictionary(Jedis jedis,String key,String min,String max){
	return jedisSortedSetOperater.countBetweenDictionary(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public long countBetweenDictionary(Jedis jedis,byte[] key,byte[] min,byte[] max){
	return jedisSortedSetOperater.countBetweenDictionary(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public double scoreIncr(Jedis jedis,String key,double score,String value){
	return jedisSortedSetOperater.scoreIncr(ThreadLocalPool.thresdJedis.get(),key, score,value);
    }
    public double scoreIncr(Jedis jedis,byte[] key,double score,byte[] value){
	return jedisSortedSetOperater.scoreIncr(ThreadLocalPool.thresdJedis.get(),key, score,value);
    }
    //按顺序从小到大排，获取从起始到结束
    public Set<String> getRange(Jedis jedis,String key ,long start,long end){
	return jedisSortedSetOperater.getRange(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public Set<byte[]> getRange(Jedis jedis,byte[] key ,int start,int end){
	return jedisSortedSetOperater.getRange(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public Set<String> getRangeDesc(Jedis jedis,String key ,long start,long end){
	return jedisSortedSetOperater.getRangeDesc(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public Set<byte[]> getRangeDesc(Jedis jedis,byte[] key ,int start,int end){
	return jedisSortedSetOperater.getRangeDesc(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    //按字典排序 ZRANGEBYLEX myzset [aaa (g

    //按score排，获取从在score之间
    public Set<String> getRangeBySocre(Jedis jedis,String key ,double min,double max){
	return jedisSortedSetOperater.getRangeBySocre(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public Set<byte[]> getRangeBySocre(Jedis jedis,byte[] key ,double min,double max){
	return jedisSortedSetOperater.getRangeBySocre(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public Set<String> getRangeBySocreDesc(Jedis jedis,String key ,double min,double max){
	return jedisSortedSetOperater.getRangeBySocreDesc(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    public Set<byte[]> getRangeBySocreDesc(Jedis jedis,byte[] key ,double min,double max){
	return jedisSortedSetOperater.getRangeBySocreDesc(ThreadLocalPool.thresdJedis.get(),key, min, max);
    }
    //按分数值递增(从小到大)顺序排列，获取排名
    public long getRank(Jedis jedis,String key,String value){
	return jedisSortedSetOperater.getRank(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long getRank(Jedis jedis,byte[] key,byte[] value){
	return jedisSortedSetOperater.getRank(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long getRankDesc(Jedis jedis,String key,String value){
	return jedisSortedSetOperater.getRankDesc(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long getRankDesc(Jedis jedis,byte[] key,byte[] value){
	return jedisSortedSetOperater.getRankDesc(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public double getScore(Jedis jedis,String key,String value){
	return jedisSortedSetOperater.getScore(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public double getScore(Jedis jedis,byte[] key,byte[] value){
	return jedisSortedSetOperater.getScore(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long removeValues(Jedis jedis, String key,String... values ){
	return jedisSortedSetOperater.removeValues(ThreadLocalPool.thresdJedis.get(),key, values);
    }
    public long removeValues(Jedis jedis, byte[] key,byte[]... values ){
	return jedisSortedSetOperater.removeValues(ThreadLocalPool.thresdJedis.get(),key, values);
    }
    public long removeBySocre(Jedis jedis, String key,double start,double end){
	return jedisSortedSetOperater.removeBySocre(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public long removeBySocre(Jedis jedis, byte[] key,double start,double end){
	return jedisSortedSetOperater.removeBySocre(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public long removeByRank(Jedis jedis, String key,long start,long end){
	return jedisSortedSetOperater.removeByRank(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public long removeByRank(Jedis jedis, byte[] key,int start,int end){
	return jedisSortedSetOperater.removeByRank(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    
    public long interStore(Jedis jedis,String targetKey,String... keys){
	return jedisSortedSetOperater.interStore(ThreadLocalPool.thresdJedis.get(),targetKey, keys);
    }
    public long interStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedisSortedSetOperater.interStore(ThreadLocalPool.thresdJedis.get(),targetKey, keys);
    }
    public long unionStore(Jedis jedis,String targetKey,String... keys){
   	return jedisSortedSetOperater.interStore(ThreadLocalPool.thresdJedis.get(),targetKey, keys);
    }
    public long unionStore(Jedis jedis,byte[] targetKey,byte[]... keys){
	return jedisSortedSetOperater.interStore(ThreadLocalPool.thresdJedis.get(),targetKey, keys);
    }
}
