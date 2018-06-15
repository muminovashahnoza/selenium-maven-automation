package com.dice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordSearch {

	public static void main(String[] args) {
		//print time, when test started
		System.out.println("Test started - " + LocalDateTime.now());
		
		
		 WebDriverManager.chromedriver().setup();
	      
	      //creating array list to hold keywords
		 List<String> keywords = new ArrayList<>();
	      
	      String line;   
	   //read file, using bufeered reader, add keywords in the list
	     try ( FileReader fr = new FileReader("keywords.txt");
	      BufferedReader bf = new BufferedReader(fr); )  {
	    	  while ( (line = bf.readLine() ) != null) {
	    		    keywords.add(line);
	    		     }
		} catch (IOException e) {
			System.out.println("IO");
		}
	    		    
	     
	      //System.out.println(keywords.size()); 20
	    /** Search for keywords on dice.com,
	     * using loop 
	     * location doesn't change
	     */
	    try {
	      for(int i=0; i<keywords.size(); i++) {
	    	  //creating driver object
	    	   WebDriver driver1 = new ChromeDriver();
	    	   //maximizing window
	    	   driver1.manage().window().maximize();
	    	  //set universal wait time in case web page is slow
		   driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  //go to www.dice.com  
	    	  driver1.get("https://www.dice.com");
	    	  //assign array list item to a variable
	    	  String keyword = keywords.get(i);
	    	  //send variable keyword to a field Keyword
	      driver1.findElement(By.id("search-field-keyword")).sendKeys(keyword);
	     // send location 
	      driver1.findElement(By.id("search-field-location")).clear();
	      driver1.findElement(By.id("search-field-location")).sendKeys("Venice, FL");
	      //click find jobs button
	      driver1.findElement(By.id("findTechJobs")).click();
	     //in case there no jobs found for keyword 
	      if(driver1.getTitle().equalsIgnoreCase("Jobs not found | Dice.com")) {
	    	  System.out.println("for keyword - " + keyword + " 0 jobs found");
	    	  driver1.close();
	    	  continue;
	      }else {
	      driver1.findElement(By.id("posiCountId")).getText();
	      }
	       
	       //assign String value of CountId to a var
	    	  String countOfPositions = driver1.findElement(By.id("posiCountId")).getText();
	    	  //assign value of var String to an int var count
	    	int  count = Integer.parseInt(countOfPositions.replaceAll(",", ""));
	     
	       
	        //print out search result
	    		 System.out.println("For keyword :" + keyword + " found : " + count + " positions");
	    		 //close the object driver
	    		 driver1.close();
	    		 
	      }
	     } catch (NoSuchElementException e) {
	       	e.printStackTrace() ;
	
	     }
	      
	      
	      
	      //print time when test ended
	        System.out.println("Test complited - " + LocalDateTime.now());

	}

}
