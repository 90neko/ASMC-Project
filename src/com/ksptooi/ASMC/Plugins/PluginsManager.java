package com.ksptooi.ASMC.Plugins;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class PluginsManager {

	
	HashMap<String,Command_cmd> installPlugin=new HashMap<String,Command_cmd>();
	
	
	public void LoadingPlugins(){
		
		IOController_V5 v5=ASMC.getV5();
		
		MessageManager msg = ASMC.getMessageManager();
			
		ArrayList<File> PluginList=new ArrayList<File>();
		
		HashMap<String,String> PluginMainList=new HashMap<String,String>();
		
		msg.sendSysMessage("����ASMC���");
		
		//��������ļ���
		ASMC.getMainPluginsfolder().mkdirs();
		
		
		//ȡ���в��
		File[] PrePluginList = ASMC.getMainPluginsfolder().listFiles();
		
		
		
		//ȥ�����ǲ�����ļ� || �����ļ������ PluginList��
		for(int i=0;i<PrePluginList.length;i++){
			
			if( ! PrePluginList[i].getName().contains(".jar")){
				continue;
			}	
			
			PluginList.add(PrePluginList[i]);
			
		}

		try{		
	
	
		
			//��ȡ
			for(File f:PluginList){
				
				msg.sendSysMessage("��ȡ:"+f.getName());
						
				
				
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd"); 
			
				
				InputStream is=url.openStream(); 
							
				
				String main=v5.getKeyValueOfInputStream(is, "Plugin_Main");
				
				
				PluginMainList.put(f.getName(), main);
				
				
				System.out.println(f.getName()+":"+main); 
				 
			
			
			}
			
		
		

			
					
			
			//����
			for(File f:PluginList){
				msg.sendSysMessage("����:"+f.getName());
				
				
				String main = PluginMainList.get(f.getName());
				
				
				URL url=f.toURI().toURL();
				
				ClassLoader loader=new URLClassLoader(new URL[]{url});//������������
				
				Class<?> cls=loader.loadClass(main);
				
				
				Method m1 = cls.getDeclaredMethod("getThis");
				
				Object obj = cls.newInstance();
				
				Command_cmd CT=(Command_cmd)m1.invoke(obj);
				
				msg.sendSysMessage("ע������:"+ CT.getClass());
				
				
				CT.ExecuteOfType(new CommandEntity());
		
				
			}
			
			
			
			
		}catch(Exception e){		
			e.printStackTrace();			
		}
		

		
		
		
	}
	
	
}
