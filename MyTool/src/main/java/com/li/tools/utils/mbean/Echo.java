package com.li.tools.utils.mbean;

/**
 * 
 * @author lijuntao
 * @date 2016-9-18
 * jxm使用，接口名必须是以MBean结尾，实现类必须是接口去除MBean，且两个类必须在同一包下
 */
public class Echo implements EchoMBean{
	
	public void print(String name) {
		System.out.println("echo:"+name+";end.");
	}
	
}
