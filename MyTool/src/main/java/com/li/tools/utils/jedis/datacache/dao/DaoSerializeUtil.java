package com.li.tools.utils.jedis.datacache.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.CacheEntity;
import com.li.tools.utils.jedis.datacache.CacheSet;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public class DaoSerializeUtil {
    /***			用于redis中Map的操作：开始行				***/
    //用于存放map到redis时，把键值序列化
    public static <T extends CacheEntity<?>> Map<byte[],byte[]> cacheSetToMap(CacheSet<T> cacheSet){
	Map<byte[],byte[]> map = new HashMap<byte[],byte[]>();
	for(CacheEntity<?> entity:cacheSet.getEntitySet()){
	    Object id = entity.getId();
	    map.put(SerializeUtil.serialize(id), SerializeUtil.serialize(entity));
	}
	return map;
    }
    //用于从redis获取map的多个value值时，把key键序列化
    public static byte[][] objectsToBytess(Object[] ss){
	if(ss==null||ss.length==0)
	    return null;
	byte[][] bs = new byte[ss.length][];
	for(int i=0;i<ss.length;i++){
	    bs[i] = SerializeUtil.serialize(ss[i]);
	}
	return bs;
    }
    //用于从redis获取map所有的键值对时，把获取的数据的键和值反序列化成对象，存放在新的map里
    public static <T extends CacheEntity<K>,K> Map<K,T> bytesMapToObjMap(Map<byte[], byte[]> map,Class<T> class1){
	HashMap<K,T> newMap = new HashMap<K,T>();
	for(Entry<byte[], byte[]> entry:map.entrySet()){
	    K object = (K) SerializeUtil.unserialize(entry.getKey());
	    T value = (T) SerializeUtil.unserialize(entry.getValue());
	    newMap.put(object, value);
	}
	return newMap;
    }
    //用于从redis的map集合中获取所有的key值时，反序列化成对象，存放在新的set里
    public static <T extends CacheEntity<K>,K> Set<K> keysBytesToObjects(Collection<byte[]> set,Class<T> class1){
	if(set==null||set.size()==0)
	    return null;
	Set<K> set2 = new HashSet<K>();
	for(byte[] bs:set){
	    set2.add((K)SerializeUtil.unserialize(bs));
	}
	return set2;
    }
    //用于从redis的map集合中获取所有的value值时，反序列化成对象，存放在新的list里
    public static <T extends CacheEntity<?>> Set<T> valuesBytesToObjects(Collection<byte[]> list,Class<T> class1){
	if(list==null||list.size()==0)
	    return null;
	Set<T> set = new HashSet<T>();
	for(byte[] bs:list){
	    set.add((T)SerializeUtil.unserialize(bs));
	}
	return set;
    }
    /***			用于redis中Map的操作：结束行				***/
    /***			用于redis中List的操作：开始行				***/
    public static byte[][] serializeCollection(Collection co){
	if(co==null||co.size()==0)
	    return null;
	byte[][] bss = new byte[co.size()][];
	int i=0;
	for(Object obj:co){
	    bss[i++] =  SerializeUtil.serialize(obj);
	}
	return bss;
    }
    public static List<byte[][]> serializeCollection(Collection<?> co,int k){
	if(co==null||co.size()==0)
	    return null;
	List<byte[][]> list = new ArrayList<byte[][]>();
	int m = (co.size()-1)/k;
	int n = co.size()%k;
	Iterator<?> iterator = co.iterator();
	for(int i=0;i<=m;i++){
	    int length = 0;
	    if(i==m)
		length = n;
	    else
		length = k;
	    byte[][] bss = new byte[length][];
	    for(int j=0;j<length;j++)
		if(iterator.hasNext())
		    bss[j] = SerializeUtil.serialize(iterator.next());
	    list.add(bss);
	}
	return list;
    }
}
