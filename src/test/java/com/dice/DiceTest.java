package com.dice;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceTest {

	public static void main(String[] args) {
		
       WebDriverManager.chromedriver().setup();
       //invoke selenium webdriver
        WebDriver driver = new ChromeDriver();
        //maximize the window
        driver.manage().window().fullscreen();
        
        //set universal wait time in case web page is slow
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        
        //step 1: navigate to dice.com
       
        
        //Expected : dice home page displayed
        driver.get("https://www.dice.com");
        
        String expectedTitle = "Job Search for Technology Professionals | Dice.com";
        String actualTitle =   driver.getTitle();
        if(expectedTitle.equalsIgnoreCase(actualTitle)) {
        	System.out.println("Title test passed|| dice page successfully loaded ");
        } else {
        	System.out.println("Fail || page did not load successfully");
        	throw new RuntimeException("Step fail");
        }
        
        
        //go to box job title . Clear and type job 
        String position = "automation tester";
        driver.findElement(By.id("search-field-keyword")).clear();
        driver.findElement(By.id("search-field-keyword")).sendKeys(position);
        //go to box location . Clear field and type location
        String location = "Tampa";
        driver.findElement(By.id("search-field-location")).clear();
        driver.findElement(By.id("search-field-location")).sendKeys(location);
        
        driver.findElement(By.id("findTechJobs")).click();
       
       String count = driver.findElement(By.id("posiCountId")).getText();
       int countResult = Integer.parseInt(count.replaceAll(",", ""));
       if(countResult > 0) {
    	   System.out.println("Step PASS || position :" + position + " search returned " + countResult + " in " +
       location);
       } else {
    	   System.out.println("Step FAILED");
    	   
       }
       driver.close();
        System.out.println(count);
       
       
	}

}
