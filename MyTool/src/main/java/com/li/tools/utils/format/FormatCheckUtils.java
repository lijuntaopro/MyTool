package com.li.tools.utils.format;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class FormatCheckUtils {
    	private static String regexUrl = "(((https|http|ftp|fps|file)://)|(www\\.))(\\w+\\.)+\\w+(:\\d{1,5})?(/+[^/\\?]*)*\\?*";
    	private static String regexParam = "\\?+([a-zA-z_]\\w*=[^&]*)?(&+([a-zA-z_]\\w*=[^&]*)?)*";
	private static boolean test(String str,String regex){
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(str);
	    return m.matches();
	}
    	public static boolean checkLogOutUrl(String str){
    	    return test(str,regexUrl);
	}
	public static boolean checkLogOutUrlAndParam(String str){
	    return test(str,regexUrl + regexParam);
	}
	public static boolean checkAddress(String str){
		String regex = "";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	@Test
	public void test(){
	    boolean b = checkLogOutUrlAndParam("http://197.168.15.227:8002/ship-web/UUMSSCasServer/logout?sessionid=BA59FE8CA6DD06EEDA096F71952DE784");
	    System.out.println(b);
	}
}
