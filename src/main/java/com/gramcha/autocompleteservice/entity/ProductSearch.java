package com.gramcha.autocompleteservice.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Repository
@Transactional
//@EnableJpaRepositories(basePackages="com.gramcha.autocompleteservice", entityManagerFactoryRef="emf")
public class ProductSearch {

	  @PersistenceContext
	  private EntityManager entityManager;


	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * A basic search for the entity User. The search is done by exact match per
	   * keywords on fields name, city and email.
	   * 
	   * @param text The query text.
	   */
	  public List<products> search(String text) {
		  
	    // get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	      org.hibernate.search.jpa.Search.
	      getFullTextEntityManager(entityManager);
	    
	    // create the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	      fullTextEntityManager.getSearchFactory()
	      .buildQueryBuilder().forEntity(products.class).get();
	    
	    // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	      queryBuilder
	        .keyword()
	        .onFields("title")
	        .matching(text)
	        .createQuery();
	    
	    //System.out.println("query "+query.toString());
	    // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery =
	      fullTextEntityManager.createFullTextQuery(query, products.class);
	    jpaQuery.setMaxResults(10);
	    //System.out.println("query "+jpaQuery.toString());
	    // execute search and return results (sorted by relevance as default)
	    @SuppressWarnings("unchecked")
	    List<products> results = jpaQuery.getResultList();
	    
	    return results;
	  } // method search

}
