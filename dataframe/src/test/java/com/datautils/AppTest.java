package com.datautils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;





import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.datautils.imputors.MeanImputor;



/**
 * Unit test for simple App.
 */
public class AppTest  

{
	DataFrame df;
	DataFrame df_missing;
	
    @Before
    public void loadCSV(){
    	
    	Class cls = this.getClass();
    	 
    	// finds resource relative to the class location
    	java.net.URL url = cls.getResource("/iris.csv");
    	
    	df=new DataFrame();

    	try {
			df.readCSV(url.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	df_missing=new DataFrame();
    	url = cls.getResource("/iris_missing.csv");
    	try {
			df_missing.readCSV(url.getPath());
			df_missing.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
  
    @Test
    public void imputeMean()
    {	
    	
try {
	MeanImputor imputor =new MeanImputor();
	ArrayList<DataPoint> values=imputor.getImputedValues(1, df_missing);
	System.out.println(values.toString());
	df_missing.setColumn(1, values);
	System.out.println(df_missing.getColumn(1).get(0));
	Assert.assertTrue(df_missing.getColumn(1).get(0).toString().contains("5.854054"));
	Assert.assertTrue(df_missing.getColumn(1).get(4).toString().contains("5.854054"));

} catch (DataFrameIndexException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    
    }
    
    
//    @Test
//    public void imputeMultipleRows()
//    {	
//try {
//	Set<Integer> rows=new HashSet<Integer>();
//	rows.add(1);
//	rows.add(2);
//	HashMap<Integer, ArrayList<DataPoint>> points=DataUtils.impute(df_missing, rows , new DataUtils.meanImputor());
//	//System.out.println(points.toString());
//	Assert.assertTrue(points.toString().equals("{1=[5.854054054054055, 3.5, 3.7891891891891913, 0.2, 'setosa'], 2=[4.9, 3.0, 1.4, 1.2135135135135142, 'setosa']}"));
//} catch (DataFrameIndexException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//
//    }
//
//    @Test
//    public void testImputation(){
//    	
//    	try {
//        	df_missing.setRows(DataUtils.impute(df_missing, new int[]{2,3,5,7,8,14}, new DataUtils.meanImputor()));
//			df_missing.getRows(new int[]{2,3,5,7,8,14});
//		} catch (DataFrameIndexException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    Assert.assertTrue(true);
//    }
//    
//    @Test
//    public void testMeans(){
//    	try {
//			Assert.assertTrue(DataUtils.getMeans(2, df)>5.8 && DataUtils.getMeans(2, df)<6);
//		} catch (DataFrameTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//    }
//    
//    @Test
//    public void testVariances(){
//    	try {
//			Assert.assertTrue(DataUtils.getVariance(2, df)>0.68 && DataUtils.getVariance(2, df)<0.7);
//		} catch (DataFrameTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }


}
