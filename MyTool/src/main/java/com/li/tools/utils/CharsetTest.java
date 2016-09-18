package com.li.tools.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class CharsetTest {
	@Test
	public void yes() throws Exception{
		char c1 = '中';
		byte[] bs = new byte[2];
		bs[0] = (byte) ((c1>>8)&0xff);
		bs[1] = (byte) (c1&0xff);
		String string = new String(bs,"UTF-16");
		System.out.println(string);
		String s = "a";
		String s1 = "中";
		System.out.println(s.getBytes().length);
		System.out.println(s1.getBytes().length);
		
		String ss = "卡绝对是个好51243asjgd";
		char[] cs = ss.toCharArray();
		byte[] bs2 = new byte[cs.length*2];
		for(int i=0;i<cs.length;i++){
			bs2[2*i] = (byte) ((cs[i]>>8)&0xff);
			bs2[2*i+1] = (byte) (cs[i]&0xff);
		}
		String string2 = new String(bs2,"utf-16");
		System.out.println(string2);
	}
	@Test
	//对于最后一个字符英文字符在0-127之间的字符串，进行编码后，字节数组最后的一个字节必定为该字符的ASCII值
	public void tsetr() throws UnsupportedEncodingException{
		String a = "aaaaaaabb";
		char[] cs = new char[32];
		for(int i=0;i<cs.length;i++){
			cs[i] = (char) i;
		}
		a = new String(cs);
		byte[] bs = a.getBytes("gbk");
		System.out.println(Arrays.toString(bs));
	}
	
	public byte[] aesEncript(String content,String key,String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
		System.out.println("content:"+content);
		System.out.println("content,bs:"+Arrays.toString(content.getBytes()));
		byte[] bs = cipher.doFinal(content.getBytes());
		return bs;
	}
	
	public String aesDecript(byte[] content,String key,String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		byte[] bs = cipher.doFinal(content);
		StringBuffer bf = new StringBuffer();
		for(byte b:bs)
			bf.append((char)b);
		System.out.println(bf.toString());
		return bf.toString();
	}
	@Test
	public void test(){
		String content = "aaaabbbbccccdddd";
		//key必须为16位
		String key = "abcde123abcde123abcde123abcde123abcde123abcde123";
		//iv必须为16位
		String iv = "234sdqwasdasd234";
		try {
			byte[] bs = aesEncript(content, key, iv);
			System.out.println("encript:size="+bs.length+";"+Arrays.toString(bs));
			aesDecript(bs,key,iv);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testCipherInputStream() throws Exception{
		String contentStr = "aaaabbbbccccddd";
		byte[] content = contentStr.getBytes();
		String ase = "AES/CBC/NoPadding";
		String ase2 = "AES/CBC/PKCS5Padding";
		String key = "abcde123abcde123";
		String iv = "234sdqwasdasd234";
		Cipher cipher = getCipher(ase, key, iv, true);
		//byte[] bs = cipher.doFinal(content);
		//System.out.println(contentStr+":="+Arrays.toString(bs));
		int i = 0;
		InputStream is = new StringBufferInputStream(contentStr);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		while((i=cis.read())!=-1){
			System.out.print(((byte)i)+" ");
		}
		System.out.print(":=");
		//System.out.println(Arrays.toString(bs));
		
		/*
		 * 证明String可以转化为InputStream
		 */
//		while((i=is.read())!=-1){
//			System.out.print(i);
//		}
//		System.out.print(":=");
//		System.out.println(Arrays.toString(content));
		
	}
	@Test
	public void testCipherInputStream2() throws Exception{
		byte[] content = {-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17};
		String ase = "AES/CBC/NoPadding";
		String key = "abcde123abcde123";
		String iv = "234sdqwasdasd234";
		Cipher cipher = getCipher(ase, key, iv, true);
		byte[] bs = cipher.doFinal(content);
		int i = 0;
		InputStream is = new ByteArrayInputStream(content);
		CipherInputStream cis = new CipherInputStream(is, cipher);
//		while((i=cis.read())!=-1){
//			System.out.print(i);
//		}
//		System.out.print(":=");
//		System.out.println(Arrays.toString(bs));
		
		/*
		 * 证明String可以转化为InputStream
		 */
		while((i=is.read())!=-1){
			System.out.print(i+" ");
		}
		System.out.print(":=");
		System.out.println(Arrays.toString(content));
		
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
