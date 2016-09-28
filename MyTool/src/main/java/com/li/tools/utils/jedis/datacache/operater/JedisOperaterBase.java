package com.li.tools.utils.jedis.datacache.operater;

import java.util.List;
import java.util.Set;

import com.li.tools.utils.jedis.datacache.operater.interfaces.IJedisOperater;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.TransactionBlock;

/**
 * @author lijuntao
 * @date 2016-9-20
 * 用于统一所有的操作
 * 这一操作层主要是实现redis读取时的序列化和反序列化
 */
public class JedisOperaterBase implements IJedisOperater{
    /***			redis的key操作：开始				***/
    public long detele(Jedis jedis,String... keys){
	return jedis.del(keys);
    }
    public long detele(Jedis jedis,byte[]... keys){
	return jedis.del(keys);
    }
    public boolean exists(Jedis jedis,String key){
	return jedis.exists(key);
    }
    public boolean exists(Jedis jedis,byte[] key){
	return jedis.exists(key);
    }
    public long expire(Jedis jedis,String key,int second){
	return jedis.expire(key,second);
    }
    public long expire(Jedis jedis,byte[] key,int second){
	return jedis.expire(key,second);
    }
    public long removeExpire(Jedis jedis,String key){
	return jedis.persist(key);
    }
    public long removeExpire(Jedis jedis,byte[] key){
	return jedis.persist(key);
    }
    public long expireAtUnixTimestamp(Jedis jedis,String key,long timestamp){
	return jedis.expireAt(key,timestamp);
    }
    public long expireAtUnixTimestamp(Jedis jedis,byte[] key,long timestamp){
	return jedis.expireAt(key,timestamp);
    }
    /*public long expireByMillisecond(Jedis jedis,String key,long timestamp){
	return jedis.pexpireAt(key,timestamp);
    }
    public long expireAtUnixTimestamp(Jedis jedis,byte[] key,long timestamp){
	return jedis.expireAt(key,timestamp);
    }*/
    public Set<String> keys(Jedis jedis,String pattern){
	return jedis.keys(pattern);
    }
    public Set<byte[]> keys(Jedis jedis,byte[] pattern){
	return jedis.keys(pattern);
    }
    public long moveToAnotherDB(Jedis jedis,String key,int dbIndex){
	return jedis.move(key, dbIndex);
    } 
    public long moveToAnotherDB(Jedis jedis,byte[] key,int dbIndex){
	return jedis.move(key, dbIndex);
    } 
    public String rename(Jedis jedis,String oldkey,String newkey){
	return jedis.rename(oldkey, newkey);
    }
    public String rename(Jedis jedis,byte[] oldkey,byte[] newkey){
	return jedis.rename(oldkey, newkey);
    }
    public long renameIfNewNameNotExists(Jedis jedis,String oldkey,String newkey){
	return jedis.renamenx(oldkey, newkey);
    }
    public long renameIfNewNameNotExists(Jedis jedis,byte[] oldkey,byte[] newkey){
	return jedis.renamenx(oldkey, newkey);
    }
    /**
     * 
     * @param jedis
     * @param key
     * @return
     * none (key不存在)、string (字符串)、list (列表)
     * set (集合)、zset (有序集)、hash (哈希表)
     * 
     */
    public String type(Jedis jedis,String key){
	return jedis.type(key);
    }
    public String type(Jedis jedis,byte[] key){
	return jedis.type(key);
    }
    public long getRemainingExpireInSecond(Jedis jedis,String key){
	return jedis.ttl(key);
    }
    public long getRemainingExpireInSecond(Jedis jedis,byte[] key){
	return jedis.ttl(key);
    }
    public String getRandomKey(Jedis jedis){
	return jedis.randomKey();
    }
    public byte[] getRandomBinaryKey(Jedis jedis){
	return jedis.randomBinaryKey();
    }
    
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
    public Transaction beginTransaction(Jedis jedis){
	return jedis.multi();
    }
    public List<Object> executeTransaction(Jedis jedis,TransactionBlock transactionBlock){
	return jedis.multi(transactionBlock);
    }
    public List<Object> executeTransaction(Transaction transaction){
	return transaction.exec();
    }
    public String disTransaction(Transaction transaction){
	return transaction.discard();
    }
    /*
     * 在这条连接，开启监听某些keys，为事务做准备。事务执行完，keys释放，不再监听
     */
    public String watchKeys(Jedis jedis,String... keys){
	return jedis.watch(keys);
    }
    public String watchKeys(Jedis jedis,byte[]... keys){
	return jedis.watch(keys);
    }
    public String unwatch(Jedis jedis){
	return jedis.unwatch();
    }
    /***			redis的事务操作：结束			***/
    /***			redis的连接操作：开始			***/
    public String authPassword(Jedis jedis,String password){
	return jedis.auth(password);
    }
    public String echo(Jedis jedis,String str){
	return jedis.echo(str);
    }
    public String ping(Jedis jedis){
	return jedis.ping();
    }
    //关闭连接，如果还有命令未执行完，则执行完后再关闭
    public String quit(Jedis jedis){
	return jedis.quit();
    }
    public String selectDB(Jedis jedis,int dbIndex){
	return jedis.select(dbIndex);
    }
    /***			redis的连接操作：结束		***/
    /***			redis的服务器操作：开始			***/
    public long keySizeAtOneDB(Jedis jedis){
	return jedis.dbSize(); 
    }
    
}
