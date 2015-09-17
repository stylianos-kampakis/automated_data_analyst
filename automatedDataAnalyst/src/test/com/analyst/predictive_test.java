package com.analyst;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.dataframe.DataFrame;

public class predictive_test {

	DataFrame df_iris=new DataFrame();
	
	@Before
	public void prepareTest(){
		
		
		Class cls = this.getClass();
   	 
    	// finds resource relative to the class location
    	java.net.URL url = cls.getResource("/iris.csv");
    	
    	df_iris=new DataFrame();

    	try {
			df_iris.readCSV(url.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	


	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
