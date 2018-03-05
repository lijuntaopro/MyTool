package com.li.tools.utils.algorithm;

import org.junit.Test;

/**
 * 约瑟夫环 author lijuntao date 2018年2月5日
 */
public class JosephCircle {
	
	@Test
	public void test111(){
		long c2 = System.currentTimeMillis();
		int i = circle4(435000, 10000);
		int[] arrs2 = {i};
		long length2 = System.currentTimeMillis() - c2;
		System.out.println("-------------------");
		System.out.println("circle2 time:" + c2);
		System.out.println("circle2 cost:" + length2);
		System.out.println(arrs2[arrs2.length - 1]);
//		System.out.println(Arrays.toString(arrs2));
		System.out.println("-------------------");
		
		long cL = System.currentTimeMillis();
		int[] arrL = circleLink(435000, 10000);
		long lengthL = System.currentTimeMillis() - cL;
		System.out.println("-------------------");
		System.out.println("circleLink time:" + cL);
		System.out.println("circleLink cost:" + lengthL);
		System.out.println(arrL[arrL.length - 1]);
//		System.out.println(Arrays.toString(arrL));
		System.out.println("-------------------");
	}

	public int[] circleLink(int n, int m) {
		//采用链表实现
		int[] linked = new int[n];
		int[] result = new int[n];
		//构建链表
		for(int i=0; i<n; i++){
			linked[i] = i+1;
		}
		linked[n-1] = 0;
		//初始化开始节点
		int prev = n-1;
		int cur = 0;
		int everySize = 0;
		int selectedSize = 0;
		while(true){
			if (++everySize == m) {
				//cur 被选中， 移除链表：把当前节点指向值赋予之前节点指向值， 并把下个节点设置为当前节点
				result[selectedSize++] = cur + 1;
				linked[prev] = linked[cur];
				cur = linked[cur];
				everySize = 0;
				if(selectedSize == n){
					return result;
				}
			}else{
				prev = cur;
				cur = linked[cur];
			}
		}
	}
	
	public int[] circle2(int n, int m) {
		int[] arrs = new int[n];
		for(int i=0; i<n; i++) 
			arrs[i] = i+1;
		int select = 0;
		int cur = 0;
		while(true){
			if(n == 1) return arrs;
			cur = cur + m - 1;
			if(cur >= n){
				cur = cur % n;
			}
			int temp = arrs[cur+select];
			for(int j=cur+select; j>select; j--){
				arrs[j] = arrs[j-1];
			}
			arrs[select] = temp;
			select++;
			n--;
		}
	}
	
	public int circle4(int n, int m) {
		int[] arrs = new int[n];
		for(int i=0; i<n; i++) 
			arrs[i] = i+1;
		int select = 0;
		int cur = 0;
		while(true){
			cur = cur + m - 1;
			if(cur >= n){
				cur = cur % n;
			}
			int temp = arrs[cur+select];
			if(cur<=n/2-1){
				for(int j=cur+select; j>select; j--){
					arrs[j] = arrs[j-1];
				}
				arrs[select] = temp;
				select++;
			}else{
				for(int j=cur+select; j<select + n -1; j++){
					arrs[j] = arrs[j+1];
				}
				arrs[select+n-1] = temp;
			}
			n--;
			if(n == 1) return arrs[select];
		}
	}
	
	public int[] circle3(int n, int m) {
		int[] arrs = new int[n];
		for(int i=0; i<n; i++) 
			arrs[i] = i+1;
		int i = 0;
		while(true){
			i = i + m - 1;
			if(i >= n){
				i = i % n;
			}
			int temp = arrs[i];
			for(int j=i; j<n-1; j++){
				arrs[j] = arrs[j+1];
			}
			arrs[n-1] = temp;
			n--;
			if(n == 1) return arrs;
		}
	}
	
	/**
	 * 70倍差，两个方法
	 * author lijuntao
	 * date 2018年2月5日
	 */
	@Test
	public void test(){
		long c2 = System.currentTimeMillis();
		int[] arrs = circle2(100000, 10);
		long length2 = System.currentTimeMillis() - c2;
		System.out.println("-------------------");
		System.out.println("circle2:" + c2);
		System.out.println("circle2:" + length2);
		System.out.println(arrs[arrs.length - 1]);
//		System.out.println(Arrays.toString(arrs));
		System.out.println("-------------------");
		
//		long c = System.currentTimeMillis();
//		int i = circle(1000000, 10);
//		long length = System.currentTimeMillis() - c;
//		System.out.println("-------------------");
//		System.out.println("circle:" + c);
//		System.out.println("circle:" + length);
//		System.out.println(i);
//		System.out.println("-------------------");
		
		long c4 = System.currentTimeMillis();
		int i4 = circle4(100000, 10);
		long length4 = System.currentTimeMillis() - c4;
		System.out.println("-------------------");
		System.out.println("circle4:" + c4);
		System.out.println("circle4:" + length4);
		System.out.println(i4);
		System.out.println("-------------------");
		
		long c3 = System.currentTimeMillis();
		int[] arrs3 = circle3(100, 2023);
		long length3 = System.currentTimeMillis() - c3;
		System.out.println("-------------------");
		System.out.println("circle3:" + c3);
		System.out.println("circle3:" + length3);
		System.out.println(arrs3[0]);
//		System.out.println(Arrays.toString(arrs));
		System.out.println("-------------------");
	}
}
