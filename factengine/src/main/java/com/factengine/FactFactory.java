package com.factengine;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;

import com.analysis.parameters.ParameterSet;
import com.analysis.results.PredictionResultSet;
import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPointType;
import com.dataframe.IDataFrame;
import com.datautils.DataFrameTypeException;
import com.datautils.DataUtils;
import com.factengine.datasetdescriptors.FactColumn;
import com.factengine.datasetdescriptors.FactDataFrame;
import com.factengine.datasetdescriptors.FactRow;
import com.factengine.descriptionenums.DatasetDescriptionTags;
import com.factengine.factmodels.FactModel;
import com.factengine.performancedescriptors.FactPerformanceRegression;

/**
 * The FactFactory class contains methods that get 
 * as input a DataFrame or another object and then comes back with an ArrayList of facts.
 * These facts should be inserted in a kieSession and reasoned upon.
 * 
 * The facts are always created at the initialization of the object. The rest of the functions
 * simply update the existing facts.
 * 
 * @author stelios
 *
 */
public class FactFactory {

	ArrayList<FactColumn> factsColumn;
	ArrayList<FactRow> factsRow;
	IDataFrame df;
	
	public FactFactory(IDataFrame df){
		
		this.df=df;
		
		factsColumn= new ArrayList<FactColumn>();
		factsRow = new ArrayList<FactRow>();
		
		for(int i=1;i<=df.getNumberColumns();i++){
			//The fact row is constructed taking the row number as the argument
			FactColumn fact=new FactColumn(i);		
			factsColumn.add(fact);
		}	
		
		for(int i=1;i<=df.getNumberRows();i++){
			//The fact row is constructed taking the row number as the argument
			FactRow fact=new FactRow(i,df.getNumberColumns());
			factsRow.add(fact);
		}
	}
	
	

	public ArrayList<FactColumn> getFactsColumn(){
		return this.factsColumn;
	}
	
	public ArrayList<FactRow> getFactsRow(){
		return this.factsRow;
	}
	
	/**
	 * 
	 * Utility method used to insert lists of facts inside the kieSession.
	 * 
	 * @param kSession
	 * @param facts
	 */
	public void factInsertor(KieSession kSession, List<? extends Fact> facts){
		for(Fact fact : facts){
			kSession.insert(fact);
		}
	}
	
	/**
	 * 
	 * This functions is used after a model has been executed in a KieSession in order to convert
	 * the results of the model to a FactPerformanceRegressionModel.
	 * 
	 * @param model
	 * @param results
	 * @param parameters
	 * @return
	 */
	public FactPerformanceRegression createRegressionFacts(FactModel model,PredictionResultSet results, ParameterSet parameters){
		return new FactPerformanceRegression(model,results,parameters);
	}

	
}
