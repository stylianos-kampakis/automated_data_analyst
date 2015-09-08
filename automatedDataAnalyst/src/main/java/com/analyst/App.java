package com.analyst;

import java.io.IOException;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.analysis.providers.supervised.ILinearRegression;
import com.dataframe.DataFrame;
import com.dataframe.DataFrameIndexException;
import com.datautils.DataUtils;
import com.describer.SimpleDescriber;
import com.factengine.FactFactory;
import com.factengine.Response;
import com.factengine.commanders.ImputationCommander;
import com.factengine.datasetdescriptors.FactRow;
import com.factengine.results.Results;
import com.rinterface.RLinearRegressionProvider;

public class App {

	public static void main(String[] args) {
		DataFrame df_missing;
		DataFrame df;
		
		App c=new App();
		Class cls = c.getClass();
	   	 
    	// finds resource relative to the class location
    	java.net.URL url = cls.getResource("/iris.csv");
    	
    	df=new DataFrame();

    	try {
			df.readCSV(url.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	df_missing=new DataFrame();
    	url = cls.getResource("/iris_missing.csv");
    	try {
			df_missing.readCSV(url.getPath());
			df_missing.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	RulesDataCleaner cleaner=new RulesDataCleaner(df_missing,RulesDataCleaner.imputationOptions.MEAN);
		cleaner.cleanData();
		System.out.println(cleaner.getDataFrame().toString());
                
	}

}
