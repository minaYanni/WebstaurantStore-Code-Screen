package com.WebstaurantStore.libraries;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import com.WebstaurantStore.libraries.MySeleniumLibrary.Browser;

public class Base {
	final static Logger logger = Logger.getLogger(Base.class);

	public static MySeleniumLibrary myLib;
	public static WebDriver driver;
	private String url = "https://www.webstaurantstore.com/";

	@BeforeMethod
	public void setUp() {
		myLib = new MySeleniumLibrary();
		driver = myLib.startBrowser(Browser.CHROME);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String websiteTitle = driver.getTitle();
		logger.info("website title is: " + websiteTitle);
		String expectedTitle = "WebstaurantStore: Restaurant Supplies & Foodservice Equipment";
		Assert.assertEquals(websiteTitle, expectedTitle);
	}

	@AfterMethod
	public void closingBrowsers() {
		logger.info("After Method....");
		myLib.customWait(5);

		if (driver != null) {
			driver.close();
		}

	}

	@AfterClass
	public void cleanUpProcess() {
		logger.info("After Class....");

		if (driver != null) {
			driver.quit();
		}
	}

}
