package com.li.tools.utils.rsa;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
/**
 * 
 * @author lijuntao
 * 对该RSA加解密的认知
 * 1、密钥保存到文件或者数据库，是Base64编码的字符串
 * 2、密钥的格式加载：私钥是pkcs8格式，公钥是x509格式
 * 3、执行本类加解密方法的参数，都是不经过编码的：即密钥是经过Base64解码过的字符串，明文是经过utf8等编码的格式，密文是加密后的字节数组不能经过编码
 * 4、实际使用：字符编码为utf8，传输编码为Base64，Base64采用common-codec.jar
 */
public class RSAUtils {
	private static RSAUtils rsaUtils;
	private RSAUtils(){
		super();
	}
	public static RSAUtils getInstance(){
		if(rsaUtils==null){
			synchronized(new Object()){
				if(rsaUtils==null)
					rsaUtils = new RSAUtils();
			}
		}
		return rsaUtils;
	}
	public static void main(String[] args) {
		RSAUtils generator = new RSAUtils();
		byte[] privateBytes = generator.loadRSAKey(new File("C:/Users/lijuntao/Desktop/pkcs8_rsa_private_key.pem"));
		byte[] publicBytes = generator.loadRSAKey(new File("C:/Users/lijuntao/Desktop/rsa_public_key.pem"));
		byte[] privateBytes2 = null;
		byte[] publicBytes2 = null;
		privateBytes2 = Base64.decodeBase64(privateBytes);
		publicBytes2 = Base64.decodeBase64(publicBytes);
		//Map<String, byte[]> pairKey = generator.generatePairKey();
		String s = "~!@#$%^&*()_+,./;'[]\\，s12356sssss12356sssss12356sssss12356sssss12356sssss12356sssss12356sssss12356sssss12356s--&&==";
		byte[] bs = s.getBytes();
		byte[] encrypt = generator.encrypt(privateBytes2, bs, true);
		System.out.println("encrypt:"+encrypt.length+":"+Arrays.toString(encrypt));
		byte[] encrypt2 = generator.decrypt(publicBytes2, encrypt, false);
		String string = new String(encrypt2);
		System.out.println(string);
	}
	/**
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@return 生成的私钥可以说是PKCS8格式
	 */
	public Map<String,byte[]> generatePairKey(){
		Map<String, byte[]> map = new HashMap<String,byte[]>();
		SecureRandom random = new SecureRandom();
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024,random);
			KeyPair keyPair = kpg.genKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			byte[] privateKeyBytes = privateKey.getEncoded();
			byte[] publicKeybytes = publicKey.getEncoded();
			
			System.out.println(Arrays.toString(privateKeyBytes));
			System.out.println(Arrays.toString(publicKeybytes));
			map.put("privateKey", privateKeyBytes);
			map.put("publicKey", publicKeybytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param pkcs8OrX509keys 经过Base64解码过的
	 * param:@param contexts 经过utf-8(或者其他)编码的字符串
	 * param:@param isPrivateKey
	 * param:@return
	 */
	public byte[] encrypt(byte[] pkcs8OrX509keys,byte[] contexts,boolean isPrivateKey){
		byte[] final1 = null;
		try {
			 Cipher cipher = Cipher.getInstance("RSA");
			 Key key = null;
			 if(isPrivateKey)
				 key =  getPKCS8PrivateKey(pkcs8OrX509keys);
			 else
				 key =  getX509PublicKey(pkcs8OrX509keys);
			 cipher.init(Cipher.ENCRYPT_MODE, key);
			 final1 = cipher.doFinal(contexts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return final1;
	}
	public byte[] encrypt(String Base64Encoded,String contexts,boolean isPrivateKey){
	    byte[] sourceKey = Base64.decodeBase64(Base64Encoded);
	    try {
		byte[] bs = contexts.getBytes("utf-8");
		return encrypt(sourceKey,bs,isPrivateKey);
	    } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	    }
	    return null;
	}
	/**
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param pkcs8OrX509keys 经过Base64解码过的
	 * param:@param cipherTexts 经过Base64解码过的
	 * param:@param isPrivateKey
	 * param:@return
	 */
	public byte[] decrypt(byte[] pkcs8OrX509keys,byte[] cipherTexts,boolean isPrivateKey){
		byte[] final1 = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			Key key = null;
			if(isPrivateKey)
				key =  getPKCS8PrivateKey(pkcs8OrX509keys);
			else
				key =  getX509PublicKey(pkcs8OrX509keys);
			cipher.init(Cipher.DECRYPT_MODE, key);
			final1 = cipher.doFinal(cipherTexts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return final1;
	}
	public byte[] decrypt(String keyOfBase64Encoded,String cipherTextBase64Encoded,boolean isPrivateKey){
	    byte[] sourceKeys = Base64.decodeBase64(keyOfBase64Encoded);
	    byte[] cipherTexts = Base64.decodeBase64(cipherTextBase64Encoded);
	    return decrypt(sourceKeys,cipherTexts,isPrivateKey);
	}
	/**
	 * 
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param bs 必须是RSA私钥且经过Base64解密过的
	 * param:@return
	 */
	public PrivateKey getPKCS8PrivateKey(byte[] bs){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(bs);
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param bs 必须是RSA公钥且经过Base64解密过的
	 * param:@return
	 */
	public PublicKey getX509PublicKey(byte[] bs){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec encodedPublicKey = new X509EncodedKeySpec(bs);
			PublicKey publicKey = keyFactory.generatePublic(encodedPublicKey);
			return publicKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param file
	 * param:@return 返回的是经过Base64解码的密钥
	 */
	public byte[] loadRSAKeyDecodedByBase64(File file){
		return Base64.decodeBase64(loadRSAKey(file));
	}
	/**
	 * 
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param file
	 * param:@return 返回的是没有经过Base64解码的
	 */
	public byte[] loadRSAKey(File file){
		try {
			FileReader is = new FileReader(file);
			BufferedReader buf = new BufferedReader(is);
			StringBuilder sb = new StringBuilder();
			String readLine = null;
	        while ((readLine = buf.readLine()) != null) { 
	        	if(!readLine.startsWith("-----"))
	        		sb.append(readLine);    
	        }
	        return sb.toString().getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * create:2016-9-8
	 * author:lijuntao
	 * param:@param file
	 * param:@param Base64bs 必须是经过base64编码过的
	 * param:@param textHaveStartAndEnd
	 * param:@param IsPrivateKey 是否私钥和公钥，私钥为true
	 */
	public void saveRSAKey(File file,byte[] Base64bs,boolean textHaveStartAndEnd,boolean IsPrivateKey){
		String lineSeparator = System.getProperty("line.separator", "\n");
		String privateName = "PRIVATE";
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			if(!IsPrivateKey)
				privateName = "PUBLIC";
			if(textHaveStartAndEnd)
				os.write(("-----BEGIN "+privateName+" KEY-----"+lineSeparator).getBytes("utf-8"));
			for(int i=1;i<=Base64bs.length;i++){
				os.write(Base64bs[i-1]);
				if(i%64==0 || i==Base64bs.length)
					os.write(lineSeparator.getBytes("utf-8"));
			}
			if(textHaveStartAndEnd)
				os.write(("-----END "+privateName+" KEY-----"+lineSeparator).getBytes("utf-8"));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
