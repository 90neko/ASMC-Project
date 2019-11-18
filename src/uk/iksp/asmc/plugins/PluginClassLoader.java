package uk.iksp.asmc.plugins;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

import sun.misc.ClassLoaderUtil;

public class PluginClassLoader {

	private MessageManager msg = ASMC.getMessageManager();
	
	private URLClassLoader classLoader;
	

    public PluginClassLoader(File file){  
    	
    	msg.sendSysMessage("初始化内部组件 - ASMC类加载器");
    	
    	URL url;
		try {
			
			
			url = file.toURI().toURL();
			classLoader=new URLClassLoader(new URL[]{url});
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }  
     
   
   
    
    public Object loadClass(String className){  
  
    	
		try {
			
			
			Class<?> loadClass = classLoader.loadClass(className);
    	
			Object obj;
    	
			
			obj = loadClass.newInstance();

			return obj;  
        
        
        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;  
    }  
    
    
    
    public void closeClassLoader() {
		//关闭ClassLoader
		ClassLoaderUtil.releaseLoader((URLClassLoader)classLoader);
    }
	
	
	
	
	
	
}
