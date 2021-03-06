package com.analyst;

import java.util.ArrayList;

import org.drools.core.WorkingMemoryEntryPoint;
import org.drools.core.marshalling.impl.ProtobufMessages.FactHandle;
import org.kie.api.runtime.rule.EntryPoint;

import com.analysis.AnalysisExecutor;
import com.analysis.providers.supervised.ILinearRegression;
import com.dataframe.IDataFrame;
import com.factengine.Response;
import com.factengine.performancedescriptors.FactPerformanceRegressionStatistical;
import com.factengine.results.ResultStatement;
import com.factengine.results.Results;

public class RulesLinearRegression extends RulesExecutorModel{
	
	private org.kie.api.runtime.rule.FactHandle handleResponse;
	
	public RulesLinearRegression(IDataFrame df,ILinearRegression analysisExecutor) {
		//the constructor initializes for the drools session "statistical" corresponding to the statistical.drl
		super(df,"linear_regression","linear_regression",analysisExecutor);
		kSession.insert(analysisExecutor);

	}
	
	public Results analyzeData(Response response){
		//We have to remove any old responses and add the current response variable.
		//The documentation was not clear on how to replace the deprecated 'retract' method.
		
		//kSession.retract(handleResponse);
		//kSession.retract(handleResults);
		
		handleResponse=kSession.insert(response);	
		//kSession.insert(new Results());
		kSession.fireAllRules();

		hasExecuted=true;
		
		return results;
	}
	
	public ILinearRegression returnModel(){
		return (ILinearRegression)this.analysisExecutor;		
	}
	
	public Results getResults(){
		return this.results;
	}
	
	//public returnRegressionFitted
	

}