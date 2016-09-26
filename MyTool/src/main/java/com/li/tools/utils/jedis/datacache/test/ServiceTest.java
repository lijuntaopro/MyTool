package com.li.tools.utils.jedis.datacache.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.li.tools.utils.jedis.datacache.CacheSet;
import com.li.tools.utils.jedis.datacache.ChannelCacheEntity;
import com.li.tools.utils.jedis.datacache.service.RedisCacheMapService;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * @author lijuntao
 * @date 2016-9-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:serviceTest.xml")
public class ServiceTest extends AbstractJUnit4SpringContextTests{
    @Resource
    private RedisCacheMapService redisCacheMapService;
    public static void printDate(String str){
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	System.out.println(str+format.format(new Date()));
    }
    @Test
    public void printMapInRedis(){
	String key = "maptest10";
	
	printDate("length");
	long length = redisCacheMapService.getMapLength(key);
	printDate("length");
	System.out.println("map:length="+length);
	
	printDate("some");
	Set<ChannelCacheEntity> set = redisCacheMapService.getSomeValuesBykeys(key,new Object[]{ "lijuntao0","lijuntao2","lijuntao2","lijuntao3", "lijuntao1"}, ChannelCacheEntity.class);
	printDate("some");
	System.out.println("map:length="+set.size()+";set="+set);
	
	printDate("keys");
//	Set<String> keys = redisCacheMapService.getAllKeys(key, ChannelCacheEntity.class);
	printDate("keys");
//	System.out.println("map:length="+keys.size()+";keys="+keys);
	
	printDate("values");
//	Set<ChannelCacheEntity> values = redisCacheMapService.getAllValues(key, ChannelCacheEntity.class);
	printDate("values");
//	System.out.println("map:length="+values.size()+";values="+values);
	
	printDate("map");
//	Map<String, ChannelCacheEntity> map = redisCacheMapService.getAllKeyAndValuesCacheSetInHashMap(key, ChannelCacheEntity.class);
	printDate("map");
//	System.out.println("map:size="+map.size());
    }
    /**
     * map最多991003个
     */
    public void mapWriteToRedis(){
	CacheSet<ChannelCacheEntity> set = new CacheSet<ChannelCacheEntity>();
	set.setPrefix("map");
	set.setKey("test10");
	Set<ChannelCacheEntity> set2 = new HashSet<ChannelCacheEntity>();
	printDate("create");
	for(int i=1000000;i<2000000;i++){
	    ChannelCacheEntity entity = new ChannelCacheEntity("lijuntao"+i,"lijuntao","ajsdq2d咖啡机按时打卡按时打卡了阿萨德可拉倒");
	    set2.add(entity);
	}
	printDate("create");
	set.setEntitySet(set2);
	printDate("save");
	String string = redisCacheMapService.save(set);
	printDate("save");
	System.out.println("string"+string);
//	printDate("get");
//	Set<ChannelCacheEntity> set3 = redisCacheMapService.get("maptest10", ChannelCacheEntity.class);
//	printDate("get");
//	System.out.println("set3.size="+set3.size());
//	System.out.println("set3"+set3);
    }
    
    @Test
    public void printListInRedis(){
	
    }
    public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("serviceTest.xml");
	RedisCacheMapService redisCacheMapService = (RedisCacheMapService) context.getBean("redisCacheMapService");
	ServiceTest test = new ServiceTest();
	test.redisCacheMapService = redisCacheMapService;
	test.mapWriteToRedis();
	test.printMapInRedis();
    }
}
