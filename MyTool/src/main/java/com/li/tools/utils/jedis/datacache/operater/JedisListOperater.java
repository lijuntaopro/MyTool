package com.li.tools.utils.jedis.datacache.operater;

import java.util.List;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;

/**
 * @author lijuntao
 * @date 2016-9-19
 * l-->代表left，也是List头部;r-->代表right，也是List的尾部。
 * JedisListOperater
 * 
 * 
 */
public class JedisListOperater extends JedisOperaterBase{
    public long addElementToFirst(Jedis jedis,String key,String value){
	return jedis.lpush(key, value);
    }
    public long addElementToFirst(Jedis jedis,byte[] key,byte[] value){
	return jedis.lpush(key, value);
    }
    public long addElementsToFirstExistsList(Jedis jedis,String key,String value){
	return jedis.lpushx(key, value);
    }
    public long addElementsToFirstExistsList(Jedis jedis,byte[] key,byte[] value){
	return jedis.lpushx(key, value);
    }
    public long addElementToLast(Jedis jedis,String key,String value){
	return jedis.rpush(key, value);
    }
    public long addElementToLast(Jedis jedis,byte[] key,byte[] value){
	return jedis.rpush(key, value);
    }
    public long addElementToLastExistsList(Jedis jedis,String key,String value){
	return jedis.rpushx(key, value);
    }
    public long addElementToLastExistsList(Jedis jedis,byte[] key,byte[] value){
	return jedis.rpushx(key, value);
    }
    public String popElementAtFirst(Jedis jedis,String key){
	return jedis.lpop(key);
    }
    public byte[] popElementAtFirst(Jedis jedis,byte[] key){
	return jedis.lpop(key);
    }
    public String popElementAtLast(Jedis jedis,String key){
	return jedis.rpop(key);
    }
    public byte[] popElementAtLast(Jedis jedis,byte[] key){
	return jedis.rpop(key);
    }
    //阻塞版本，获取多个List的值
    public List<String> popElementsAtFirstAtLists(Jedis jedis,String[] key,int timeout){
	return jedis.blpop(timeout,key);
    }
    public List<byte[]> popElementsAtFirstAtLists(Jedis jedis,byte[][] key,int timeout){
	return jedis.blpop(timeout, key);
    }
    public List<String> popElementsAtLastAtLists(Jedis jedis,String[] key,int timeout){
	return jedis.brpop(timeout,key);
    }
    public List<byte[]> popElementsAtLastAtLists(Jedis jedis,byte[][] key,int timeout){
	return jedis.brpop(timeout, key);
    }
    public long insertBeforeOrAfter(Jedis jedis,String key,LIST_POSITION where,String preValue,String newValue){
	return jedis.linsert(key, where, preValue, newValue);
    }
    public long insertBeforeOrAfter(Jedis jedis,byte[] key,LIST_POSITION where,byte[] preValue,byte[] newValue){
	return jedis.linsert(key, where, preValue, newValue);
    }
    public String updateByIndex(Jedis jedis,String key,int index,String value){
	return jedis.lset(key, index, value);
    }
    public String updateByIndex(Jedis jedis,byte[] key,int index,byte[] value){
	return jedis.lset(key, index, value);
    }
    public String getByIndex(Jedis jedis,String key,int index){
	return jedis.lindex(key, index);
    }
    public byte[] getByIndex(Jedis jedis,byte[] key,int index){
	return jedis.lindex(key, index);
    }
    //其中 0 表示列表的第一个元素，以 -1 表示列表的最后一个元素
    public List<String> getRangeElement(Jedis jedis,String key,long start,long end){
	return jedis.lrange(key, start, end);
    }
    //其中 0 表示列表的第一个元素，以 -1 表示列表的最后一个元素
    public List<byte[]> getRangeElement(Jedis jedis,byte[] key,int start,int end){
	return jedis.lrange(key, start, end);
    }
    public String removeWithoutRangeElement(Jedis jedis,String key,int start,int end){
	return jedis.ltrim(key, start, end);
    }
    public String removeWithoutRangeElement(Jedis jedis,byte[] key,int start,int end){
	return jedis.ltrim(key, start, end);
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param start 负代表从尾开始移除，正从头，绝对值代表移除个数，其他0代表移除全部。
     * @param value
     * @return
     */
    public long removeElementsByValue(Jedis jedis,String key,int start,String value){
	return jedis.lrem(key, start, value);
    }
    public long removeElementsByValue(Jedis jedis,byte[] key,int start,byte[] value){
	return jedis.lrem(key, start, value);
    }
    public long size(Jedis jedis,String key){
	return jedis.llen(key);
    }
    public long size(Jedis jedis,byte[] key){
	return jedis.llen(key);
    }
    public String elementToOtherList(Jedis jedis,String key,String otherListKey){
	return jedis.rpoplpush(key, otherListKey);
    }
    public byte[] elementToOtherList(Jedis jedis,byte[] key,byte[] otherListKey){
	return jedis.rpoplpush(key, otherListKey);
    }
    //阻塞版本
    public String elementToOtherListwith(Jedis jedis,String key,String otherListKey,int timeout){
	return jedis.brpoplpush(key, otherListKey,timeout);
    }
    public byte[] elementToOtherListwith(Jedis jedis,byte[] key,byte[] otherListKey,int timeout){
	return jedis.brpoplpush(key, otherListKey,timeout);
    }
    
}