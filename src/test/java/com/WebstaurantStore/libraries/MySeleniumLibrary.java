package com.WebstaurantStore.libraries;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class MySeleniumLibrary extends Base {

	final static Logger logger = Logger.getLogger(MySeleniumLibrary.class);

	private WebDriver driver;

	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
			logger.info("starting 'Chrome' browser.");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	public void enterText(By by, String inputText) {
		try {
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(inputText);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickButton(By by) {
		try {
			WebElement element = driver.findElement(by);
			element.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void assertionByText(By by, String textName) {
		try {
			List<WebElement> searchResults = driver.findElements(by);
			for (int i = 0; i < searchResults.size(); i++) {
				WebElement elem = searchResults.get(i);
				String actualText = elem.getText();
				Assert.assertTrue(actualText.contains(textName));
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void verifyingTextOnTheSearchResults() {
		try {
			for (int i = 0; i < 8; i++) {
				myLib.clickButton(By.cssSelector("#paging > nav > ul > li.rc-pagination-next > a > svg"));

				myLib.customWait(1);

				myLib.assertionByText(By.xpath("//a[@data-testid='itemDescription']"), "Table");

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void clickingOnThePageByIndex(int pageNum) {
		try {
			List<WebElement> allItems = driver.findElements(By.xpath("//*[@id='paging']/nav/ul/li"));
			for (int i = 0; i < allItems.size(); i++) {

				WebElement elem = allItems.get(i);
				if (i == pageNum) {
					elem.click();
				}

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public void selectingTheLastItemOnTheLastPage(int itemNum) {
		try {
			List<WebElement> allItems = driver.findElements(By.xpath("//input[@name='addToCartButton']"));
			for (int i = 0; i < allItems.size(); i++) {

				WebElement elem = allItems.get(i);
				if (i == itemNum) {
					elem.click();
				}

			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

}
