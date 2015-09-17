package com.factengine.factmodels;

import com.analysis.Algorithms;
import com.factengine.descriptionenums.ModelProperties;

public class NaiveBayesFactModel extends FactModel {

	public NaiveBayesFactModel(Algorithms name, ModelProperties[] modelProperties) {
	
	
	super(Algorithms.NAIVE_BAYES,new ModelProperties[]{ModelProperties.KERNEL_METHOD,
		
			ModelProperties.NUMERICAL_DATA_ONLY,
			//REFERENCE : ESLII
			ModelProperties.BENCHMARK
	
	});
}
	
}
