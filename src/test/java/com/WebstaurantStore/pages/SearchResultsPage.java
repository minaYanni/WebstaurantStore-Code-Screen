package com.WebstaurantStore.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.WebstaurantStore.libraries.Base;

public class SearchResultsPage extends Base {
	final static Logger logger = Logger.getLogger(HomePage.class);

	public SearchResultsPage() {

	}

	public void clickingOnTheLastSearchPage() {

		List<WebElement> pageBar = driver.findElements(By.xpath("//ul[@unselectable='unselectable']"));
		for(int i = 2; i < pageBar.size();i++){
		WebElement elem = pageBar.get(i);
		elem.findElement(By.xpath("//a[@aria-label='Page "+ i +"']")).click();	
		myLib.assertionByText(By.xpath("//a[@data-testid='itemDescription']"), "Table");
		}
	}

	public SearchResultsPage verifyingSearchResults(String textName) {

		logger.info("Veifying that Search results for every product item has the word Table");
		myLib.assertionByText(By.xpath("//a[@data-testid='itemDescription']"), textName);

		return this;

	}
}
