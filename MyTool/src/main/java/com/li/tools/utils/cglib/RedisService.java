package com.li.tools.utils.cglib;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
@Service("redisService")
public class RedisService {
	@Resource
	private JedisDao jedisDao;
	
	public void set(String str,int seconds){
		for(int i=0;i<10;i++){
			String key = "lijuntao_test_"+str+i;
			jedisDao.set(key, ""+i);
			System.out.println("jedisDao.set:key="+key+";value="+i);
			try {
				Thread.currentThread().sleep(seconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String get(String key){
		return jedisDao.get(key);
	}
	
	public String test(String str){
		System.out.println("RedisService:"+str);
		return str;
	}
}
