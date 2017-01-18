package com.gramcha.autocompleteservice;

import java.util.ArrayList;
import java.util.Collection;

public class AutoCompleteResponse {

	
	Collection<String> results = new ArrayList<String>();
	String queryTime="";
	public AutoCompleteResponse(Collection<String> result, String queryTime) {
		super();
		this.results = result;
		this.queryTime = queryTime;
	}
	
	public Collection<String> getResults() {
		return results;
	}


	public void setResults(ArrayList<String> results) {
		this.results = results;
	}


	public String getQueryTime() {
		return queryTime;
	}


	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}


	@Override
	public String toString() {
		return "AutoCompleteResponse [results=" + results + ", queryTime=" + queryTime + "]";
	}
}
