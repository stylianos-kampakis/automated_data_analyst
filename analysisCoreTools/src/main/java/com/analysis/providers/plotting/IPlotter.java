package com.analysis.providers.plotting;

import com.dataframe.DataFrame;
import com.factengine.Response;
import com.factengine.results.ResultStatementPlot;

public interface IPlotter {
	
	public ResultStatementPlot makeHistogram(DataFrame df, Response res);

}
