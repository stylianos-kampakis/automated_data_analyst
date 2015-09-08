package com.factengine.analysisdescriptors;

import com.analysis.results.PredictionResultSet;
import com.dataframe.IDataFrame;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.IFactPerformance;

public interface IFactAnalysisModel {
	
	FactModel model=null;
	IFactPerformance performance=null;
	
	public IFactPerformance getPerformance();
	public PredictionResultSet fit(IDataFrame df);
	public PredictionResultSet predict(IDataFrame df);

}
