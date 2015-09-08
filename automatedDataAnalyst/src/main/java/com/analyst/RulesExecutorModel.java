package com.analyst;

import com.analysis.providers.supervised.IModel;
import com.dataframe.IDataFrame;

public class RulesExecutorModel extends RulesExecutor {

	protected IModel analysisExecutor;
	
	public RulesExecutorModel(IDataFrame df,String packageName, String sessionName, IModel analysisExecutor) {
		super(df, packageName, sessionName);
		this.analysisExecutor=analysisExecutor;
	}

}
