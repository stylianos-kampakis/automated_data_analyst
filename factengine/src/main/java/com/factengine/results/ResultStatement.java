package com.factengine.results;


/**
 * The result statement is used by the rule engine in order to describe the results
 * of the analysis and then produce a natural language description of it.
 * 
 * The 'description' variable holds the description of an event that took place.
 * The 'id' variable is used in order to identify clusters of descriptions.
 * 
 * @author stelios
 *
 */
public class ResultStatement {
	
private String description;
private ResultTags tag;

public ResultStatement(String description,ResultTags tag){
	this.description=description;
	this.tag=tag;
}

public String getDescription(){
	return description;
}

public ResultTags getTag(){
	return tag;
}
	

}
