package com.ksptooi.asmc.util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.ksptooi.asmc.main.Asmc;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AdvHash {



	
	public static String md5(String str){
		

        byte[] secretBytes = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(str.getBytes());
           
            secretBytes = md.digest();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("算法异常! - MD5");
        }
        
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字

        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        
        return md5code;
         
    }
	
	
	   public static String AESEncode(String encodeRules,String content){
	        try {
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	            SecretKey original_key=keygen.generateKey();
	            byte [] raw=original_key.getEncoded();
	            SecretKey key=new SecretKeySpec(raw, "AES");
	            Cipher cipher=Cipher.getInstance("AES");
	            cipher.init(Cipher.ENCRYPT_MODE, key);
	            byte [] byte_encode=content.getBytes("utf-8");
	            byte [] byte_AES=cipher.doFinal(byte_encode);
	            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
	            return AES_encode;
	        }catch(Exception e){
	        	Asmc.getLogger().error("编码时出现错误,可能的原因是:数据损坏 Rule错误");
	        }
	            
	        return "Encode_Failed";         
	        
	    }

	   
	    public static String AESDncode(String encodeRules,String content){
	        try {
	            KeyGenerator keygen=KeyGenerator.getInstance("AES");
	            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
	            SecretKey original_key=keygen.generateKey();
	            byte [] raw=original_key.getEncoded();
	            SecretKey key=new SecretKeySpec(raw, "AES");
	            Cipher cipher=Cipher.getInstance("AES");
	            cipher.init(Cipher.DECRYPT_MODE, key);
	            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
	            byte [] byte_decode=cipher.doFinal(byte_content);
	            String AES_decode=new String(byte_decode,"utf-8");
	            return AES_decode;
	        }catch(Exception e){
	        	Asmc.getLogger().error("编码时出现错误,可能的原因是:数据损坏 Rule错误");
	        }

	        return "Encode_Failed";         
	    }
	
	
	
	
}
