package com.ksptooi.ASMC.Util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Var {
	
<<<<<<< HEAD
	public static final String ASMC_Version = "V3.29-G";
=======
	public static final String ASMC_Version = "V3.29-D";
>>>>>>> parent of 9c1ccf3... Version 3.29-E "事件" 升级
	
	
	
	 public static void setClipboardString(String text) { 
		 // ��ȡϵͳ������ 
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		 // ��װ�ı����� 
		 Transferable trans = new StringSelection(text); 
		 // ���ı��������õ�ϵͳ������ 
		 clipboard.setContents(trans, null); 
		 
	 }
	 

}