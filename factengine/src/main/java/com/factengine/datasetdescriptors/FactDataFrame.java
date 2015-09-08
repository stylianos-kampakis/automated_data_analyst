package com.factengine.datasetdescriptors;

import java.util.ArrayList;

import com.dataframe.IDataFrame;
import com.factengine.descriptionenums.DatasetDescriptionTags;

public class FactDataFrame {
	
	IDataFrame df;
	ArrayList<DatasetDescriptionTags> tags;

	public FactDataFrame(IDataFrame df, ArrayList<DatasetDescriptionTags> tags){
		this.df=df;
		this.tags=tags;
	}
	
}
