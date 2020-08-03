package uk.iksp.asmc.plugin.loader;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import com.ksptooi.asmc.common.Project;
import com.ksptooi.asmc.entity.plugins.AsmcPlugin;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

import sun.misc.JarFilter;
import uk.iksp.v7.Factory.StreamFactory;
import uk.iksp.v7.Session.InputStreamReaderSession;

public class PluginSearch{

	
	private Logger log = Asmc.getLogger();
	
	/**
	 * 插件搜索器
	 */
	
	public PluginSearch(){
		
		log.info("初始化内部组件 - ASMC插件搜索 ");
		
	}
	
	
	/**
	 * 搜索插件目录下的插件
	 */
	public ArrayList<AsmcPlugin> searchPlugins(File folder){
		
		log.info("·ASMC插件搜索 - 正在获取插件");
		
		StreamFactory streamFactory = Asmc.getGeneralDataFactoryBuilder().buildStreamFactory();
		
		ArrayList<AsmcPlugin> asmcPluginList = new ArrayList<AsmcPlugin>();
		
		try {
		
			//创建插件文件夹
			folder.mkdirs();
		
			//取所有插件
			File[] pluginList = Project.pluginFolder.listFiles(new JarFilter());
		
		
			//遍历获取插件信息
			for(File f:pluginList){
			
				AsmcPlugin asmcPlugin = new AsmcPlugin();
				
				
				log.info("获取:"+f.getName());
			
			
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd");
				
				//获取插件文件流
				InputStream is=url.openStream(); 
				
				
				try(InputStreamReaderSession isrs = streamFactory.openInputStreamReaderSession(is)){
					
					
					asmcPlugin.setMainClass(isrs.get("Plugin_Main"));
					asmcPlugin.setName(isrs.get("Plugin_Name"));
					asmcPlugin.setFile(f);
					
				}
				
				
				asmcPluginList.add(asmcPlugin);
			
			}
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return asmcPluginList;
		
	}
	
	
}
