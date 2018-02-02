package com.li.tools.designpattern;

public class Singleton {
	private static volatile Singleton singleton;
	
	private Singleton(){}
	
	/**
	 * singleton 必须是用volatile修饰，在1.5后才起作用
	 * 双重检查
	 * author lijuntao
	 * date 2017年10月23日
	 */
	public static Singleton getInstance(){
		if(singleton == null){
			synchronized (Singleton.class) {
				if(singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
