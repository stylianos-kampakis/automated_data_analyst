package com.analyst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dataframe.DataFrameIndexException;
import com.dataframe.DataPoint;
import com.dataframe.IDataFrame;
import com.datautils.DataUtils;
import com.datautils.imputors.KnnImputor;
import com.datautils.imputors.MeanImputor;
import com.datautils.imputors.MedianImputor;
import com.factengine.Action;
import com.factengine.Fact;
import com.factengine.FactFactory;
import com.factengine.commanders.ColumnRemovalCommander;
import com.factengine.commanders.ImputationCommander;
import com.factengine.commanders.RowRemovalCommander;
import com.factengine.datasetdescriptors.FactColumn;
import com.factengine.datasetdescriptors.FactRow;
import com.factengine.results.Results;

public class RulesDataCleaner extends RulesExecutor {
	
	
	private ImputationCommander imputationCommander;
	private RowRemovalCommander rowRemovalCommander;
	private ColumnRemovalCommander columnRemovalCommander;
	
	public enum imputationOptions{MEAN,MEDIAN,KNN}
	
	public RulesDataCleaner(IDataFrame df, imputationOptions options, boolean act){
	
		
		super(df,"data_preparation","data_cleaning");
		
	    FactFactory factFactory=new FactFactory(df);

	    
		List<FactColumn> factsColumns=factFactory.getFactsColumn();
	    List<FactRow> factsRows=factFactory.getFactsRow();
	    
	    
	    switch(options){
		case MEAN:
		    imputationCommander=new ImputationCommander(new MeanImputor());
		    break;
		case MEDIAN:
		    imputationCommander=new ImputationCommander(new MedianImputor());
		    break;
		case KNN:
		    imputationCommander=new ImputationCommander(new KnnImputor());
		    break;
		}
	    rowRemovalCommander=new RowRemovalCommander();
	    columnRemovalCommander = new ColumnRemovalCommander();
	    
	    kSession.insert(factFactory);
	    factFactory.factInsertor(kSession, factsRows);
	    factFactory.factInsertor(kSession, factsColumns);
	    
	     
	    kSession.insert(df);
	    kSession.insert(imputationCommander);
	    kSession.insert(rowRemovalCommander);
	    kSession.insert(columnRemovalCommander);
	    kSession.insert(new Results());
	    if(act){
	    	kSession.insert(new Action());
	    }

	}
	
	public void cleanData(){
		kSession.fireAllRules();
	}


	public IDataFrame getDataFrame(){
		return this.df;
	}
}
