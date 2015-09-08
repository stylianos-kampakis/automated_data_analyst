package com.rinterface;
import java.io.IOException;
import java.util.HashMap;

import com.analysis.parameters.ParameterSet;
import com.analysis.providers.supervised.ILinearRegression;
import com.analysis.results.Coefficients;
import com.analysis.results.PredictionResultSet;
import com.dataframe.DataFrame;
import com.factengine.Response;
import org.expr.rcaller.RCaller;
import org.expr.rcaller.RCode;


public class RLinearRegressionProvider extends RProviderBase implements ILinearRegression {
	
	//This variable indicates whether a model exists or not. Once a model has been fit
	//to a dataset, then this variable is set to true.
	private boolean modelExists=false;
	
	
	public RLinearRegressionProvider(){
		super();
	}
	
	public RLinearRegressionProvider(String path){
		super(path);
	}

	/**
	 * Fits a linear regression model to the specified dataframe, by using the function 'lm' of R.
	 * It assumes that all the variables, besides the response, are covariates.
	 * 
	 */
	public void fit(Response res, DataFrame df) {
		String response=res.getResponse();
		initialize();
		
		String dfR=df.getRDataFrame();
		code.addRCode(dfR);
		//This is a standard pattern of code in this class. First we create a template object to hold the code.
		//Then we create a string called call, that will go into the runAndReturnResultOnline method of caller.
		//These two are then passed to the addToCodestore function, so that we update the codestore.
		String template="lm1=lm(<RESPONSE>~.,data=DataFrame)";
		template=template.replace("<RESPONSE>", response);
		
		
		code.addRCode(template);

		String call="lm1";
		
		caller.setRCode(code);
		caller.runAndReturnResultOnline(call);
		this.modelExists=true;

		addToCodestore(code,call);
				
	}

	/**
	 * Fits a linear regression model to the specified dataframe, by using the function 'lm' of R.
	 * The user needs to specify the names of the covariates in the 'Covariates' argument.
	 * 
	 */
	public void fit(Response res, DataFrame df, String[] covariates) {
		initialize();
		
		String response=res.getResponse();
		String dfR=df.getRDataFrame();
		
		String template="lm1=lm(<RESPONSE>~";
		for(String covariate :covariates){
			
			template=template+covariate+"+";
		}
			
		template=template+",data=DataFrame)";
		template=template.replace("+,", ",");
		
		template=template.replace("<RESPONSE>", response);
		
		this.clearAndSetCode(dfR);
		
		
		code.addRCode(template);

		String call="lm1";
		
		caller.setRCode(code);
		caller.runAndReturnResultOnline(call);
		this.modelExists=true;
		
		addToCodestore(code,call);
		
	}

	
	//These methods are 'inactive'. They have to be implemented because of inheritance from IModel,
	//but they don't carry any meaning for linear regression, unless a regularizer would be
	//to be used (which is not used in this provider).

	public double[] predict(DataFrame df, ParameterSet parameters) {
		
		return predict(df);
		
	}

	public void fit(Response response, DataFrame df, ParameterSet parameters) {
		this.fit(response, df);
		
	}

	public void fit(Response response, DataFrame df, String[] covariates,
			ParameterSet parameters) {
		this.fit(response,df, covariates);
		
	}
	
	
	/**
	 * Makes predictions for a new dataset. The model should have been fit first to a dataset, otherwise
	 * the function will not run.
	 * 
	 * 
	 */
	public double[] predict(DataFrame df) throws IllegalStateException {
		if(modelExists){
		String dfR=df.getRDataFrame();
		
		String template="results=predict(lm1,data="+dfR.replace("DataFrame=", "")+")";
		clearAndSetCode(template);
		caller.runAndReturnResultOnline("results");
		double[] results=caller.getParser().getAsDoubleArray("results");
		return results;
		}
		else{
			throw new IllegalStateException("There must a model that has been fit first.");
		}
	}



	/**
	 * Gets the fitted values from the last fit of the model to a dataset.
	 */
	public double[] getFitted() {
		if(modelExists){
			String template="results=fitted(lm1)";
			clearAndSetCode(template);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results;
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	/**
	 * Gets the Akaike Information Criterion from the last fit of the model.
	 * 
	 */
	public Double getAIC() {
		if(modelExists){
			String template="results=AIC(lm1)";
			return getSingleResult(template);
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	/**
	 * Gets the Bayesian Information Criterion from the last fit of the model.
	 * 
	 */
	public Double getBIC() {
		if(modelExists){
			String template="results=BIC(lm1)";
			return getSingleResult(template);
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	

	
	/**
	 * Gets the log likelihood from the last fit of the model.
	 * 
	 */
	public Double getLogLikelihood() {
		if(modelExists){
			String template="results=summary(logLik(lm1))[[1]]";
			return getSingleResult(template);

			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}

	
	
	/**
	 * Gets the coefficients from the last fit of the model. NOT WRITTEN TO CODESTORE.
	 * 
	 */
	public Coefficients getCoefficients() {
		if(modelExists){
			
			HashMap<String,Double> results=new HashMap<String,Double>();
			
			String template="results=coef(lm1)";
			clearAndSetCode(template);
			caller.runAndReturnResultOnline("names(results)");
			
			String[] names=caller.getParser().getAsStringArray("names(results)");
			
			caller.runAndReturnResultOnline("results");
			double[] coefs=caller.getParser().getAsDoubleArray("results");
			
			
			for(int i=0;i<names.length;i++){
				results.put(names[i], coefs[i]);
			}
			
			HashMap<String,Double> pvalues=new HashMap<String,Double>();
			
			template="results=summary(lm1)$coefficients[,4]";
			clearAndSetCode(template);
			caller.runAndReturnResultOnline("results");
			
			
			double[] values=caller.getParser().getAsDoubleArray("results");
			
			for(int i=0;i<names.length;i++){
				pvalues.put(names[i], values[i]);
			}
			
			return new Coefficients(results,pvalues);
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	
	/**
	 * Gets the R squared from the last fit of the model.
	 * 
	 */
	public Double rSquared() {
		if(modelExists){
			String template="results=summary(lm1)$r.squared";
			clearAndSetCode(template);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				
			return Double.NaN;
			
			}
	}
	
	/**
	 * Gets the adjusted r squared from the last fit of the model. If the model has not been fit, then
	 * it throws an error.
	 * 
	 */
	public double adjustedRSquared() {
		if(modelExists){
			String template="results=summary(lm1)$adj.r.squared";
			clearAndSetCode(template);
			caller.runAndReturnResultOnline("results");
			double[] results=caller.getParser().getAsDoubleArray("results");
			
			return results[0];
			}
			else{
				throw new IllegalStateException("There must a model that has been fit first.");
			}
	}
	


	public boolean modelExists() {
		return modelExists;
	}

	/**
	 * Uses the 'gvlma' package from R to test the global assumptions behind linear regression.
	 * The paper can be found at: 
	 */
	public boolean checkGlobalAssumptions(DataFrame df,Response res,double significance) {
		this.installPackageIfNotInstalled("gvlma");
		String response=res.getResponse();
		initialize();
		
		String dfR=df.getRDataFrame();
		code.addRCode(dfR);
		
		String template="g=gvlma(<RESPONSE>~.,data=DataFrame)";
		template=template.replace("<RESPONSE>", response);
		
		
		code.addRCode(template);

		caller.setRCode(code);
		caller.runAndReturnResultOnline("g$GlobalTest$GlobalStat$pvalue[1]");
		Double pvalue=caller.getParser().getAsDoubleArray("g$GlobalTest$GlobalStat$pvalue[1]")[0];
		
		if(pvalue<significance){
		return false;
		}
		else{
		return true;
		}
	}

	public boolean checkLinearityAssumption() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkHomoskedasticityAssumption() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkNormalityAssumption() {
		// TODO Auto-generated method stub
		return false;
	}

	


}
