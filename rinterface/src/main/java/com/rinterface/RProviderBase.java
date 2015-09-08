package com.rinterface;

import com.analysis.providers.IProvider;

import org.expr.rcaller.RCaller;
import org.expr.rcaller.RCode;

public class RProviderBase implements IProvider {

	protected String rPath;
	protected RCode code;
	protected RCaller caller;
	protected String codestore="";
	
	
	protected RProviderBase(){
		code=new RCode();
		caller=new RCaller();
	}
	
	protected RProviderBase(String path){
		code=new RCode();
		caller=new RCaller();
		this.setPath(path);
	}

	protected String getPath(){
		
		return this.rPath;
	}
	
	protected void setPath(String path){
		this.rPath=path;
	}
	
	protected void initialize(){
		code.clear();
		caller.cleanRCode();
		caller.setRExecutable(this.rPath+"R.exe");
		caller.setRscriptExecutable(this.rPath+"Rscript.exe");
	}
	
	protected void clearAndSetCode(String somecode){
		code.clear();
		code.addRCode(somecode);
		caller.setRCode(code);
	}
	
	/**
	 * Reads a single result and converts this to a double. "Inf" is converted to Double.Infinity
	 * 
	 * @param str
	 * @return
	 */
	protected Double convertStringToNumber(String str){
		Double result;
		if(str.contains("-Inf")){
			result=Double.NEGATIVE_INFINITY;
		}
		else if(str.contains("Inf")){
			result=Double.POSITIVE_INFINITY;
		}
		else{
			result=Double.parseDouble(str);
		}
		
		return result;
	}

	protected void addToCodestore(RCode code,String call){
		codestore+="\n"+code.getCode().toString()+"\n"+call;
	}
	
	/**
	 * returns a single Double result. Assumes that the R code uses 
	 * the variable "results" for storing the results of the process.
	 * @param template
	 * @return
	 */
	protected Double getSingleResult(String template){
		code.clear();
		code.addRCode(template);
		
		String call="results";
		
		caller.setRCode(code);
		caller.runAndReturnResultOnline(call);
		String[] res=caller.getParser().getAsStringArray(call);
		
		//the codestore is always updated at the end of the operation
		addToCodestore(code,call);
		
		return convertStringToNumber(res[0]);
	}
	
	protected void installPackageIfNotInstalled(String packageName){
		code.clear();
		code.addRCode("if(!('"+packageName+"' %in% installed.packages()[,\"Package\"])){install.packages('"+packageName+"')}");
		caller.setRCode(code);
		
		String call="library('"+packageName+"')";
		
		caller.runAndReturnResultOnline("library('"+packageName+"')");
		//the codestore is always updated at the end of the operation
		addToCodestore(code,call);
		}

	public String getCode() {
		// TODO Auto-generated method stub
		return this.codestore;
	}

	public void clearCode() {
		this.codestore="";
	}
	
}
