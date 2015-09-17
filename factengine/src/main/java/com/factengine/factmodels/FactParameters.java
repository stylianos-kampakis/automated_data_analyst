package com.factengine.factmodels;

import java.util.Arrays;

import com.analysis.Algorithms;
import com.factengine.descriptionenums.ModelProperties;
import com.factengine.descriptionenums.ParameterSetProperties;

public class FactParameters {
	//this array contains all the algorithms for which these parameters can be applied
	protected Algorithms[] algorithms;
	//this array contains mode properties which if they are satisfied by a model, then the parameter settings can be used
	protected ModelProperties[] modelProperties;
	protected String parameterDescription;
	protected ParameterSetProperties paramProperties;
		
	
	/**
	 * Tests whether a particular model can be used with these parameter setting.
	 * The test returns true if any of the model properties are satisfied, or if the algorithm's name
	 * is in the list.
	 * 
	 * @param model
	 * @return
	 */
	public boolean isModelValid(FactModel model){
		if(Arrays.asList(algorithms).contains(model.modelName)){
			return true;
		}
		
		for(ModelProperties property:modelProperties){
			if(Arrays.asList(modelProperties).contains(property)){
				return true;
			}
		}
		
		return false;
		
	}
	
	public ParameterSetProperties getParamProperties(){
		return this.paramProperties;
	}
	
	public String getDescription(){
		return this.parameterDescription;
	}
	
}
