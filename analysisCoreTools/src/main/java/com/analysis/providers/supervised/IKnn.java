package com.analysis.providers.supervised;


public interface IKnn extends IModel, IClassification {
	
	public void setK(int K);

}
