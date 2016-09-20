package com.li.tools.utils.jedis.datacache.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.li.tools.utils.jedis.datacache.CacheSet;
import com.li.tools.utils.jedis.datacache.ChannelCacheEntity;
import com.li.tools.utils.jedis.datacache.service.RedisCacheMapService;

/**
 * @author lijuntao
 * @date 2016-9-19
 */
public class ServiceTest {
    public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("serviceTest.xml");
	RedisCacheMapService redisCacheMapService = (RedisCacheMapService) context.getBean("redisCacheMapService");
	CacheSet<ChannelCacheEntity> set = new CacheSet<ChannelCacheEntity>();
	set.setPrefix("map");
	set.setKey("test1");
	Set<ChannelCacheEntity> set2 = new HashSet<ChannelCacheEntity>();
	for(int i=0;i<10000;i++){
	    ChannelCacheEntity entity = new ChannelCacheEntity("lijuntao"+i,"lijuntao","ajsdq2d咖啡机按时打卡按时打卡了阿萨德可拉倒");
	    set2.add(entity);
	}
	set.setEntitySet(set2);
	printDate();
	String string = redisCacheMapService.save(set);
	printDate();
	System.out.println("string"+string);
	printDate();
	Set<ChannelCacheEntity> set3 = redisCacheMapService.get("maptest1", ChannelCacheEntity.class);
	printDate();
	System.out.println("set3.size="+set3.size());
	//System.out.println("set3"+set3);
    }
    public static void printDate(){
	DateFormat format = new SimpleDateFormat("yyyy-Mm-dd HH:mm:ss:SSS");
	System.out.println(format.format(new Date()));
    }
}
