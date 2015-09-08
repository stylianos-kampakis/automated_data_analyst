package com.dataframe;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;







import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void guessTypeTest()
    {		
    	System.out.println(df.GetTypes());
    	Assert.assertTrue(df.GetTypes().contains("\"unknown_name\" - type: STRING - subtype: FREE_TEXT"));
    	Assert.assertTrue(df.GetTypes().contains(",\"Petal.Width\" - type: DOUBLE - subtype: POSITIVE_REAL"));

    }
    
    @Test
    public void guessMissingValuesTypeTest()
    {		
    	System.out.println(df_missing.GetTypes());
    	Assert.assertTrue(df_missing.GetTypes().equals("Sepal.Length - type: DOUBLE - subtype: POSITIVE_REAL,Sepal.Width - type: DOUBLE - subtype: POSITIVE_REAL,Petal.Length - type: DOUBLE - subtype: POSITIVE_REAL,Petal.Width - type: DOUBLE - subtype: POSITIVE_REAL,Species - type: STRING - subtype: FACTOR"));
    }
    
  //test that the largest column index is 6
    @Test
    public void columnNumber()
    {	
		
    	for (Entry<IndexKey, DataPoint> entry : df.df.entrySet()) {
  		  IndexKey key = entry.getKey();
  		  Assert.assertTrue(key.getColumn()<7);
  	
  		}
    
    }
    
    @Test
    public void testGetColumn()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getColumn(1);
			Assert.assertTrue(column1.size()==150);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testGetRow()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getRow(1);
			Assert.assertTrue(column1.size()==6);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testGetRowNames()
    {		
    	try {
			ArrayList<DataPoint> column1=df.getRow(1);
			Assert.assertTrue(true);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//System.out.println(column1.toString());
    
    }
    
    @Test
    public void testFirstRowNames()
    {		
    	ArrayList<DataPoint> column1;
		try {
			column1 = df.getRow(1);
			System.out.println(column1.toString());
			Assert.assertTrue(column1.toString().equals("[\"1\", 5.1, 3.5, 1.4, 0.2, \"setosa\"]"));
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    
    @Test
    public void testGetColumnByName(){
    	try {
			ArrayList<DataPoint> paok=df_missing.getColumn("Sepal.Length");
			Assert.assertTrue(paok.get(0).toString().equals("?"));
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @Test
    public void testDropColumns(){
    	int[] columns={1,5};
    	df.dropColumns(columns);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	
    	System.out.println(strings[503]);
    	Assert.assertTrue(strings[4].equals("2,1"));
    	Assert.assertTrue(strings[503].equals("126,4"));
    }
    
    @Test
    public void testDropMiddleColumns(){
    	int[] columns={2,3};
    	df.dropColumns(columns);

    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	
    	System.out.println(strings[503]);
    	Assert.assertTrue(strings[4].equals("2,1"));
    	Assert.assertTrue(strings[503].equals("126,4"));
    }
    
    @Test
    public void testDropRows(){
    	int[] rows={1,2,3,150};
    	df.dropRows(rows);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	Assert.assertTrue(strings[875].equals("146,6"));
    }
    
    @Test
    public void testDropMiddleRows(){
    	int[] rows={2,3,4,150};
    	df.dropRows(rows);
    	
    	String[] strings=df.getIndexKeyListString().split("\n");

    	Assert.assertTrue(strings[875].equals("146,6"));
    }
    
    @Test
    public void computeNumberofMissingValuesForRow(){
    	Assert.assertTrue(df_missing.getNumMissingValuesForRow(1)==2);
    }
   
    @Test
    public void computeNumberofMissingValuesForColumn(){
    	try {
			Assert.assertTrue(df_missing.getNumMissingValuesForColumn(1)==2);
		} catch (DataFrameIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void computeNumberofMissingValues(){
		Assert.assertTrue(df_missing.getNumMissingValues()==8);
    }
    
    @Test
    public void containsMissingValues(){
    	
			try {
				Assert.assertTrue(df_missing.checkContainsMissingValues());
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Assert.assertFalse(df.checkContainsMissingValues());
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
    
    @Test
    public void testColumnsType(){
			Assert.assertTrue(df.checkContainsColumnsType(DataPointType.STRING));
			Assert.assertTrue(df.checkContainsColumnsType(DataPointType.DOUBLE));		
			Assert.assertFalse(df.checkContainsColumnsType(DataPointType.NA));
			
    }
    
    
    @Test
    public void testNumColumnsType(){
    	Assert.assertTrue(df.numColumnsType(DataPointType.STRING)==2);	
    }

    
    //test that the largest column index is 6
    @Test
    public void testDeepCopy() throws DataFrameIndexException
    {	
    	IDataFrame newdf=df.clone();
    	
    	newdf.dropRows(new int[] {1,2,3});
    	Assert.assertFalse(newdf.getRow(1).toString().equals(df.getRow(1)));
    	    	
    }
    
    @Test
    public void testApplyLogicalToColumn(){
    	
    }
    
    @Test
    public void testApplyLogicalToRow(){
    	
    }
    
    @Test
    public void testSetColumn() throws DataFrameIndexException{
    	ArrayList<DataPoint> points=new ArrayList<DataPoint>();
    	for(int i=0;i<df.getNumberRows();i++){  		
    		points.add(new DataPoint(2.0));
    	}
    	
    	df.setColumn(2, points);
    	Assert.assertTrue(df.getRow(1).get(1).equals(new DataPoint(2.0)));
    	Assert.assertTrue(df.getRow(50).get(1).equals(new DataPoint(2.0)));

    	
    }
    
    
}
