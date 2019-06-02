package com.ksptooi.ASMC.Util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Var {
	
	public static final String ASMC_Version = "V3.31-E";
	
	
	 public static void setClipboardString(String text) { 
		 // 获取系统剪贴板 
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		 // 封装文本内容 
		 Transferable trans = new StringSelection(text); 
		 // 把文本内容设置到系统剪贴板 
		 clipboard.setContents(trans, null);  
	 }
	 

}