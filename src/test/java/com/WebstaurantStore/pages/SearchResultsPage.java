package com.WebstaurantStore.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import com.WebstaurantStore.libraries.Base;

public class SearchResultsPage extends Base {
	final static Logger logger = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {

	}

	public SearchResultsPage ensuringProductItemHasTableWord() {
		logger.info("Verifying that the items on the First Search page has the word 'Table' in it's feild");
		myLib.assertionByText(By.xpath("//a[@data-testid='itemDescription']"), "Table");
		logger.info("Verifying  that the items on the rest of the Search pages has the word 'Table' in it's feild");
		myLib.verifyingTextOnTheSearchResults();

		return this;
	}
	
	// Quick note: Pages 8 and 9 of the search results has some items that don't have the word 'Table'
   // in its field therefore, the test has failed due to the assertion.
	// Items number that don't have the word 'Table' in page 8 are(#522SG3024, #522SG3036)
	// Items number that don't have the word 'Table' in page 9 are (#522SG3048, #522SG3060, #522SG3072, #46148FSFSPK2, #600ES48KIT)
	
	public SearchResultsPage clickingOnTheLastFoundItemsOnTheLastPage() {
		logger.info("Waiting for 3 seconds for the Last Page to load");
		myLib.customWait(3);
		logger.info("Clicking on the last page of the search");
		myLib.clickingOnThePageByIndex(7);
		return this;
	}

	public SearchResultsPage addTheLastItemToTheCart() {
		logger.info("Waiting for 3 seconds for the Last item to load");
		myLib.customWait(3);
		logger.info("Adding the Last item on the search results to the cart");
		myLib.selectingTheLastItemOnTheLastPage(59);

		return this;
	}

	public CartPage clickingViewCart() {
		logger.info("Clicking on View Cart");
		myLib.clickButton(By.cssSelector("a.btn.btn-small.btn-primary"));
		logger.info("Navigating to Cart Page");
		return new CartPage();
	}

}
