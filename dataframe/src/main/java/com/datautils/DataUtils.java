package com.datautils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.analysis.providers.datamanipulator.IImputor;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.DataPointType;
import com.dataframe.IDataFrame;
import com.factengine.results.ResultStatement;
import com.factengine.results.ResultTags;

public class DataUtils {
	
	


	
	//helper class that gets the rows of a column where missing values exist
	private static Set<Integer> getMissingRowIndices(ArrayList<DataPoint> points)
	{
		Set<Integer> missing=new HashSet<Integer>();
		DataPoint point;
		//we need to start at 1, because the indexing of columns starts at 1
		for(int i=1;i<=points.size();i++){
			point=points.get(i-1);	
			if(point.getType()==DataPointType.NA)
			{
				missing.add(i);
			}
		}
		return missing;
	}
	
/**
 * 
 * Method that calculates the means of the specified columns. This is the master method
 * that the overloaded methods call.
 * 
 * @param columns
 * @param df
 * @return
 * @throws DataFrameTypeException 
 */


public static TransformSettings normalize(IDataFrame df){
	return null;
	
}

public static TransformSettings standardize(IDataFrame df){
	return null;
	
}

/**
 * Undoes a transformation applied to a dataframe.
 * @param df
 * @param settings
 */
public static void detransform(IDataFrame df,TransformSettings settings){
	
}
	
/**
 * 
 * This method tries to convert a response to normal by trying various heuristics. It changes
 * the dataframe inplace, so it does not return a new dataframe, but rather it changes the dataframe
 * it has been provided with.
 * 
 * It is not clear whether a kiesession should be used here or simply try a few simple heuristics.
 * 
 * @param df
 * @param column
 * @return 
 */
public static ResultStatement makeNormal(IDataFrame df,String column){
	
	return new ResultStatement("Response was converted to normal",ResultTags.TRANSFORMATION);
	
	//in case the transformation was not successful simply return unsuccessful transformation back to normal.
	
}

public static boolean checkNormal(IDataFrame df,String column){
	
	return true;
	
}
	
	//this is a 'delegate' for imputing missing values using the mean of a column
	
}
