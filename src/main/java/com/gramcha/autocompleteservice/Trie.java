package com.gramcha.autocompleteservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Trie {

	  String currentStr="root";
	  Trie parent=null;
	  private Map<String, Trie> children = new HashMap<String, Trie>();
	  boolean completeNode = false;

	  @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((children == null) ? 0 : children.hashCode());
	    result = prime * result + ((currentStr == null) ? 0 : currentStr.hashCode());
	    return result;
	  }
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Trie other = (Trie) obj;
	    if (children == null) {
	      if (other.children != null)
	        return false;
	    } else if (!children.equals(other.children))
	      return false;
	    if (currentStr == null) {
	      if (other.currentStr != null)
	        return false;
	    } else if (!currentStr.equals(other.currentStr))
	      return false;
	    return true;
	  }
	  private Trie getLastNode(String input,int currentpos, int length)
	  { 
	    //System.out.println(input+" currentpos "+ currentpos +" length "+input.length());
	    String key = input;
	    if(currentpos==1 && input.length()>0)
	      {
	      //System.out.println("%%%");
	      key = input.substring(0,currentpos);
	      }
	    else if(currentpos-1<input.length()&&currentpos>0)
	      key = input.substring(0,currentpos-1);
	    /*else
	      return this;*/
	    //System.out.println("key "+key);
	    Trie result = children.get(key);
	    if(result!=null /*&& currentpos-1<=length*/) {      
	      //result = result.getLastNode(key,currentpos+1,length);
	      result = result.getLastNode(key,currentpos,currentpos+1);
	    }
	    //System.out.println("returning last element "+result);
	    if(result==null)
	      return this;
	    else
	      return result;
	  }
	  public void insert(String input,int currentpos, int length)
	  {
	    //System.out.println("cpos = "+currentpos+" length = "+length);
	    Trie prevPar=null;
	    Trie lastChild=null;
	    for(int i=currentpos;i<=length;i++) {
	      
	    if(i+1<length)
	    {
	      Trie child = null;
	      String key = input.substring(0,i+1);      
	      Trie parent =null; 
	      parent = getLastNode(key,i+1,length);
	      //System.out.println("**parent** "+parent);
	      boolean spl =false;
	        if (prevPar != null) {
	          //System.out.println("####");
	          parent = prevPar.getLastNode(key, i + 1, length);
	        } else {
	          //System.out.println("$$$$");
	        }
	      //System.out.println("**after parent** "+parent);
	      //System.out.println("this "+this);
	      //System.out.println("inserting "+key);
	      if(parent!=this || i==currentpos) {
	        child = new Trie();
	        child.currentStr=key;
	        child.parent=parent;
	        parent.children.put(key, child);
	        //System.out.println("after insert "+parent);
	        lastChild = child;
	      }
	      prevPar = parent;
	    }
	    else if(i+1==length)
	    {
	      Trie child = new Trie();
	      String key = input;
	      //System.out.println("@@@inserting "+key);
	      //System.out.println("this "+this);
	      Trie parent = getLastNode(key,0,length);
	      child.currentStr=key;
	      //System.out.println("parent "+parent);
	      //System.out.println("lastchild "+lastChild);
	      lastChild.children.put(key, child);
	    }
	    //System.out.println("for loop "+i);
	    }
	    /*else*/
	    {
	      //System.out.println("reached end "+this);
	    }
	  }
	  Trie findNode(Trie current, String key,int cpos)
	  {
	    Trie parent = null;
	    if(current.parent!=null)
	      parent = current.parent;
	    int length= key.length();
	    for(int i=cpos;i<=length;i++)
	    {
	      String tempkey="" ;
	      if(i==0)
	      {
	    	  tempkey = key.substring(0, 1);	
			
	      }
	      else
	        tempkey = key.substring(0, i);
	      //System.out.println(i+" search key "+tempkey);
	      if(current.currentStr.equals(tempkey))
	      {
	        if(key.equals(tempkey))
	        {
	          //System.out.println("@returning at "+tempkey);
	          return current;
	        }
	        else
	        {
	          
	        }
	      }
	      else
	      {
	        //System.out.println("* "+tempkey);
	        Trie tempnode = current.children.get(tempkey);
	        if (tempnode != null && key.equals(tempkey)) {
	          //System.out.println("returning at " + tempkey);
	          return tempnode;
	        }
	        else if(tempnode == null) {
	          
	        } 
	        else
	        {
	          current=tempnode;
	          //System.out.println(" $$$ "+current);
	        }
	      }
	    }
	    //System.out.println("returning null");
	    return null;
	  }
	  public Trie insert(String key)
	  {
	    //Check key is already available
	    Trie head = this;
	    Trie result = findNode(head,key,0);
	    if(result!=null)
	    {
	      //System.out.println("Already in system "+ result);
	      return result; 
	    }
	    else
	    {
	      int length = key.length();
	      Trie current = head;
	      String tempkey;
	      for(int i=0;i<=length;i++)
	      {
	        if(i==0)
	        {
	          tempkey = key.substring(0, 1);
	        }
	        else
	          tempkey = key.substring(0, i);
	        Trie tempnode = findNode(current,tempkey,0); 
	        if(tempnode==null)
	        {
	          tempnode = new Trie();
	          tempnode.currentStr = tempkey;
	          tempnode.parent = current;
	          current.children.put(tempkey, tempnode);
	          current = tempnode;
	        }
	        else
	        {
	          current = tempnode;
	        }
	      }
	      current.completeNode=true;
	      //System.out.println("Initial head "+head);
	      //System.out.println("final current "+current);
	      //findNode(Trie current, String key,int cpos)
	    }
	    return null;
	  }
	  public static Trie create()
	  {
	    Trie tree = new Trie();
	    Trie child = new Trie();
	    child.currentStr="t";
	    child.parent=tree;
	    tree.children.put(child.currentStr, child);
	    Trie child2 = new Trie();
	    child2.currentStr="te";
	    child2.parent=child;
	    child.children.put(child2.currentStr, child2);
	    Trie child3 = new Trie();
	    child3.currentStr="tea";
	    child3.completeNode=true;
	    child3.parent=child2;
	    child2.children.put(child3.currentStr, child3);
	    return tree;
	  }
	  @Override
	  public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append(currentStr);
	    
	    builder.append(" [terminal= ");
	    builder.append(completeNode);
	    builder.append("] ");
	    builder.append(" -->");    
	    builder.append(" ");
	    builder.append(children.toString());
	    builder.append(" ");
	    builder.append(children.size());
	    return builder.toString();
	  }


	    protected Collection<String> allMatchingResults() {
	        List<String> results = new ArrayList<String>();
	        if (this.completeNode) {
	            results.add(this.currentStr);
	        }
	        for (Entry<String, Trie> entry : children.entrySet()) {
	        	//System.out.println("entry "+entry.getKey());
	            Trie child = entry.getValue();
	            Collection<String> childPrefixes = child.allMatchingResults();
	            results.addAll(childPrefixes);
	        }
	        return results;
	    }
	    public Collection<String> getResults(String prefix)
	    {	
	    	Trie resultNodeHead = findNode(this, prefix, 0);
	    	return resultNodeHead.allMatchingResults();
	    }
	    public AutoCompleteResponse getResultsResponse(String prefix)
	    {	
	    	long startTime = System.nanoTime();    
			Collection<String> result = this.getResults(prefix);
			long estimatedTime = System.nanoTime() - startTime;
			AutoCompleteResponse response = new AutoCompleteResponse(result," "+estimatedTime);
			System.out.println("results " + result);
			return response;
	    }
	    /*
	  public static void main(String[] args) {
	    // TODO Auto-generated method stub

	    Trie tree = new Trie();
	    String value ="tea";
	    tree.insert(value, 0, value.length());
	    //System.out.println("tree "+tree.toString());
	    value ="ten";
	    tree.insert(value, 0, value.length());
	    //System.out.println("tree "+tree.toString());
	    
	    
	    Trie result = Trie.create();
	    Trie head = result;
	    System.out.println(result);
	    //System.out.println("size "+ObjectSizeFetcher.getObjectSize(result));
	    
	    result.insert("test");
	    result.insert("tesla");
	    Trie result1 = result.findNode(result,"t",0);
	    System.out.println(result1);
	    //System.out.println("size "+ObjectSizeFetcher.getObjectSize(result));
	    System.out.println(result1.allMatchingResults().toString());
	  }
*/
}
