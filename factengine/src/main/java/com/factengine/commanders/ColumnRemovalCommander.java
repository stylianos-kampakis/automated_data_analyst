package com.factengine.commanders;

import java.util.HashSet;
import java.util.Set;

import com.dataframe.IDataFrame;

public class ColumnRemovalCommander extends ExecutionCommander {
	private Set<Integer> columns=new HashSet<Integer>();

	//we have to use a set so as to avoid duplicates
	public Set<Integer> getColumns(){
		return columns;
	}


	public void addColumn(int i){		
		this.columns.add(i);
	}
	
	public void removeColumns(IDataFrame df){
		df.dropColumns(getColumns());
	}
}
