package com.rinterface;
import java.io.IOException;
import java.io.OutputStream;

import com.analysis.results.Coefficients;
import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.results.ResultStatementPlot;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String path="C:\\iris_numeric_short.csv";
    		DataFrame df=new DataFrame();
RLinearRegressionProvider rinter=new RLinearRegressionProvider("C:/Program Files/R/R-3.1.2/bin/x64/");
OutputStream rInput;
RPlotProvider plotter;


plotter=new RPlotProvider("C:/Program Files/R/R-3.1.2/bin/x64/");

    	    	try {
    				df.readCSV(path);
    				
    		    	ResultStatementPlot plt=plotter.makeHistogram(df, new Response("Sepal.Length"));

    				
    		
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    }
    }

