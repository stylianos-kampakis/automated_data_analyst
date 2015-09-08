package com.analysis.providers.statisticaltests;

import com.analysis.parameters.ParameterSet;
import com.dataframe.IDataFrame;

public interface IStatisticalTest {
		
	public double test(IDataFrame df,String... variables);

}
