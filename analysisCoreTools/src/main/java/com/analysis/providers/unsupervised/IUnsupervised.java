package com.analysis.providers.unsupervised;

import com.analysis.parameters.ParameterSet;
import com.dataframe.IDataFrame;

public interface IUnsupervised {
	
	public void fit(IDataFrame df, ParameterSet parameters);

}
