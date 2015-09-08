package com.analysis.providers.supervised;

import java.util.HashMap;

import com.dataframe.DataFrame;
import com.factengine.Response;

public interface ILinearRegression extends IRegression, IStatisticalMethod{
		
	public Double rSquared();	
	
	public double adjustedRSquared();
	
	
	
	//The assumption functions follow the structure by the paper Global Validation of Linear Model Assumptions
	// by Edsel A. Peña* and Elizabeth H. Slate† (2006)


	
}
