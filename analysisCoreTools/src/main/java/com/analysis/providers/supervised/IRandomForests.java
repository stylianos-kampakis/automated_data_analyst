package com.analysis.providers.supervised;


public interface IRandomForests extends IClassification,IRegression {
	
	public void setNumTrees(int numTrees);

}
