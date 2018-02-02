package com.li.tools.utils.regex;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.collect.Lists;

public class PatternTest {
	@Test
	public void lookAheadPositiveAssert(){
		Pattern compile = Pattern.compile("((?<=SmallDing)\\d+)");
		String value = "SmallDing520";
		Matcher matcher = compile.matcher(value);
		while(matcher.find()){
			System.out.println("结果：" + matcher.group(1));
		}
	}
	
	public static List<String> subElement(String str, String begin, String end){
		List<String> list = Lists.newArrayList();
		int i = 0;
		while(true){
			int beginIndex = str.indexOf(begin, i);
			if(beginIndex == -1 || beginIndex == str.length() - 1)
				break;
			int endIndex = str.indexOf(end, beginIndex + begin.length());
			if(endIndex == -1)
				break;
			list.add(str.substring(beginIndex, endIndex + end.length()));
			i = endIndex + end.length();
		}
		return list;
	}
	
	@Test
	public void test111(){
		Pattern compile = Pattern.compile("(\\u4e0e[^\\u6709]+\\u6709+\\u5173)");
		compile = Pattern.compile("(\\u4e0e[\\s\\S]+?\\u6709\\u5173)");
		Matcher matcher = compile.matcher("与1313与关有23有关有关关与与12313与关关有有有关关有关关有有关关有关与13234253与有关与2353与有关");
		//matcher = compile.matcher("与1313与");
		while(matcher.find()){
			System.out.println(matcher.group(1));
		}
	}
	
	public List<String> subStringListBetween(String value, String begin, String end){
		begin = getUnicodeStr(begin).replaceAll("\\\\", "\\\\");
		end = getUnicodeStr(end).replaceAll("\\\\", "\\\\");
		Pattern compile = Pattern.compile("(" + begin + "[\\s\\S]+?" + end +")");
		Matcher matcher = compile.matcher(value);
		List<String> list = new ArrayList<String>();
		while(matcher.find()){
			list.add(matcher.group(1));
		}
		return list;
	}
	
	@Test
	public void testsa(){
		String value = "有23t7232kjhds的上岛咖啡是否又有有有刷卡机电话是否 啥地方客户有有有山东矿机发货快速放大有有";
		String begin = "有";
		String end = "有有";
		List<String> list = subStringListBetween(value, begin, end);
		System.out.println(Arrays.toString(list.toArray()));
	}
	
	
	@Test
	public void test1121(){
		String begin = getUnicodeStr("与").replaceAll("\\\\", "\\\\");
		String end = getUnicodeStr("有关").replaceAll("\\\\", "\\\\");
		Pattern compile = Pattern.compile("(" + begin + "[\\s\\S]+?" + end +")");
		Matcher matcher = compile.matcher("与()(13131223)与关有有关有关关与与12313与关关有有有关关有关关有有关关有关与13234253与有关与2353与有关");
		while(matcher.find()){
			System.out.println(matcher.group(1));
		}
	}
	
	@Test
	public void test12121() throws UnsupportedEncodingException{
		String str = "中国";  
	    String[] encoding = { "Unicode", "UnicodeBig", "UnicodeLittle", "UnicodeBigUnmarked",  
	    "UnicodeLittleUnmarked", "UTF-16", "UTF-16BE", "UTF-16LE" };  
	     
	    for (int i = 0; i < encoding.length; i++) {  
	    	System.out .printf("%-22s %s%n", encoding[i], bytes2HexString(str.getBytes(encoding[i])));  
	    }
	}
	
	public static String bytes2HexString(byte[] bys) {  
	    char[] chs = new char[bys.length * 2 + bys.length - 1];  
	    for (int i = 0, offset = 0; i < bys.length; i++) {  
	        if (i > 0) {  
	            chs[offset++] = ' ';  
	        }  
	        chs[offset++] = HEX[bys[i] >> 4 & 0xf];  
	        chs[offset++] = HEX[bys[i] & 0xf];  
	    }  
	    return new String(chs);  
	} 
	
	public static char[] HEX = {'0', '1','2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e','f'};
	
	@Test
	public void test123121() throws UnsupportedEncodingException{
		String str = "中国"; 
		System.out.println(getUnicodeStr(str));
	}
	
	public static String getUnicodeStr(String s){
		if(s == null || s.length() == 0)
			return s;
		StringBuffer buffer = new StringBuffer();
		for(char c : s.toCharArray()){
			buffer.append(getUnicodeStr(c));
		}
		return buffer.toString();
	}
	
	public static String getUnicodeStr(char c){
		char[] cs = new char[6];
		cs[0] = '\\';
		cs[1] = 'u';
		for(int i=0;i<4;i++){
			cs[i + 2] = HEX[(c >>(3 - i)*4 & 0xf)];
		}
		return new String(cs);
	}
	
	@Test
	public void test22(){
		Pattern compile = Pattern.compile("Java(?:6|7)");
		Matcher matcher = compile.matcher("Java6 Java7");
		while(matcher.find()){
			System.out.println(matcher.group(1));
		}
	
	}
}
