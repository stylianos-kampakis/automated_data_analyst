package com.analysis;

import java.io.File;

import com.analysis.parameters.ParameterSet;
import com.analysis.providers.supervised.IGaussianProcess;
import com.analysis.providers.supervised.IKnn;
import com.analysis.providers.supervised.ILinearRegression;
import com.analysis.providers.supervised.ILogisticRegression;
import com.analysis.providers.supervised.IMixedEffectsModelClassification;
import com.analysis.providers.supervised.IMixedEffectsModelLinearRegression;
import com.analysis.providers.supervised.INaiveBayes;
import com.analysis.providers.supervised.INegativeBinomialRegression;
import com.analysis.providers.supervised.INeuralNetworks;
import com.analysis.providers.supervised.IPoissonRegression;
import com.analysis.providers.supervised.IRandomForests;
import com.analysis.providers.supervised.ISvm;
import com.analysis.results.PredictionResultSet;
import com.dataframe.IDataFrame;

/**AnalysisExecutor
 * 
 * This class is the central commander for any kind of analysis. 
 * It can load a configuration file outlying which technologies and libraries are to be used
 * for each kind of algorithm.
 * 
 *  This design decouples the development of specific interfaces 
 *  for languages/tools (R, Python, Java libs) etc.
*
* @author Stylianos Kampakis
* @version 0.1 March 3, 2015.
*/

//The class has been deprecated in favour of a different design
@Deprecated
public class AnalysisExecutor {
	
	IDataFrame df;
		
	//Predictive models and fit
	ILinearRegression linearRegressionProvider;
	IPoissonRegression poissonRegressionProvider;
	ILogisticRegression logisticRegressionProvider;
	INegativeBinomialRegression negativeBinomialRegressionProvider;
	
	IMixedEffectsModelLinearRegression mixedEffectsModelLinearRegressionProvider;
	IMixedEffectsModelClassification mixedEffectsModelClassification;

	
	INeuralNetworks neuralNetworksProvider;	
	IRandomForests randomForestsProvider;
	IKnn knnProvider;
	ISvm svmProvider;
	IGaussianProcess gaussianProcessProvider;
	INaiveBayes naivesBayesProvider;
	
	public void setConfigFile(File path){
		
	}
	
	
	/**
	 * Creates new providers, anything stored in the old ones is deleted.
	 * 
	 */
	public void initialize(){
		
	}
	
	public void setDataFrame(IDataFrame df){
		this.df=df;
	}
	
	
	public PredictionResultSet crossVal(int numFolds,int numRepeats,Algorithms algorithm){
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}
	
	
	public PredictionResultSet fit(Algorithms algorithm,ParameterSet parameters){
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}

	
	public PredictionResultSet predict(Algorithms algorithm,ParameterSet parameters) {
		PredictionResultSet results=new PredictionResultSet();
		
		switch(algorithm){
		case LINEAR_REGRESSION:
		
		}
		
		return results;
		
	}


}
