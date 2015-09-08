package com.analysis.providers.statisticaltests;

import com.dataframe.DataFrame;
import com.factengine.Response;

public interface ILinearRegressionGlobalAssumptionsCheckProvider{

	public boolean checkGlobalAssumptions(DataFrame df,Response res,double significance);
	
	public boolean checkLinearityAssumption(DataFrame df,Response res,double significance);

	public boolean checkHomoskedasticityAssumption(DataFrame df,Response res,double significance);

	public boolean checkNormalityAssumption(DataFrame df,Response res,double significance);

	
}
