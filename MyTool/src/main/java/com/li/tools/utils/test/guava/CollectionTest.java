package com.li.tools.utils.test.guava;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;

public class CollectionTest {
	@Test
	public void test(){
		Map<String, Object> map = Maps.newHashMap();
		List<String> list = Lists.newArrayList();
		Set<String> set = Sets.newHashSet();
		
		Integer[] array = ObjectArrays.newArray(int.class, 10);
		
		ImmutableMap<Integer,Integer> map2 = ImmutableMap.of(11, 11, 12, 12);
		
		System.out.println(map);
		System.out.println(list);
		System.out.println(set);
	}
}
