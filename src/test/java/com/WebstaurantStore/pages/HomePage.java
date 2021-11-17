package com.WebstaurantStore.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.WebstaurantStore.libraries.Base;

public class HomePage extends Base {
	final static Logger logger = Logger.getLogger(HomePage.class);
	
	public HomePage() {
		
	}
	
	public HomePage searchForStainlessWorkTable(String inputMerchandise) {
		
		logger.info("Searching for Stainless Work Table");
		myLib.enterText(By.id("searchval"),inputMerchandise );
		
		return this;
		
	}
	
	
	public SearchResultsPage clickingOnSearchButton() {
		logger.info("Clicking On the Search Button");
		myLib.clickButton(By.cssSelector("#searchForm > div > button"));
		
		return new SearchResultsPage();
		
	}
	
	
}
