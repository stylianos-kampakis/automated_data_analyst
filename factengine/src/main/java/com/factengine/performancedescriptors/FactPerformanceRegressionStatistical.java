package com.factengine.performancedescriptors;

import com.analysis.parameters.ParameterSet;
import com.analysis.results.PredictionResultSet;
import com.factengine.factmodels.FactModel;

public class FactPerformanceRegressionStatistical extends
		FactPerformanceRegression {

	double AIC;
	double BIC;
	double likelihood;
	
	public FactPerformanceRegressionStatistical(FactModel model,
			PredictionResultSet results, ParameterSet parameters) {
		super(model, results, parameters);
		
	}

}
