package com.li.tools.utils.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JedisTransactionTest {
    	private static Logger logger = LoggerFactory.getLogger(JedisTransactionTest.class.getName());
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:cglibTest.xml");
		RedisService bean = (RedisService)applicationContext.getBean("redisService");
		RedisTransactionManager transactionManager = (RedisTransactionManager)applicationContext.getBean("redisTransactionManager");
		RedisService redisServiceProxy = new RedisServiceProxy().createProxyObject(bean, null,transactionManager );
//		bean.test("hello1");
		logger.debug("hello");
		System.out.println("---------------------------------------华丽的分隔线----------------------");
//		bean.set("hello4",1000);
//		redisServiceProxy.set("hello3",1000);
		System.out.println(bean.get("hello"));
//		System.out.println(redisServiceProxy.get("lijuntao_test_hello1"));
	}
}
