package com.gramcha.autocompleteservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class DataSetLoader {

	public static Trie getRadixtree() {
		Trie radixtree = new Trie();
		
		Scanner linReader;
		try {
			linReader = new Scanner(new File("words.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return radixtree;
		}
		System.out.println("loading...");
        while (linReader.hasNext())
        {
            String line = linReader.nextLine();
            if(false == StringUtils.isEmpty(line))
            	radixtree.insert(line.toLowerCase());
            //System.out.println(line);
        }
        System.out.println("Dataset loaded.");
        
        linReader.close();
        return radixtree;
	}

/*	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Trie tree = DataSetLoader.getRadixtree();
		long startTime = System.nanoTime();    
		Collection<String> result = tree.getResults("abro");
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println("results " + result);
		
		System.out.println("execution time - "+estimatedTime);
		startTime = System.nanoTime();    
		result = tree.getResults("lukan");
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("results " + result);
		
		System.out.println("execution time - "+estimatedTime);
	
	}*/
}
