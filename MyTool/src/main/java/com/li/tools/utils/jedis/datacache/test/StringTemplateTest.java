package com.li.tools.utils.jedis.datacache.test;

import org.junit.Test;

import com.li.tools.utils.jedis.RedisCache.SerializeUtil;
import com.li.tools.utils.jedis.datacache.template.RedisStringTemplate;
import com.li.tools.utils.jedis.datacache.template.RedisTemplateFactory;

public class StringTemplateTest{
    @Test
    public void test1(){
	RedisStringTemplate template = new RedisTemplateFactory().getTemplate();
	System.out.println(template.set("stringtest1", "sdffsdf"));
	System.out.println(new String(SerializeUtil.serialize("123123")));
	System.out.println(template.getLength("stringtest1"));
    }
}