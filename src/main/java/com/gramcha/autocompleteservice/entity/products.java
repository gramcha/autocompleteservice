package com.gramcha.autocompleteservice.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "products")
public class products {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	//@Field(store = Store.YES,index = Index.YES, analyze = Analyze.YES)
	@Field(name="title", index=Index.YES, store=Store.NO, analyze = Analyze.YES)
	@NotNull	  
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public products(long id) { 
	    this.id = id;
	  }
	public products(String title) {
	    this.title = title;
	    
	  }
	public long getId() {
	    return id;
	  }
	@Override
	public String toString() {
		return "products [id=" + id + ", title=" + title + "]";
	}
	public products() {
	
	}
	
}
