package com.ksptooi.ASMC.Util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Var {
	
	public static final String ASMC_Version = "V3.29-B";
	
	
	
	 public static void setClipboardString(String text) { 
		 // ��ȡϵͳ������ 
		 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		 // ��װ�ı����� 
		 Transferable trans = new StringSelection(text); 
		 // ���ı��������õ�ϵͳ������ 
		 clipboard.setContents(trans, null); 
		 
	 }

}