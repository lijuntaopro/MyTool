package com.li.tools.utils.classhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author lijuntao
 * @date 2016-9-23
 */
public class MethodModifiers {
    public static final int CC_PUBLIC = 0x0001;
    public static final int CC_PRIVATE = 0x0002;
    public static final int CC_PROTECTED = 0x0004;
    public static final int CC_STATIC = 0x0008;
    public static final int CC_FINAL = 0x0010;
    public static final int CC_SYNCHRONIZED = 0x0020;
    public static final int CC_BRIDGE = 0x0040;
    public static final int CC_VARARGS = 0x0080;
    public static final int CC_NATIVE = 0x0100;
    public static final int CC_ABSTRACT = 0x0400;
    public static final int CC_STRICT = 0x0800;
    public static final int CC_SYNTHETIC = 0x1000;
    public static final String[] METHOD_NAME = {"public","private","protected","static","final","synchronized","bridge","varargs","native","","abstract","strict","synthetic"};
    public static String[] transformModifiers(int i){
	if(i<1 || i>=0x2000)
	    return null;
	List<String> list = new ArrayList<String>();
	for(int j=0;j<METHOD_NAME.length;j++){
	    String string = getModifier(i,j);
	    if(string!=null && !"".equals(string))
		list.add(string);
	}
	return list.toArray(new String[list.size()]);
    }
    public static String getModifier(int i,int b){
	if((i>>b&1)==1)
	    return METHOD_NAME[b];
	return null;
    }
    
    public static String getModifierStrings(int i){
	String[] strings = transformModifiers(i);
	String s = "";
	for(String string:strings)
	    s += string;
	return s;
    }
    @Test
    public void test(){
	int i = 0x1001;
	System.out.println(Arrays.toString(transformModifiers(i)));
    }
}
