package com.analyst;

import com.analysis.providers.supervised.IModel;
import com.dataframe.IDataFrame;

public class RulesDBSCAN extends RulesExecutorModel {

	public RulesDBSCAN(IDataFrame df,String packageName, String sessionName, IModel analysisExecutor) {
		super(df, "clustering_preparation","clustering_preparation", analysisExecutor);
		// TODO Auto-generated constructor stub
	}

}
