package uk.iksp.asmc.plugin.loader;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import com.ksptooi.asmc.common.Project;
import com.ksptooi.asmc.entity.plugins.ExternalPluginFile;
import com.ksptooi.asmc.filter.plugin.jarFilter;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.generaldatacore.dataInteface.InputStreamConnection;
import com.ksptooi.generaldatacore.entity.data.DataSet;


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
	public ArrayList<ExternalPluginFile> searchPlugins(File folder){
		
		log.info("·ASMC插件搜索 - 正在获取插件");

		
		ArrayList<ExternalPluginFile> asmcPluginList = new ArrayList<ExternalPluginFile>();
		
		try {
		
			//创建插件文件夹
			folder.mkdirs();
		
			//取所有插件
			File[] pluginList = Project.pluginFolder.listFiles(new jarFilter());
		
		
			//遍历获取插件信息
			for(File f:pluginList){
			
				ExternalPluginFile asmcPlugin = new ExternalPluginFile();
				
				
				log.info("获取:"+f.getName());
			
			
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd");
				
				//获取插件文件流
				InputStream is=url.openStream();


				DataSet dataSet = new InputStreamConnection(is,"UTF-8").getDataSet().setAutomatic(false);

				//将文件中的信息加入插件实体
				asmcPlugin.setMainClass(dataSet.val("Plugin_Main"));
				asmcPlugin.setName(dataSet.val("Plugin_Name"));
				asmcPlugin.setFile(f);
				
				
				asmcPluginList.add(asmcPlugin);
			
			}
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		return asmcPluginList;
		
	}
	
	
}
