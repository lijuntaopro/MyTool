package com.li.tools.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BackTrack {
	
	public void testRecursion(){
		
	}
	
	public void testPrimeRing(){
		
	}
	
	public List<int[]> primeRing(int n){
		List<int[]> list = new ArrayList<int[]>();
		if(n < 2 || n%2 == 1)
			return list;
		primeRing(1, n, new int[n], new boolean[n], list);
		return list;
	}
	
	/**
	 * 素数环递归算法
	 * author lijuntao
	 * date 2018年2月2日
	 */
	private void primeRing(int i, int n, int[] is, boolean[] bs, List<int[]> list) {
		for(int j=1; j< n+1; j++){
			if(!bs[j - 1] && (i == 1 || isPrime(is[i-2] + j))){
				is[i-1] = j;
				if(i == n){
					if(isPrime(is[0] + is[n-1])){
						int[] copy = new int[n];
						System.arraycopy(is, 0, copy, 0, n);
						list.add(copy);
					}
				}else{
					bs[j-1] = true;
					primeRing(i+1, n, is, bs, list);
					bs[j-1] = false;
				}
			}
		}
	}
	
	private void nEn(int n){
		int i = 1;
		while(i <= n){
			for(int j=0; j<n; j++){
				
			}
			i++;
		}
	}
	
	/**
	 * 素数环递推算法
	 * author lijuntao
	 * date 2018年2月2日
	 */
	private List<int[]> primeRing2(int i, List<int[]> list) {
		if(i%2 == 1 || i < 2){
			return new ArrayList<int[]>();
		}
		if(i == 2){
			list.add(new int[]{1, 2});
			list.add(new int[]{2, 1});
		}
		List<int[]> list2 = primeRing2(i - 2, list);
		List<int[]> newList =new ArrayList<int[]>();
		for(int[] arrs : list2){
			for(int j=0; j<i-2; j++){
				for(int k=0; k<i-1; k++){
					if(j == 0){
						int m = arrs[0];
						int n = arrs[arrs.length - 1];
					}else if(j != i - 2){
						int m = arrs[j - 1];
						int n = arrs[j];
					}
							
				}
			}
		}
		return null;
	}
	
	@Test
	public void test(){
		List<int[]> list = primeRing(16);
		for(int[] arrs : list){
			System.out.println(Arrays.toString(arrs));
		}
	}

	public boolean isPrime(int i) {
		if(i < 3)
			return true;
		int sqrt = (int) Math.sqrt(i);
		for(int j=2;j<=sqrt;j++){
			if(i%j == 0)
				return false;
		}
		return true;
	}
	
}
