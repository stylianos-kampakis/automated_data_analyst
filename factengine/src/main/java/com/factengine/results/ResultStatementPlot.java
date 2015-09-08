package com.factengine.results;

import java.awt.Image;

public class ResultStatementPlot extends ResultStatement {

	Image plot;
	String description;
	
	public ResultStatementPlot(String description, ResultTags id, Image plot) {
		super(description, id);
		this.plot=plot;		
	}

	public Image getPlot(){
		return this.plot;
	}
	
	
}
