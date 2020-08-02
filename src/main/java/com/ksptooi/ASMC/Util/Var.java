package com.ksptooi.ASMC.Util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Var {
	
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
	 

}