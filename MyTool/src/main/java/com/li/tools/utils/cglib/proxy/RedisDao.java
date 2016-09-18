package com.li.tools.utils.cglib.proxy;

public class RedisDao {
	public String save(String str){
		System.out.println("save:"+str);
		return str;
	}
}
