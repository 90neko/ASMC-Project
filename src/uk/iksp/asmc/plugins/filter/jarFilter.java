package uk.iksp.asmc.plugins.filter;

import java.io.File;
import java.io.FilenameFilter;

public class jarFilter implements FilenameFilter{

	@Override
	public boolean accept(File arg0, String arg1) {
		return arg1.endsWith("jar");
	}
	
}
