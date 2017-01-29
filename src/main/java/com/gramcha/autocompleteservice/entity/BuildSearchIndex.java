package com.gramcha.autocompleteservice.entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * The only meaning for this class is to build the Lucene index at application
 * startup. This is needed in this example because the database is filled 
 * before and each time the web application is started. In a normal web 
 * application probably you don't need to do this.
 * 
 * @author netgloo
 */
@Component
public class BuildSearchIndex
implements ApplicationListener<ApplicationReadyEvent> {
  
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  @PersistenceContext
  private EntityManager entityManager;
  

  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  @SuppressWarnings("unchecked")
  @Transactional
void doIndex()
  {
	  
	  FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	  //entityManager.getTransaction().begin();
	  System.out.println("*****"+"indexing started");
	  List<products>  items= entityManager.createQuery("select prt from products prt").getResultList();
	  System.out.println(items.size());
	  for (products item : items) {
	      fullTextEntityManager.index(item);
	      System.out.println("*****"+item.toString());
	  } 
	  
	  //entityManager.getTransaction().commit();
	  entityManager.close();
	  System.out.println("*****"+"indexing completed");
	  
	  
	  /*fullTextSession.setFlushMode(FlushMode.MANUAL);
	  fullTextSession.setCacheMode(CacheMode.IGNORE);
	  transaction = fullTextSession.beginTransaction();
	  //Scrollable results will avoid loading too many objects in memory
	  ScrollableResults results = fullTextSession.createCriteria( Email.class )
	      .setFetchSize(BATCH_SIZE)
	      .scroll( ScrollMode.FORWARD_ONLY );
	  int index = 0;
	  while( results.next() ) {
	      index++;
	      fullTextSession.index( results.get(0) ); //index each element
	      if (index % BATCH_SIZE == 0) {
	          fullTextSession.flushToIndexes(); //apply changes to indexes
	          fullTextSession.clear(); //clear since the queue is processed
	      }
	  }
	  transaction.commit();*/
	  
  }
  
  
  /**
   * Create an initial Lucene index for the data already present in the
   * database.
   * This method is called when Spring's startup.
   */
  public void onApplicationEvent(final ApplicationReadyEvent event) {
	  //doIndex();
    try {
      FullTextEntityManager fullTextEntityManager =
        Search.getFullTextEntityManager(entityManager);
      //doIndex();
      fullTextEntityManager.createIndexer().startAndWait();
      
    }
    catch (InterruptedException e) {
      System.out.println(
        "An error occurred trying to build the serach index: " +
         e.toString());
    }
    return;
  }


} // class