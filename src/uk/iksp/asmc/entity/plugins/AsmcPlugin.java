package uk.iksp.asmc.entity.plugins;

import java.io.File;

public class AsmcPlugin {

	
	private String name = null;
	
	private File file = null;
	
	private String mainClass = null;

	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File filePath) {
		this.file = filePath;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
}
