package com.factengine.factmodels;

import com.factengine.descriptionenums.ParameterSetProperties;

public class TestParameters {

	private FactParameters params;
	
	public TestParameters(FactParameters params){
		this.params=params;
	}
	
	public FactParameters getParameters(){
		return this.params;
	}
	
	public ParameterSetProperties getParameterProperties(){
		return params.getParamProperties();
	}
	
	public String getDescription(){
		return this.params.getDescription();
	}
	
}
