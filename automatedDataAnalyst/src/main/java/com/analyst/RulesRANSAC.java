package com.analyst;

import com.analysis.providers.supervised.IRansac;
import com.dataframe.IDataFrame;

public class RulesRANSAC extends RulesExecutorModel {
	
	public RulesRANSAC(IDataFrame df,IRansac analysisExecutor) {
		super(df,"ransac", "ransac",analysisExecutor);

	}

}
