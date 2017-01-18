package com.gramcha.autocompleteservice;

public class QueryExecutor {

	 private static QueryExecutor instance = new QueryExecutor();
	Trie dataTree = null;
	public QueryExecutor() {
		
		dataTree = DataSetLoader.getRadixtree();
	}
	 public static QueryExecutor getInstance( ) {
	      return instance;
	   }
	public AutoCompleteResponse execute(String prefix)
	{
		return dataTree.getResultsResponse(prefix);
	}
}
