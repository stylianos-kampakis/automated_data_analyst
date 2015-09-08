package com.describer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.factengine.results.ResultStatement;
import com.factengine.results.ResultStatementPlot;
import com.factengine.results.Results;

public class SimpleDescriber extends Describer {

	String description="";
	
	//folder path is the path to the folder that will contain the presentation
	public SimpleDescriber(String folderPath){
		super(folderPath);
	}
	
	public String makeDescriptionHTML(Results results){
		String html="";
	
		
		for(Object res : results.getResults()){
		if(res.getClass().equals(ResultStatement.class)){
			ResultStatement rescast=(ResultStatement)res;
			html=html+"<div><p>"+rescast.getDescription()+"</p></div>";	
		}
		else if(res.getClass().equals(ResultStatementPlot.class)){
			ResultStatementPlot rescast=(ResultStatementPlot)res;
			//String pathToPic=rescast.storePicture;
			//html=html+"<div><p><img src=\"+"pathToPic+"\" alt=\""+rescast.getDescription()+"\"></p><p>"+rescast.getDescription()+"</p></div>";	
			
		}
		}
		
		this.description=html;
		
		return html;		
	}
	
	public void storeResults(){
		try {
			PrintWriter out = new PrintWriter(this.folderPath+"\\presentation2.html");
			out.write(this.description);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
