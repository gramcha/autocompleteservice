package com.gramcha.autocompleteservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.search.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gramcha.autocompleteservice.entity.ProductSearch;
import com.gramcha.autocompleteservice.entity.products;

@Component
public class QueryExecutor {

	@Autowired
	ProductSearch psearch;
	 /*private static QueryExecutor instance = new QueryExecutor();
	Trie dataTree = null;
	public QueryExecutor() {
		
		dataTree = DataSetLoader.getRadixtree();
	}
	 public static QueryExecutor getInstance( ) {
	      return instance;
	   }*/
	public AutoCompleteResponse execute(String prefix)
	{
		long startTime = System.nanoTime();
		List<products> result = psearch.search(prefix);
		long estimatedTime = System.nanoTime() - startTime;

		Collection<String> list = new ArrayList<String>();

		for (products item : result) {
			list.add(item.getTitle());
		}
		AutoCompleteResponse response = new AutoCompleteResponse(list, " "+estimatedTime);
		/* return dataTree.getResultsResponse(prefix); */
		return response;
	}
}
