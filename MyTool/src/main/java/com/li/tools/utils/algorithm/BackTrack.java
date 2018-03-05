package com.li.tools.utils.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.li.tools.utils.CommonUtils;

public class BackTrack {
	
	/**
	 * 素数环递归算法
	 * author lijuntao
	 * date 2018年2月2日
	 */
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
	
	/**
	 * m^n循环， n层循环
	 * author lijuntao
	 * date 2018年2月5日
	 */
	public List<int[]> mEn(int m, int n){
		List<int[]> list = new ArrayList<int[]>();
		int[] arrs = new int[n];
		for(int i=0; i<n; i++){
			arrs[i] = 1;
		}
		int cenci = 0;
		while(true){
			for(int j=1; j<=m; j++){
				arrs[n - 1] = j;
				int[] copy = new int[n];
				System.arraycopy(arrs, 0, copy, 0, n);
				list.add(copy);
			}
			cenci = n;
			while(cenci - 2 >= 0){
				if(arrs[cenci - 2] != m){
					arrs[cenci - 2]++;
					break;
				}
				arrs[cenci - 2] = 1;
				cenci--;
			}
			if(cenci - 2 < 0){
				break;
			}
		}
		return list;
	}
	
	/**
	 * m^n循环， n层循环, m不重复 == Cmn
	 * author lijuntao
	 * date 2018年2月5日
	 */
	public List<int[]> mEnNoRepeat(int m, int n){
		List<int[]> list = new ArrayList<int[]>();
		if(m < n){
			return list;
		}
		int cenci = 1;
		int[] arrs = new int[n];
		boolean[] repeatCheck = new boolean[m+1];
		while(true){
			/**
			 * 第1 --> n-1构造
			 */
			for(int i=cenci-1; i< n - 1; i++){
				for(int j=1; j<m; j++){
					if(!repeatCheck[j]){
						arrs[i] = j;
						repeatCheck[j] = true;
						break;
					}
				}
			}
			
			/**
			 * 第n次构造
			 */
			for(int j=1; j<=m; j++){
				if(!repeatCheck[j]){
					arrs[n - 1] = j;
					int[] copy = new int[n];
					System.arraycopy(arrs, 0, copy, 0, n);
					list.add(copy);
				}
			}
			
			/**
			 * 由第n列，不断退位
			 */
			cenci = n;
			a:while(cenci - 2 >= 0){
				repeatCheck[arrs[cenci - 2]] = false;
				for(int j = arrs[cenci - 2] + 1; j <= m; j++){
					if(!repeatCheck[j]){
						repeatCheck[j] = true;
						arrs[cenci - 2] = j;
						break a;
					}
				}
				cenci--;
			}
			
			/**
			 * 如果第一列也退位了，证明已经循环完了
			 */
			if(cenci - 2 < 0){
				break;
			}
		}
		return list;
	}
	
	@Test
	public void test1(){
		List<int[]> list = mEn(3, 3);
		for(int[] arrs : CommonUtils.nullToEmpty(list)){
			System.out.println(Arrays.toString(arrs));
		}
	}
	
	@Test
	public void test2(){
		List<int[]> list = mEnNoRepeat(4, 2);
		for(int[] arrs : CommonUtils.nullToEmpty(list)){
			System.out.println(Arrays.toString(arrs));
		}
	}
	
	@Test
	public void test(){
		List<int[]> list = primeRing(16);
		for(int[] arrs : list){
			System.out.println(Arrays.toString(arrs));
		}
	}
	
	/**
	 * 素数环递推算法
	 * author lijuntao
	 * date 2018年2月2日
	 */
	public List<int[]> primeRing2(int i, List<int[]> list) {
		return null;
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
