package com.gramcha.autocompleteservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class DataSetLoader {

	public static Trie getRadixtree() {
		Trie radixtree = new Trie();
		//simpleWordsParsing(radixtree);
		return amazonProductDatasetParsing(radixtree);
        
	}

	static Trie amazonProductDatasetParsing(Trie radixtree)
	{
		String csvFile = "amazon_dataset.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        long items=0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] productDetails = line.split(cvsSplitBy);
                items++;
                String str = productDetails[0].toLowerCase();
                str = str.replace("\"", "");
                System.out.print(items +" "+str);
                radixtree.insert(str);                
                //System.out.println(items+".Product [title= " + productDetails[0] + " , url=" + productDetails[1] + "]");
                

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		return radixtree;
	}
	private static Trie simpleWordsParsing(Trie radixtree) {
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
