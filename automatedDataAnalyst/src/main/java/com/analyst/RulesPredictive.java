package com.analyst;

import com.analysis.providers.supervised.IModel;
import com.dataframe.IDataFrame;
import com.factengine.Response;
import com.factengine.results.Results;

public class RulesPredictive extends RulesExecutorModel {

	public RulesPredictive(IDataFrame df, String packageName, String sessionName, IModel analysisExecutor) {
		super(df, packageName, sessionName, analysisExecutor);
	}
	
	
	public Results suggestAlgorithms(Response response){
		
		
		kSession.insert(response);		
		kSession.fireAllRules();
		
		return results;
		
	}

}
