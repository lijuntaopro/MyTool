package com.li.tools.utils.test;

import org.junit.Test;

public class CircleTest {
	
	public void circle1(int i){
		int[] arr = new int[i];
		for(int j=0; j<i; j++){
			arr[j] = j;
		}
	}
	
	public void circle2(int i){
		int[] arr = new int[i/4*4];
		for(int j=0; j<i/4; j++){
			arr[j*4] = j*4;
			arr[j*4+1] = j*4 + 1;
			arr[j*4+2] = j*4 + 2;
			arr[j*4+3] = j*4 + 3;
		}
	}
	
	@Test
	public void test(){
		int i = 300000000;
		System.out.println(System.currentTimeMillis());
		circle1(i);
		System.out.println(System.currentTimeMillis());
		circle2(i);
		System.out.println(System.currentTimeMillis());
	}
	
}
