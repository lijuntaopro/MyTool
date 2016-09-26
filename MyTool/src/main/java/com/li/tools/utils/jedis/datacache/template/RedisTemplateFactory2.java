package com.li.tools.utils.jedis.datacache.template;

import java.lang.reflect.Method;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.li.tools.utils.cglib.proxy.Pointcut;
import com.li.tools.utils.cglib.proxy.TransactionManager;
import com.li.tools.utils.jedis.RedisPoolUtil;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RedisTemplateFactory2 implements MethodInterceptor{
    	private RedisStringTemplate redisStringTemplate;
//    	private static MethodInterceptor methodInterceptor;
        private JedisPool pool = RedisPoolUtil.getInstance();
	private Pointcut pointcut;
	private TransactionManager transactionManager;
	private boolean pointcutNotNullFlag = false;
	private boolean transactionManagerNotNullFlag = false;
	public <T> T getTemplate(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(RedisStringTemplate.class);
		if(redisStringTemplate==null)
		    redisStringTemplate = new RedisStringTemplate();
		enhancer.setCallback(this);
		T object = (T) enhancer.create();
		return object;
	}
	public <T> T getTemplate(T obj,Pointcut p){
		if(p!=null)
			pointcutNotNullFlag = true;
		pointcut = p;
		return getTemplate();
	}
	public <T> T getTemplate(T obj,Pointcut p,TransactionManager t){
		pointcut = p;
		transactionManager = t;
		if(p!=null)
			pointcutNotNullFlag = true;
		if(t!=null)
			transactionManagerNotNullFlag = true;
		return getTemplate();
	}
	/**
	 * param : obj是this对象
	 */
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		if(pointcutNotNullFlag && !pointcut.matchMethod(method.getName()))
			return null;
		if(transactionManagerNotNullFlag)
			transactionManager.before();
		Jedis jedis2 = pool.getResource();
		ThreadLocalPool.thresdJedis.set(jedis2);
		Object invoke = null;
		try{
		    invoke = method.invoke(redisStringTemplate, args);
		}catch(Exception e){
		    System.out.println("RedisTemplateProxy执行出错.");
		    e.printStackTrace();
		}finally{
		    if(jedis2!=null)
			pool.returnResource(jedis2);
		}
		if(transactionManagerNotNullFlag)
			transactionManager.after();
		return invoke;
	}
	public JedisPool getPool() {
	    return pool;
	}
	public void setPool(JedisPool pool) {
	    this.pool = pool;
	}
}
