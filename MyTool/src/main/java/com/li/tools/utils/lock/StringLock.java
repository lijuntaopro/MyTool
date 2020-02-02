package com.li.tools.utils.lock;

import java.util.Map;

import com.google.common.collect.Maps;

public class StringLock{

	private Map<String, String> map = Maps.newHashMap();
	
	public boolean tryLock(String key){
		if(key == null){
			throw new RuntimeException("锁对象不能为空");
		}
		synchronized(map){
			String oldValue = map.get(key);
			if(oldValue != null){
				return false;
			}else{
				map.put(key, key);
				return true;
			}
		}
	}
	
	public void lock(String key){
		String oldValue = null;
		while(!tryLock(key)){
			synchronized(map){
				oldValue = map.get(key);
				if(oldValue == null){
					 continue;
				}
			}
			synchronized (oldValue) {
				try {
					oldValue.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public void unlock(String key){
		if(key == null){
			throw new RuntimeException("锁对象不能为空");
		}
		String oldValue = null;
		synchronized(map){
			oldValue = map.get(key);
			if(oldValue == null){
				 throw new RuntimeException("没有找到锁对象");
			}
			map.remove(key);
		}
		synchronized (oldValue) {
			oldValue.notifyAll();
		}
	}

}
