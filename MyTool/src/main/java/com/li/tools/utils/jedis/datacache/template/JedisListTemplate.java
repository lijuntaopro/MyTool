package com.li.tools.utils.jedis.datacache.template;

import java.util.List;
import com.li.tools.utils.jedis.datacache.operater.JedisListOperater;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

/**
 * @author lijuntao
 * @date 2016-9-19
 * l-->代表left，也是List头部;r-->代表right，也是List的尾部。
 * JedisListOperater
 * 
 * 
 */
public class JedisListTemplate{
    private JedisListOperater jedisListOperater = new JedisListOperater();
    public long addElementToFirst(String key,String value){
	return jedisListOperater.addElementToFirst(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementToFirst(byte[] key,byte[] value){
	return jedisListOperater.addElementToFirst(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementsToFirstExistsList(String key,String value){
	return jedisListOperater.addElementsToFirstExistsList(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementsToFirstExistsList(byte[] key,byte[] value){
	return jedisListOperater.addElementsToFirstExistsList(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementToLast(String key,String value){
	return jedisListOperater.addElementToLast(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementToLast(byte[] key,byte[] value){
	return jedisListOperater.addElementToLast(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementToLastExistsList(String key,String value){
	return jedisListOperater.addElementToLastExistsList(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public long addElementToLastExistsList(byte[] key,byte[] value){
	return jedisListOperater.addElementToLastExistsList(ThreadLocalPool.thresdJedis.get(),key, value);
    }
    public String popElementAtFirst(String key){
	return jedisListOperater.popElementAtFirst(ThreadLocalPool.thresdJedis.get(),key);
    }
    public byte[] popElementAtFirst(byte[] key){
	return jedisListOperater.popElementAtFirst(ThreadLocalPool.thresdJedis.get(),key);
    }
    public String popElementAtLast(String key){
	return jedisListOperater.popElementAtLast(ThreadLocalPool.thresdJedis.get(),key);
    }
    public byte[] popElementAtLast(byte[] key){
	return jedisListOperater.popElementAtLast(ThreadLocalPool.thresdJedis.get(),key);
    }
    //阻塞版本，获取多个List的值
    public List<String> popElementsAtFirstAtLists(String[] key,int timeout){
	return jedisListOperater.popElementsAtFirstAtLists(ThreadLocalPool.thresdJedis.get(),key,timeout);
    }
    public List<byte[]> popElementsAtFirstAtLists(byte[][] key,int timeout){
	return jedisListOperater.popElementsAtFirstAtLists(ThreadLocalPool.thresdJedis.get(),key,timeout);
    }
    public List<String> popElementsAtLastAtLists(String[] key,int timeout){
	return jedisListOperater.popElementsAtLastAtLists(ThreadLocalPool.thresdJedis.get(),key,timeout);
    }
    public List<byte[]> popElementsAtLastAtLists(byte[][] key,int timeout){
	return jedisListOperater.popElementsAtLastAtLists(ThreadLocalPool.thresdJedis.get(),key,timeout);
    }
    public long insertBeforeOrAfter(String key,LIST_POSITION where,String preValue,String newValue){
	return jedisListOperater.insertBeforeOrAfter(ThreadLocalPool.thresdJedis.get(),key, where, preValue, newValue);
    }
    public long insertBeforeOrAfter(byte[] key,LIST_POSITION where,byte[] preValue,byte[] newValue){
	return jedisListOperater.insertBeforeOrAfter(ThreadLocalPool.thresdJedis.get(),key, where, preValue, newValue);
    }
    public String updateByIndex(String key,int index,String value){
	return jedisListOperater.updateByIndex(ThreadLocalPool.thresdJedis.get(),key, index, value);
    }
    public String updateByIndex(byte[] key,int index,byte[] value){
	return jedisListOperater.updateByIndex(ThreadLocalPool.thresdJedis.get(),key, index, value);
    }
    public String getByIndex(String key,int index){
	return jedisListOperater.getByIndex(ThreadLocalPool.thresdJedis.get(),key, index);
    }
    public byte[] getByIndex(byte[] key,int index){
	return jedisListOperater.getByIndex(ThreadLocalPool.thresdJedis.get(),key, index);
    }
    //其中 0 表示列表的第一个元素，以 -1 表示列表的最后一个元素
    public List<String> getRangeElement(String key,long start,long end){
	return jedisListOperater.getRangeElement(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    //其中 0 表示列表的第一个元素，以 -1 表示列表的最后一个元素
    public List<byte[]> getRangeElement(byte[] key,int start,int end){
	return jedisListOperater.getRangeElement(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public String removeWithoutRangeElement(String key,int start,int end){
	return jedisListOperater.removeWithoutRangeElement(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    public String removeWithoutRangeElement(byte[] key,int start,int end){
	return jedisListOperater.removeWithoutRangeElement(ThreadLocalPool.thresdJedis.get(),key, start, end);
    }
    /**
     * 
     * @param jedis
     * @param key
     * @param start 负代表从尾开始移除，正从头，绝对值代表移除个数，其他0代表移除全部。
     * @param value
     * @return
     */
    public long removeElementsByValue(String key,int start,String value){
	return jedisListOperater.removeElementsByValue(ThreadLocalPool.thresdJedis.get(),key, start, value);
    }
    public long removeElementsByValue(byte[] key,int start,byte[] value){
	return jedisListOperater.removeElementsByValue(ThreadLocalPool.thresdJedis.get(),key, start, value);
    }
    public long size(String key){
	return jedisListOperater.size(ThreadLocalPool.thresdJedis.get(),key);
    }
    public long size(byte[] key){
	return jedisListOperater.size(ThreadLocalPool.thresdJedis.get(),key);
    }
    public String elementToOtherList(String key,String otherListKey){
	return jedisListOperater.elementToOtherList(ThreadLocalPool.thresdJedis.get(),key, otherListKey);
    }
    public byte[] elementToOtherList(byte[] key,byte[] otherListKey){
	return jedisListOperater.elementToOtherList(ThreadLocalPool.thresdJedis.get(),key, otherListKey);
    }
    //阻塞版本
    public String elementToOtherListwith(String key,String otherListKey,int timeout){
	return jedisListOperater.elementToOtherListwith(ThreadLocalPool.thresdJedis.get(),key, otherListKey,timeout);
    }
    public byte[] elementToOtherListwith(byte[] key,byte[] otherListKey,int timeout){
	return jedisListOperater.elementToOtherListwith(ThreadLocalPool.thresdJedis.get(),key, otherListKey,timeout);
    }
    
}