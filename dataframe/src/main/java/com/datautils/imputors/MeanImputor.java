package com.datautils.imputors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import com.analysis.providers.datamanipulator.IImputor;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.DataPointType;
import com.dataframe.IDataFrame;
import com.datautils.DataFrameTypeException;

public class MeanImputor implements IImputor{		

	/**
	 * This method calculates the means of each numerical column where a missing value exists and then
	 * returns a Map of the form <Column, Imputed value>.
	 * 
	 * @param ArrayList<Integer> missing
	 * @param DataFrame df
	 * @return 
	 */


	
	public ArrayList<DataPoint> getImputedValues(int column, IDataFrame df) {
		ArrayList<DataPoint> newpoints = new ArrayList<DataPoint>();

	
			try {
				ArrayList<DataPoint> points=df.getColumn(column);
				
				double mean= df.getMean(column);
				
				for(DataPoint point:points)
				{
					if(point.getType()==DataPointType.NA)
					{
						newpoints.add(new DataPoint(mean));
					}
					else
					{
						newpoints.add(new DataPoint(point.toString()));
					}
				
				}
			} catch (DataFrameIndexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataFrameTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return newpoints;
			
			
	
	}

	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
