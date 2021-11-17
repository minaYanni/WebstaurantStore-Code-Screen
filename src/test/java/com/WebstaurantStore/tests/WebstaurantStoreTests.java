package com.WebstaurantStore.tests;

import org.testng.annotations.Test;

import com.WebstaurantStore.libraries.Base;
import com.WebstaurantStore.pages.CartPage;
import com.WebstaurantStore.pages.HomePage;
import com.WebstaurantStore.pages.SearchResultsPage;

public class WebstaurantStoreTests extends Base {

	@Test(enabled = true, priority = 1)
	public void searchForStainlessWorkTableFailedTestCase() {

		HomePage myHP = new HomePage();
		myHP.searchForStainlessWorkTable("Stainless Work Table").clickingOnSearchButton();

		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.ensuringProductItemHasTableWord();

	}

	@Test(enabled = true, priority = 2)
	public void addTheLastItemFound() {
		HomePage myHP = new HomePage();
		myHP.searchForStainlessWorkTable("Stainless Work Table").clickingOnSearchButton();

		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.clickingOnTheLastFoundItemsOnTheLastPage()
	    .addTheLastItemToTheCart()
	    .clickingViewCart();

		CartPage cartPage = new CartPage();
		cartPage.emptyCart().verifyEmptyCartText();
	}

}
