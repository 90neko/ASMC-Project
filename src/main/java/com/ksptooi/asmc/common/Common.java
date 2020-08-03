package com.ksptooi.asmc.common;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.ksptooi.asmc.main.Asmc;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Common {
	
	 private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
	
	 public static void setClipboardString(String text) { 
		 // 获取系统剪贴板 
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		 // 封装文本内容 
		 Transferable trans = new StringSelection(text); 
		 // 把文本内容设置到系统剪贴板 
		 clipboard.setContents(trans, null);  
	 }
	 
	    public static String getUTCTimeStr() {
	    	
	        StringBuffer UTCTimeBuffer = new StringBuffer();
	        // 1、取得本地时间：
	        Calendar cal = Calendar.getInstance() ;
	        // 2、取得时间偏移量：
	        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
	        // 3、取得夏令时差：
	        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
	        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH)+1;
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE); 
	        UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
	        UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;
	        try{
	            format.parse(UTCTimeBuffer.toString()) ;
	            return UTCTimeBuffer.toString() ;
	        }catch(ParseException e)
	        {
	            e.printStackTrace() ;
	        }
	        return null ;
	    }
	    
	    
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