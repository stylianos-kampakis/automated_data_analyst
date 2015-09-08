package com.factengine.analysisdescriptors;

import java.util.Arrays;

import com.analysis.AnalysisExecutor;
import com.analysis.parameters.ParameterSet;
import com.analysis.results.PredictionResultSet;
import com.dataframe.IDataFrame;
import com.factengine.descriptionenums.ModelProperties;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.FactPerformanceRegression;
import com.factengine.performancedescriptors.FactPerformanceRegressionStatistical;
import com.factengine.performancedescriptors.IFactPerformance;

public class FactAnalysisRegression implements IFactAnalysisModel {

	FactPerformanceRegression performance;
	FactModel model;
	AnalysisExecutor executor;
	
	public FactAnalysisRegression(FactModel model, AnalysisExecutor analysisExecutor){
		this.model=model;
		this.executor=executor;
		this.performance=performance;
	}
	

	public void setDataFrame(IDataFrame df){
		executor.setDataFrame(df);
	}
	
	public IFactPerformance getPerformance() {
		return performance;
	}

	public FactPerformanceRegression crossVal(IDataFrame df,ParameterSet parameters){
		setDataFrame(df);
		PredictionResultSet res = executor.crossVal(10, 10, model.getModelName());
		FactPerformanceRegression fact=new FactPerformanceRegression(model,res,parameters);
		return fact;
	}

	public FactPerformanceRegressionStatistical fit(IDataFrame df,ParameterSet parameters) {
		
		setDataFrame(df);
		PredictionResultSet res = executor.fit(model.getModelName(), parameters);
		FactPerformanceRegressionStatistical fact=new FactPerformanceRegressionStatistical(model,res,parameters);
		return fact;
	}

	
	public boolean returnsCoefs(){
		ModelProperties[] properties=model.getModelProperties();
		return Arrays.asList(properties).contains(ModelProperties.RETURNS_COEFFICIENTS);
	}


	public PredictionResultSet fit(IDataFrame df) {
		// TODO Auto-generated method stub
		return null;
	}


	public PredictionResultSet predict(IDataFrame df) {
		// TODO Auto-generated method stub
		return null;
	}

}
