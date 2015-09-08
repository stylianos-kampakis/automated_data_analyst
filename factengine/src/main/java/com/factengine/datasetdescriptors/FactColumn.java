package com.factengine.datasetdescriptors;

import com.factengine.Fact;

public class FactColumn extends Fact {
	
	int column;
	
	public FactColumn(int column){
		this.column=column;
	}
	
	public int getColumn(){
		return column;
	}


}
