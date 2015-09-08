package com.rinterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.analysis.providers.plotting.IPlotter;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.results.ResultStatementPlot;
import com.factengine.results.ResultTags;

public class RPlotProvider extends RProviderBase implements IPlotter {
	//Plotting in rcaller works by storing the plot as a temporary image file on the hard drive.
	File file;
	
	public RPlotProvider(){
		super();
	}
	
	public RPlotProvider(String path){
		super(path);
	}
	
	public ResultStatementPlot makeHistogram(DataFrame df, Response res) {
		initialize();
		
		BufferedImage img=null;
		
		String template="with(DataFrame,hist("+res.getResponse()+"))";
		
		try {
			String dfR=df.getRDataFrame();
			code.addRCode(dfR);
			
			file=code.startPlot();
			code.addRCode(template);
			code.endPlot();
			
			caller.setRCode(code);
			caller.runOnly();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultStatementPlot result=new ResultStatementPlot("Histogram of "+res.getResponse(), ResultTags.HISTOGRAM, img);
				
		return result;
	}
	
	/**
	 * Shows the last plot. This should be used for testing and debugging purposes.
	 */
	public void showPlot(){
		code.showPlot(file);
	}

}
