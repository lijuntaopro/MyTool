package com.li.tools.utils.cglib;

import java.lang.reflect.Method;

import com.li.tools.utils.cglib.proxy.Pointcut;
import com.li.tools.utils.cglib.proxy.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RedisServiceProxy implements MethodInterceptor{
	private Object targetObj;
	private Pointcut pointcut;
	private TransactionManager transactionManager;
	private boolean pointcutNotNullFlag = false;
	private boolean transactionManagerNotNullFlag = false;
	public <T> T createProxyObject(T obj){
		targetObj = obj;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		T object = (T) enhancer.create();
		return object;
	}
	public <T> T createProxyObject(T obj,Pointcut p){
		if(p!=null)
			pointcutNotNullFlag = true;
		pointcut = p;
		return createProxyObject(obj);
	}
	public <T> T createProxyObject(T obj,Pointcut p,TransactionManager t){
		pointcut = p;
		transactionManager = t;
		if(p!=null)
			pointcutNotNullFlag = true;
		if(t!=null)
			transactionManagerNotNullFlag = true;
		return createProxyObject(obj);
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
		Object invoke = method.invoke(targetObj, args);
		if(transactionManagerNotNullFlag)
			transactionManager.after();
		return invoke;
	}
	
}
