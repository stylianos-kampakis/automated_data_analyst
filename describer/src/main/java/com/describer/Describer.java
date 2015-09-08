package com.describer;

import java.io.File;

public class Describer {
	
	String folderPath;
	
	public Describer(String folderPath) {
		this.folderPath=folderPath;
		
		new File(folderPath).mkdirs();
		/*File parent = targetFile.getParentFile();
		
		if(!parent.exists() && !parent.mkdirs()){
		    throw new IllegalStateException("Couldn't create dir: " + parent);
		}*/
		
		//NOW CREATE THE FOLDER OR CLEAN IT UP
	}
	
	
	

}
