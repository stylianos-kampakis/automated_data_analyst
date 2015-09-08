package com.analysis.providers.supervised;

import java.util.HashMap;

import com.analysis.results.Coefficients;

public interface IStatisticalMethod {
	
	public Double getAIC();
	
	public Double getBIC();
	
	public Double getLogLikelihood();
	
	public Coefficients getCoefficients();

}
