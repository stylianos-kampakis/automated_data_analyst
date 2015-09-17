package com.factengine.factmodels;

import java.util.ArrayList;

import com.analysis.Algorithms;
import com.factengine.Fact;
import com.factengine.descriptionenums.ModelProperties;

/**
 * FactModel is a class that is used as the parent for other classes that describe models about facts.
 * The FactModel contains two important characteristics regarding the description of a fact:
 * 
 * a. the name of the model.
 * b. the tags/properties that define it.
 * 
 * A FactModel implementation (e.g. SVMFactModel) is inserted into a kieSession in order to be reasoned upon.
 * The model name and properties have to be defined at creation time and are immutable. The reasoning behind this
 * is that the knowledge regarding models should have been specified a priori at build time, and not at runtime.
 * 
 * 
 * @author stelios
 *
 */
public class FactModel extends Fact {
	
	protected Algorithms modelName;
	ModelProperties[] modelProperties;

	
	public FactModel(Algorithms name, ModelProperties[] modelProperties){
		this.modelName=name;
		this.modelProperties=modelProperties;
	}
	
	
	public ModelProperties[] getModelProperties(){
		return modelProperties;
	}
	
	
	public Algorithms getModelName(){
		return modelName;
	}
	
	
}
