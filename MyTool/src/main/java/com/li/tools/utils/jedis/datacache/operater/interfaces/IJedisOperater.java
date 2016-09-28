package com.li.tools.utils.jedis.datacache.operater.interfaces;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.TransactionBlock;

/**
 * @author lijuntao
 * @date 2016-9-28
 */
public interface IJedisOperater {

    /***			redis的key操作：开始				***/
    public abstract long detele(Jedis jedis, String... keys);

    public abstract long detele(Jedis jedis, byte[]... keys);

    public abstract boolean exists(Jedis jedis, String key);

    public abstract boolean exists(Jedis jedis, byte[] key);

    public abstract long expire(Jedis jedis, String key, int second);

    public abstract long expire(Jedis jedis, byte[] key, int second);

    public abstract long removeExpire(Jedis jedis, String key);

    public abstract long removeExpire(Jedis jedis, byte[] key);

    public abstract long expireAtUnixTimestamp(Jedis jedis, String key,
	    long timestamp);

    public abstract long expireAtUnixTimestamp(Jedis jedis, byte[] key,
	    long timestamp);

    /*public long expireByMillisecond(Jedis jedis,String key,long timestamp){
        return jedis.pexpireAt(key,timestamp);
    }
    public long expireAtUnixTimestamp(Jedis jedis,byte[] key,long timestamp){
        return jedis.expireAt(key,timestamp);
    }*/
    public abstract Set<String> keys(Jedis jedis, String pattern);

    public abstract Set<byte[]> keys(Jedis jedis, byte[] pattern);

    public abstract long moveToAnotherDB(Jedis jedis, String key, int dbIndex);

    public abstract long moveToAnotherDB(Jedis jedis, byte[] key, int dbIndex);

    public abstract String rename(Jedis jedis, String oldkey, String newkey);

    public abstract String rename(Jedis jedis, byte[] oldkey, byte[] newkey);

    public abstract long renameIfNewNameNotExists(Jedis jedis, String oldkey,
	    String newkey);

    public abstract long renameIfNewNameNotExists(Jedis jedis, byte[] oldkey,
	    byte[] newkey);

    /**
     * 
     * @param jedis
     * @param key
     * @return
     * none (key不存在)、string (字符串)、list (列表)
     * set (集合)、zset (有序集)、hash (哈希表)
     * 
     */
    public abstract String type(Jedis jedis, String key);

    public abstract String type(Jedis jedis, byte[] key);

    public abstract long getRemainingExpireInSecond(Jedis jedis, String key);

    public abstract long getRemainingExpireInSecond(Jedis jedis, byte[] key);

    public abstract String getRandomKey(Jedis jedis);

    public abstract byte[] getRandomBinaryKey(Jedis jedis);

    /*redis2.6+
    public String serialize(Jedis jedis,String key){
        return jedis.dump(key);
    }
    public String serialize(Jedis jedis,byte[] key){
        return jedis.dump(key);
    }
    public long getRemainingExpireInMilliSecond(Jedis jedis,String key){
        return jedis.pttl(key);
    }
    public long getRemainingExpireInMilliSecond(Jedis jedis,byte[] key){
        return jedis.pttl(key);
    }
     *
     */
    /***			redis的key操作：结束				***/
    /***			redis的事务操作：开始				***/
    public abstract Transaction beginTransaction(Jedis jedis);

    public abstract List<Object> executeTransaction(Jedis jedis,
	    TransactionBlock transactionBlock);

    public abstract List<Object> executeTransaction(Transaction transaction);

    public abstract String disTransaction(Transaction transaction);

    /*
     * 在这条连接，开启监听某些keys，为事务做准备。事务执行完，keys释放，不再监听
     */
    public abstract String watchKeys(Jedis jedis, String... keys);

    public abstract String watchKeys(Jedis jedis, byte[]... keys);

    public abstract String unwatch(Jedis jedis);

    /***			redis的事务操作：结束			***/
    /***			redis的连接操作：开始			***/
    public abstract String authPassword(Jedis jedis, String password);

    public abstract String echo(Jedis jedis, String str);

    public abstract String ping(Jedis jedis);

    //关闭连接，如果还有命令未执行完，则执行完后再关闭
    public abstract String quit(Jedis jedis);

    public abstract String selectDB(Jedis jedis, int dbIndex);

    /***			redis的连接操作：结束		***/
    /***			redis的服务器操作：开始			***/
    public abstract long keySizeAtOneDB(Jedis jedis);

}