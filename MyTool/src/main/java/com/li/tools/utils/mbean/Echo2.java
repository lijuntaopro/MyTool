package com.li.tools.utils.mbean;

public class Echo2 implements EchoMBean{

	public void print(String name) {
		System.out.println("echo:"+name+";end.");
	}
	
}
