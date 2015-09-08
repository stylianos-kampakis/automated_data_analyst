package com.dataframe;

import com.datautils.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.print.DocFlavor.URL;

import com.dataframe.DataFrame.CheckIfMissing;
import com.dataframe.DataFrame.CheckMissingCount;
import com.datautils.DataUtils;

/**
 * Hello world!
 *
 */
public class App 
{
		
    public static void main( String[] args ) throws IOException
    {
    	App c = new App();
    	Class cls = c.getClass();
    	
    	// finds resource relative to the class location
    	java.net.URL url = cls.getResource("/iris_missing.csv");
    
    	    
    	DataFrame df=new DataFrame();

    	try {
			df.readCSV(url.getPath());
			System.out.println(df.checkContainsMissingValues());
			System.out.println(df.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
  
    	
    }

}
