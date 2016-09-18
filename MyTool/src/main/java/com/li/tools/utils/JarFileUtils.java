package com.li.tools.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;

public class JarFileUtils {
	@Test
	public void test(){
		unZip("C:/jarfile3.zip","C:/jarfile8");
	}
	
	public void unZip(String sourcePath,String targetPath){
		System.out.println("sourcePath:"+sourcePath+";targetPath:"+targetPath);
		FileOutputStream os = null;
		String filePath = null; 
		try {
			File file2 = new File(sourcePath);
			JarFile file = new JarFile(file2);
			boolean doneMkdir = false;
			if(targetPath!=null&&!"".equals(targetPath)){
				try {
					File targetDirect = new File("targetPath");
					targetDirect.mkdir();
					doneMkdir = true;
					System.out.println("可以创建文件夹："+targetPath);
					filePath = targetPath;
				} catch (Exception e) {
					System.out.println("无法创建文件夹："+targetPath);
				}
			}
			if(!doneMkdir){
				filePath = file2.getPath();
				int j = filePath.lastIndexOf(".");
				if(j!=-1)
					filePath = filePath.substring(0,j);
			}
			File os2 = new File(filePath);
			System.out.println("最后目标文件夹路径："+os2.getAbsolutePath());
			os2.mkdir();
			Enumeration<JarEntry> entries = file.entries();
			while(entries.hasMoreElements()){
				JarEntry jarEntry = entries.nextElement();
				String name = jarEntry.getName();
				System.out.println("name:"+name);
//				System.out.println("1:"+new String(name.getBytes("iso-8859-1"),"gbk"));
//				System.out.println("1:"+new String(name.getBytes("utf-8"),"gbk"));
//				System.out.println("1:"+new String(name.getBytes("iso-8859-1"),"utf-8"));
//				System.out.println("1:"+new String(name.getBytes("utf-8"),"utf-8"));
				File file3 = new File(filePath+File.separator+name);
				if(jarEntry.isDirectory()){
					System.out.println("shige");
					file3.mkdir();
				}else{
					System.out.println("jarEntry"+(jarEntry==null));
					
					InputStream is = file.getInputStream(jarEntry);
					System.out.println("流是否为空："+(is==null));
					if(is==null){
						Attributes attributes = jarEntry.getAttributes();
//						Certificate[] certificates = jarEntry.getCertificates();
//						System.out.println("certificates"+certificates.length);
						if(attributes!=null)
							for(Entry<Object, Object> s:attributes.entrySet()){
								System.out.println("key="+s.getKey()+";value="+s.getValue());
							}
						System.out.println("再编码："+new String(name.getBytes("utf-8"),"utf-16"));
						System.out.println("再编码："+new String(name.getBytes("utf-8"),"gbk"));
						System.out.println("再编码："+new String(name.getBytes("utf-8"),"utf-8"));
						System.out.println("再编码："+new String(name.getBytes("utf-8"),"gb2312"));
						continue;
					}else{
						os = new FileOutputStream(file3);
						int i=0;
						while((i=is.read())!=-1){
							os.write(i);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os!=null)
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	public void unZip(String sourcePath){
		unZip(sourcePath,null);
	}
}
