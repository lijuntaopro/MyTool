package com.li.tools.utils.sort;

import java.util.Comparator;

import org.junit.Test;

public class MergeSort {
	public static <T> int countRunAndMakeAscending(T[] a, int lo, int hi, Comparator<? super T> c) {
		assert lo < hi;
		int runHi = lo + 1;
		if (runHi == hi)
			return 1;

		// Find end of run, and reverse range if descending
		if (c.compare(a[runHi++], a[lo]) < 0) { // Descending
			while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) < 0)
				runHi++;
			reverseRange(a, lo, runHi);
		} else { // Ascending
			while (runHi < hi && c.compare(a[runHi], a[runHi - 1]) >= 0)
				runHi++;
		}

		return runHi - lo;
	}
	
	private static void reverseRange(Object[] a, int lo, int hi) {
        hi--;
        while (lo < hi) {
            Object t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }
	
	@Test
	public void test(){
		Integer[] is = {1,2,3,4,5};
		int i = countRunAndMakeAscending(is, 0, 4, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		System.out.println(i);
	}
}
