package com.analysis.providers;

/**
 * IProvider is an interface that has to be implemented by any provider class. 
 * 
 * @author stelios
 *
 */
public interface IProvider {

	/**
	 * Returns the code for the latest process that has been executed.
	 * @return
	 */
	public String getCode();
	

	
}
