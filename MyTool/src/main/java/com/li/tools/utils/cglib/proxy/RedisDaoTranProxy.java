package com.li.tools.utils.cglib.proxy;

import java.lang.reflect.Method;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RedisDaoTranProxy implements MethodInterceptor{
	private Object targetObj ;
	private JedisPool pool ;
	public <T> T create(T obj,JedisPool pool){
		this.targetObj = obj;
		this.pool = pool;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Jedis jedis = pool.getResource();
		Transaction multi = jedis.multi();
		Object invoke = method.invoke(targetObj, args);
		multi.exec();
		return invoke;
	}
	
}
