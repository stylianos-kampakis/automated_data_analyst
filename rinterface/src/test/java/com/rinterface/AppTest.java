package com.rinterface;

import com.dataframe.DataFrame;
import com.dataframe.IDataFrame;
import com.factengine.Response;
import com.factengine.results.ResultStatementPlot;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
 
{
	DataFrame df;
	DataFrame df_missing;
	IDataFrame df_numeric;
	RLinearRegressionProvider rinter;
	RPlotProvider plotter;
	
	  @Before
	    public void loadCSV(){
		  
	    	String rpath="C:/Program Files/R/R-3.1.2/bin/x64/";


	    	df=new DataFrame();
	    	String path="C:\\iris_numeric.csv";
	    	try {
				df.readCSV(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	df_missing=new DataFrame();
	    	path="C:\\iris_missing.csv";
	    	try {
				df_missing.readCSV(path);
				df_missing.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	rinter=new RLinearRegressionProvider(rpath);   
	    	plotter=new RPlotProvider(rpath);
	    }
    
	
	  
	@Test
    public void testLinearRegressionAllCovariates()
    {
		rinter.fit(new Response("Sepal.Length"), df);
		HashMap<String,Double> coefs=rinter.getCoefficients().getCoefficients();
		System.out.println(coefs.toString());
	
	//we are using only a single coefficient int the assertion because the result string is
	//too long.
        Assert.assertTrue(coefs.get("Petal.Length")==0.471920039327133);
    }
    
    @Test
    public void testChooseCovariatesLinearRegression(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length"});
    	HashMap<String,Double> coefs=rinter.getCoefficients().getCoefficients();
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.
    Assert.assertTrue(coefs.get("Petal.Length")==0.408922277351185);
    

    
    }
    
    @Test
    public void testPrediction(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length","Sepal.Width"});
    	//System.out.println(result);
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.
 
    
    double[] results=rinter.predict(df);
	Assert.assertTrue(results[2]==4.76831540748799);
    
    }
    
    @Test
    public void testFitted(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length","Sepal.Width"});
    	//System.out.println(result);
    	//we are using only a single coefficient int the assertion because the result string is
    	//too long.

    
    double[] results=rinter.getFitted();
	Assert.assertTrue(results[2]==4.76831540748799);
    
    }
    
    @Test
    public void testAIC(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getAIC();
	Assert.assertTrue(result==101.025499819592);
    }
    
    @Test
    public void testBIC(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getBIC();
	Assert.assertTrue(result==113.068040995977);
    }
   
    @Test
    public void testLogLik(){
    	rinter.fit(new Response("Sepal.Length"), df,new String[]{"Petal.Length","Sepal.Width"});
    
    double result=rinter.getLogLikelihood();
	Assert.assertTrue(result==-46.51);
    }
    
    @Test
    public void testHistogram(){
    	ResultStatementPlot plt=plotter.makeHistogram(df, new Response("Sepal.Length"));
    	plotter.showPlot();
    }
}
