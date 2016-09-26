package com.li.tools.utils.classhandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.li.tools.utils.CharsetTest;
import com.li.tools.utils.jedis.datacache.dao.RedisCollectionDao;
import com.li.tools.utils.jedis.datacache.dao.RedisListDao;
import com.li.tools.utils.jedis.datacache.template.JedisMapTemplate;

/**
 * @author lijuntao
 * @date 2016-9-23
 */
public class ClassCreater {
    private List<String> list;
    public List<String> getList() {
        return list;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    @Test
    public void create(){
	Class<?> cl = RedisListDao.class;
	String class1 = cl.getName();
	String class11 = cl.getSimpleName();
	String class111 = class11.substring(0,1).toLowerCase()+class11.substring(1);
	String adapter11 = class11+"Adapter";
	Method[] methods = cl.getDeclaredMethods();
	Set<String> importSet = new HashSet<String>();
	String package1 = "package ";
	package1 += substringLastOf(class1,".") + ";";
	list = new ArrayList<String>();
	list.add(package1);
	for(String str : importSet){
	    list.add("import "+str+";");
	}
	list.add("public class "+adapter11+"{");
	list.add("private "+class11+" "+class11+";");
	list.add("public "+adapter11+"("+class11+" "+getFirstLowerCase(class11)+"){");
	list.add("    this."+class11+" = "+getFirstLowerCase(class11)+";");
	list.add("}");
	
	for(Method method:methods){
	    MethodParse methodParse = new MethodParse();
	    String string = methodParse.parse(method);
	    list.add(string+"{");
	    String code = "    ";
	    if(!"void".equals(methodParse.getMethodReturn()))
		code += "return ";
	    code += class111+".";
	    code += methodParse.getMethodName()+"(";
	    code += getZhiXingParameters(methodParse.getZhiXingParameters());
	    code += class111+");";
	    list.add(code);
	    list.add("}");
	}
	list.add("}");
	System.out.println(printList(list));
    }
    
    public static String printList(List<String> list){
	StringBuffer buffer = new StringBuffer();
	for(String s:list){
	    buffer.append(s+"\n");
	}
	return buffer.toString();
    }
    public static String getZhiXingParameters(String[] ss){
	String sss = "";
	if(ss==null || ss.length ==0)
	    return sss;
	for(int i=0;i<ss.length;i++){
	    sss += ss[i];
	    if(i != ss.length)
		sss += ",";
	}
	return sss;
    }
    
    public String getFirstLowerCase(String s){
	return s.substring(0,1).toLowerCase() + s.substring(1);
    }
    public String substringLastOf(String s,String regex){
	String ss = "";
	if(s==null)
	    return ss;
	int lastIndexOf = s.lastIndexOf(regex);
	if(lastIndexOf!=-1)
	    ss =  s.substring(0, lastIndexOf);
	return ss;
    }
    public static void main(String[] args) {
	new ClassCreater().create();
    }
}
