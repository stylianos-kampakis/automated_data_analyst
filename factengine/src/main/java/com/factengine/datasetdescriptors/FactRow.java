package com.factengine.datasetdescriptors;

import com.factengine.Fact;

/**
 * A FactRow is a class that contains all important information for a row. This is used to reason 
 * upon later by the kieSession.
 * 
 * @author stelios
 *
 */
public class FactRow extends Fact {

	int row;
	
	public FactRow(int row, int numColumns){
		this.row=row;
	}
	
	public int getRow(){
		return row;
	}
	
	
	
}
