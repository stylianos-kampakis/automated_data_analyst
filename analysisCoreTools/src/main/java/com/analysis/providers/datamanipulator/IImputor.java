package com.analysis.providers.datamanipulator;

import java.util.ArrayList;

import com.analysis.providers.IProvider;
import com.dataframe.DataPoint;
import com.dataframe.IDataFrame;

//the imputor interface is used to construct 'delegates' that impute. These delegates are used by MathUtils.
public interface IImputor extends IProvider {

	//this method is used when dealing with lots of missing values
	public ArrayList<DataPoint> getImputedValues(int column, IDataFrame df);
	
}
