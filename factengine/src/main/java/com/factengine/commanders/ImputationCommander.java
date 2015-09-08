package com.factengine.commanders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.analysis.providers.datamanipulator.IImputor;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.IDataFrame;
import com.datautils.DataUtils;

/**
 * Imputation commander is used by the kieSession to store the rows for which imputation should be performed.
 * 
 * @author stelios
 *
 */
public class ImputationCommander extends ExecutionCommander {
private Set<Integer> columns=new HashSet<Integer>();
IImputor imputor;

public ImputationCommander(IImputor imputor){
	this.imputor=imputor;
}

public Set<Integer> getColumns(){
	return this.columns;
}


public void addRow(int i){
	this.columns.add(i);
}

public void impute(IDataFrame df){
	
		for(Integer col:columns)
		{
			df.setColumn(col,imputor.getImputedValues(col, df));
		}
		
}

public Integer getNumColumns(){
	return columns.size();
}
	
}
