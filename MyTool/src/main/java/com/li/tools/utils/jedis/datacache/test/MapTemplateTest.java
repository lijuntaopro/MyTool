package com.li.tools.utils.jedis.datacache.test;

import org.junit.Test;

import com.li.tools.utils.jedis.datacache.template.JedisMapTemplate;
import com.li.tools.utils.jedis.datacache.template.RedisTemplateFactory2;

public class MapTemplateTest{
    @Test
    public void test1(){
	JedisMapTemplate mapTemplate = new RedisTemplateFactory2().getTemplate(JedisMapTemplate.class);
	System.out.println(mapTemplate.set("map_test_1", "hello1", "helloworld"));
	System.out.println(mapTemplate.get("map_test_1", "hello1"));
    }
}