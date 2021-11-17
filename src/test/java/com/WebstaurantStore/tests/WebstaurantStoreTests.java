package com.WebstaurantStore.tests;

import org.testng.annotations.Test;

import com.WebstaurantStore.libraries.Base;
import com.WebstaurantStore.pages.HomePage;
import com.WebstaurantStore.pages.SearchResultsPage;

public class WebstaurantStoreTests extends Base {

	@Test
	public void searchForStainlessWorkTable() {
		
		HomePage myHP = new HomePage();
		myHP.searchForStainlessWorkTable("Stainless Work Table")
		.clickingOnSearchButton();
		
		SearchResultsPage mySearchResults = new SearchResultsPage();
		mySearchResults.clickingOnTheLastSearchPage();
		mySearchResults.verifyingSearchResults("Table");
		
	}

}
