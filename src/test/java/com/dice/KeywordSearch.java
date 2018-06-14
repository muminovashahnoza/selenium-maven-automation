package com.dice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordSearch {

	public static void main(String[] args) {
		
		 WebDriverManager.chromedriver().setup();
	       //invoke selenium webdriver
	      
		 List<String> keywords = new ArrayList<>();
	      
	      String line;   
	   
	     try ( FileReader fr = new FileReader("keywords.txt");
	      BufferedReader bf = new BufferedReader(fr); )  {
	    	  while ( (line = bf.readLine() ) != null) {
	    		    keywords.add(line);
	    		     }
		} catch (Exception e) {
			System.out.println("IO");
		}
	    		    
	    		 // catch (IOException e) {
	    	 // System.out.println("IOException was caught");
	      System.out.println(keywords.size());
	      
	      for(int i=0; i<keywords.size(); i++) {
	    	   WebDriver driver1 = new ChromeDriver();
	    	  //set universal wait time in case web page is slow
		        driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        
	    	  driver1.get("https://www.dice.com");
	    	  
	    	  String keyword = keywords.get(i);
	    	  
	      driver1.findElement(By.id("search-field-keyword")).sendKeys(keyword);
	     
	      driver1.findElement(By.id("search-field-location")).sendKeys("Tampa");
	      
	      driver1.findElement(By.id("findTechJobs")).click();
	       String countOfPositions = driver1.findElement(By.id("posiCountId")).getText();
	       int count = Integer.parseInt(countOfPositions.replaceAll(",", ""));
	    		 System.out.println("For keyword :" + keyword + " found : " + count + " positions");
	    		 driver1.close();
	    		 
	      }
	      
	      
	      
	      
	      
	        System.out.println("Test complited - " + LocalDateTime.now());

	}

}
