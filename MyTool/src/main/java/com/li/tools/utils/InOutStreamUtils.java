package com.li.tools.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * @author lijuntao
 * @date: 2016-4-7 下午1:40:25
 * 
 */
/**
 * 测试java中流的运用
 *
 */
public class InOutStreamUtils {
	/**
	 * 测试输入流
	 * InputStream 基本接口
	 * FilterInputStream 可以包含其他的输入流FilterInputStream(inputStream)
	 * 		BufferedInputStream
	 * 		DataInputStream
	 * 		DigestInputStream
	 *      CipherInputStream
	 * FileInputStream
	 * 
	 */
	public void testInput() throws Exception{
		new FileInputStream(new File(""));
		new FileInputStream("");
		new FileInputStream(new FileDescriptor());
	}
	/**
	 * 
	 * @throws Exception
	 * FileInputStream：直接继承mark, markSupported, reset。
	 * inputstream的这些方法markSupported为false，其他不操作，即不支持。所以不要使用这些方法
	 */
	@Test
	public void testFileInputStream() throws Exception{
		InputStream bis = new FileInputStream("C:/wo.txt");
		boolean isFirst = true;
		int i = 0;
		int count = 0;
		System.out.println("是否支持标记："+bis.markSupported());
		while((i=bis.read())!=-1&&count<16){
			count++;
			if(isFirst){
				isFirst = false;
				System.out.print("读出的字节:[");
			}else
				System.out.print(",");
			System.out.print(((char)i));
		}
		if(!isFirst)
			System.out.println("]");
	}
	
	/**
	 * 
	 * @throws Exception
	 * ByteArrayInputStream：可以重设，可以标记，可以关闭，但关闭不产生效果，即可以继续读
	 * 创建时，如果设置偏移量，该偏移量被mark一下，否则mark：0
	 * 每次reset就来到mark位置重新读取
	 */
	@Test
	public void testByteArrayInputStream() throws Exception{
		byte [] bs = {-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17};
		InputStream bis = new ByteArrayInputStream(bs,0,bs.length);
		System.out.println("是否支持标记："+bis.markSupported());
		boolean isFirst = true;
		int i = 0;
		int count = 0;
		while((i=bis.read())!=-1&&count<16){
			count++;
			if(isFirst){
				isFirst = false;
				System.out.print("读出的字节:[");
			}else
				System.out.print(",");
			System.out.print(((byte)i));
			bis.mark(count);
			bis.reset();
		}
		if(!isFirst)
			System.out.println("]");
	}
	
	public void e(){
	}
	
	
	/**
	 * @author lijuntao
	 * @date: 2016-4-7 下午2:47:08
	 * 测试加密输入流
	 * CipherInputStream的mark, markSupported, reset，取决于输入的流，输入的流可以就可以
	 */
	
	@Test
	public void testCipherInputStream() throws Exception{
		String contentStr = "aaaabbbbccccdddd李俊涛";
		byte[] content = contentStr.getBytes();
		String ase2 = "AES/CBC/NoPadding";
		String ase = "AES/CBC/PKCS5Padding";
		String key = "abcde123abcde123";
		String iv = "234sdqwasdasd234";
		Cipher cipher = getCipher(ase, key, iv, true);
		byte[] bs = cipher.doFinal(content);
		Cipher cipher2 = getCipher(ase, key, iv, false);
		byte[] bs2 = cipher2.doFinal(bs);
		System.out.println("加密的字段:"+contentStr);
		System.out.println("加密的字节:"+Arrays.toString(content));
		System.out.println("加密后字节:"+Arrays.toString(bs));
		System.out.println("解密后字节:"+Arrays.toString(bs2));
		System.out.println("解密后字段:"+new String(bs2));
		int i = 0;
		boolean isFirst = true;
		InputStream is = new StringBufferInputStream(contentStr);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		System.out.println("CipherInputStream是否支持标记："+cis.markSupported());
		while((i=cis.read())!=-1){
			if(isFirst){
				isFirst = false;
				System.out.print("用加密流读出的字节:[");
			}else
				System.out.print(",");
			System.out.print(((byte)i));
		}
		System.out.println("]");
		CipherInputStream cis2 = new CipherInputStream(new ByteArrayInputStream(bs), cipher2);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		isFirst = true;
		while((i=cis2.read())!=-1){
			if(isFirst){
				isFirst = false;
				System.out.print("用解密流读出的字节:[");
			}else
				System.out.print(",");
			System.out.print(((byte)i));
			bos.write(i);
		}
		System.out.println("]");
		System.out.println("用解密流读出的字段:"+new String(bos.toByteArray()));
		bos.close();
		cis2.reset();
		isFirst = true;
		while((i=cis2.read())!=-1){
			if(isFirst){
				isFirst = false;
				System.out.print("再读解密流读出的字节:[");
			}else
				System.out.print(",");
			System.out.print(((byte)i));
			bos.write(i);
		}
		if(!isFirst)
			System.out.println("]");
		
	}
	
	
	public Cipher getCipher(String ase,String key ,String iv,boolean encript_mode) throws Exception{
		Cipher cipher = Cipher.getInstance(ase);
		SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
		if(encript_mode)
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		else
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		return cipher;	
	}
}
