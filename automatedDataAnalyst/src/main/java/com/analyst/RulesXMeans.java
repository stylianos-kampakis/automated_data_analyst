package com.analyst;

import com.analysis.providers.supervised.IModel;
import com.dataframe.IDataFrame;

public class RulesXMeans extends RulesExecutorModel {

	public RulesXMeans(IDataFrame df, String sessionName, IModel analysisExecutor) {
		super(df,"clustering_preparation", "clustering_preparation", analysisExecutor);
		// TODO Auto-generated constructor stub
		
	}

}
