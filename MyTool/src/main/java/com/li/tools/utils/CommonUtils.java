package com.li.tools.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CommonUtils {
	
	public static <T> boolean isEmpty(Collection<T> c){
		return c == null || c.size() == 0;
	}
	
	public static <T> boolean isEmpty(CharSequence cs){
		return cs == null || cs.length() == 0;
	}
	
	public static <K, V> boolean isEmpty(Map<K,V> m){
		return m == null || m.size() == 0;
	}
	
	public static <T> List<T> nullToEmpty(List<T> list){
		return list == null?new ArrayList<T>():list;
	} 
	
}
