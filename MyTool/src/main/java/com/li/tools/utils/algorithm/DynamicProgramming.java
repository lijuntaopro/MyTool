package com.li.tools.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.li.tools.utils.CommonUtils;

/**
 * 动态规划
 * author lijuntao
 * date 2018年1月25日
 */
public class DynamicProgramming {
	
	/**
	 * 最长子序列
	 */
	public static int maxSequence(CharSequence cs1, CharSequence cs2){
		int[][] bss = new int[cs1.length() + 1][cs2.length() +1];
		List<Integer> insI = new ArrayList<Integer>();
		List<Integer> insJ = new ArrayList<Integer>();
		int max = 0;
		for(int i=0; i<cs1.length(); i++){
			for(int j=0; j<cs2.length(); j++){
				if(cs1.charAt(i) == cs2.charAt(j)){
					int newMax = bss[i][j] + 1;
					if(max < newMax){
						max = newMax;
						insI.add(i);
						insJ.add(j);
					}
					bss[i+1][j+1] = newMax;
				}else{
					bss[i+1][j+1] = Math.max(bss[i+1][j], bss[i][j+1]);
				}
			}
		}
//		println(bss);
		System.out.println("最大公共子序列：" + bss[cs1.length()][cs2.length()]);
		println(cs1, insI.toArray(new Integer[insI.size()]));
		println(cs2, insJ.toArray(new Integer[insJ.size()]));
		return bss[cs1.length()][cs2.length()];
	}
	
	/**
	 * 最长子串
	 */
	public static int maxString(CharSequence cs1, CharSequence cs2){
		if(CommonUtils.isEmpty(cs1) && CommonUtils.isEmpty(cs2))
			return 0;
		if(cs1.length() <= cs2.length()){
			CharSequence temp = cs1; cs1 = cs2; cs2 = temp;
		}
		int max = 0, x = 0;
		int[] lastEqualsLength = new int[cs2.length() + 1];
		for(int i=0; i<cs1.length(); i++){
			for(int j=cs2.length(); j>0; j--){
				if(cs1.charAt(i) == cs2.charAt(j-1)){
					lastEqualsLength[j] = lastEqualsLength[j - 1] + 1;
					if(lastEqualsLength[j] > max){
						max = lastEqualsLength[j];
						x = i;
					}
				}else{
					lastEqualsLength[j] = 0;
				}
			}
//			System.out.println(Arrays.toString(lastEqualsLength));
		}
		System.out.println(String.format("shorter:%s\nlonger:%s\nindex:%d\nlength:%d\nequals：%s", cs2, cs1, x + 1 - max, max, cs1.subSequence(x + 1 - max, x + 1)));
		return max;
	}
	
	/**
	 * 最长递增子序列
	 */
	
	public static int maxIncreString(CharSequence cs1){
		int[] is = new int[cs1.length()];
		for(int k=0;k<cs1.length();k++){
			is[k] = 1;
		}
		int max = 1;
		List<Integer> indexs = new ArrayList<Integer>();
		indexs.add(0);
		for(int j=1; j<cs1.length(); j++){
			for(int i=0; i<j; i++){
				if(cs1.charAt(i) < cs1.charAt(j) && is[j] < is[i] + 1){
					is[j] = is[i] + 1;
					if(is[j] > max){
						max = is[j];
						indexs.add(j);
					}
				}
			}
		}
		System.out.println(Arrays.toString(is));
		println(cs1, indexs.toArray(new Integer[indexs.size()]));
		return max;
	}

	public static void main(String[] args) {
		testMaxSequence();
		testMaxString();
		testMaxIncreString();
	}
	
	public static void testMaxSequence(){
		String cs1 = "1234567890abcdefghijklmnopqrstuvwxyz1234567890";
		String cs2 = "nqr890abpreikl60r90deeikl8790rhl90bddjba782k6890bdck9oblrx7nbcil9r7szmodebceil9pqr8txmrs90bdijr290";
		maxSequence(cs1, cs2);
	}
	
	public static void testMaxString(){
		String cs1 = "1234567890abcdefghijklmnopqrstuvwxyz1234567890";
		String cs2 = "nqr890abpreikl60r90deeikl8790rhl90bddjba782k6890bdck9oblrx7nbcil9r7szmodebceil9pqr8txmrs90bdijr290";
		System.out.println(maxString(cs1, cs2));
	}
	
	public static void testMaxIncreString(){
		String cs = "1234567890abcdefghijklmnopqrstuvwxyz1234567890";
		System.out.println(maxIncreString(cs));
	}
	
	public static <T> void println(int[][] bss){
		for(int i=0; i<bss.length; i++){
			System.out.print("[");
			for(int j=0; j<bss[i].length; j++){
				System.out.print(bss[i][j]);
				if(j != bss[i].length - 1){
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
	}
	
	public static void println(CharSequence cs1, Integer[] indexs){
		StringBuffer charBuf = new StringBuffer();
		charBuf.append("[");
		int i = 0;
		for(int index : indexs){
			if(i == 0){
				charBuf.append(cs1.charAt(index));
			}else{
				charBuf.append(", " + cs1.charAt(index));
			}
		}
		charBuf.append("]");
		System.out.println("------------------   print sequence start   -------------------");
		System.out.println(cs1);
		System.out.println(Arrays.toString(indexs));
		System.out.println(charBuf);
		System.out.println("------------------   print sequence end     -------------------");
	}
	
	/**
	 * 背包问题，递归求解
	 * author lijuntao
	 * date 2018年2月1日
	 */
	public int knapsack(int[] weight, int[] value, int[] results, int index, int capacity, final int oldCapacity){
		int result = 0;
		if(index == 0){
			if(capacity >= weight[index]){
				result = value[index];
			}
		}else if(capacity >= weight[index]){
			int last = knapsack(weight, value, results, index - 1, capacity, oldCapacity);
			int minutWeight = knapsack(weight, value, results, index - 1, capacity - weight[index], oldCapacity) + value[index];
			result = Math.max(last, minutWeight);
		}else{
			result = knapsack(weight, value, results, index - 1, capacity, oldCapacity);
		}
		if(capacity == oldCapacity){
			results[index] = result;
		}
		return result;
	}
	
	public int knapsack(int[] weights, int[] values, int capacity){
		int[] results = new int[weights.length];
		knapsack(weights, values, results, weights.length -1, capacity, capacity);
		System.out.println(Arrays.toString(results));
		return results[results.length - 1];
	}
	
	@Test
	public void test(){
		int[] weights = new int[]{2,2,4,5,6,7,3};
		int[] values = new int[]{5,4,7,11,8,9,9};
		int capacity = 8;
		System.out.println(knapsack(weights, values, capacity));
	}
	
	/**
	 * error
	 * author lijuntao
	 * date 2018年2月1日
	 */
	public void cycle(){
		int i = 0;
//		int v = 1;
		int n = 8;
		int[] arrs = {1, 2, 3, 4, 5, 6, 7, 8};
		while(true){
			boolean find = false;
			for(int j=i+1; j<n; j++){
				if(isPrime(arrs[i], arrs[j])){
					swap(arrs, i+1, j);
					find = true;
					return;
				}
			}
			if(find){
				i++;
			}else{
				i--;
				if(arrs[i] < n){
					arrs[arrs[i]] = arrs[arrs[i]] - 1;
					arrs[i] = arrs[i] + 1;
				}
			}
		}
	}
	
	public boolean isPrime(int x, int y){
		return true;
	}
	
	public void swap(int[] arrs, int x, int y){
		int temp = arrs[x];
		arrs[x] = arrs[y];
		arrs[y] = temp;
	}
	
}
