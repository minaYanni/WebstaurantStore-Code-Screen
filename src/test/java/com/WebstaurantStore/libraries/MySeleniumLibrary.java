package com.WebstaurantStore.libraries;

import static org.testng.Assert.assertTrue;

import java.io.File;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;

public class MySeleniumLibrary {

	final static Logger logger = Logger.getLogger(MySeleniumLibrary.class);

	public enum Browser {
		FIREFOX, CHROME, SAFARI, EDGE_CHROMIUM
	}

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver _driver) {
		if (_driver != null) {
			this.driver = _driver;
		}
	}

	private int defaultWaitTimeInSecs = 30;

	public MySeleniumLibrary() {
	}

	public MySeleniumLibrary(WebDriver _driver) {
		driver = _driver;
	}

	public WebDriver startBrowser(Browser browser) {
		switch (browser) {
		case EDGE_CHROMIUM:
			driver = startEdgeChromiumBrowser();
			break;
		case CHROME:
			driver = startChromeBrowser();
			break;
		case SAFARI:
			driver = startSafariBrowser();
			break;
		case FIREFOX:
			driver = startFirefoxBrowser();
			break;
		default:

			logger.error("Currently we are not supporting this type of browser !");
			logger.error("Default browser set to Chrome");
			driver = startChromeBrowser();
			break;
		}
		return driver;
	}

	private WebDriver startEdgeChromiumBrowser() {
		logger.info("This method is not implemented yet! Start EdgeChromium");
		return driver;
	}

	private WebDriver startFirefoxBrowser() {
		logger.info("This method is not implemented yet! Start Firefox");
		return driver;
	}

	private WebDriver startSafariBrowser() {
		logger.info("This method is not implemented yet! Start Safari");
		return driver;
	}

	private WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
			logger.info("starting 'Chrome' browser.");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			setWebsiteWaits();

		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return driver;
	}

	private void setWebsiteWaits() {
		try {
			driver.manage().timeouts().implicitlyWait(defaultWaitTimeInSecs, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(defaultWaitTimeInSecs, TimeUnit.SECONDS);
			logger.info("Maximaizing the Browser");
			driver.manage().window().maximize();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
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

	public void selectDropDown(By by, String visibleTextOption) {
		try {
			Select tempSelect = selectDropDown(by);
			tempSelect.selectByVisibleText(visibleTextOption);
		} catch (Exception e) {
			logger.error("Error: ", e);

		}
	}

	public void selectDropDown2(By by, String valueOption) {
		try {
			Select tempSelect2 = selectDropDown(by);
			tempSelect2.selectByValue(valueOption);
		} catch (Exception e) {
			logger.error("Error: ", e);

		}
	}

	public Select selectDropDown(By by) {
		Select selectDropdown = null;
		try {
			WebElement element = driver.findElement(by);
			selectDropdown = new Select(element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return selectDropdown;
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

	public void clickButton(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void clickHiddenElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public WebElement dynamicWaitForVisibilityOfElement(By by) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, defaultWaitTimeInSecs);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return element;
	}

	public void moveToElement(By by) {
		try {
			WebElement element = driver.findElement(by);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			customWait(0.5);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void scrollUpDown(int pixels) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + pixels + ")");
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public String getCurrentTime() {
		String finalTime = null;
		try {
			Date date = new Date();
			logger.debug("time1: " + date.toString());
			String tempTime = new Timestamp(date.getTime()).toString();
			logger.debug("time2: " + tempTime);
			finalTime = tempTime.replace("-", "").replace(" ", "").replace(":", "").replace(".", "");
			logger.debug("time3: " + finalTime);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		return finalTime;
	}

	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
	}

	public void dragAndDrop(By by, int xOffset, int yOffset) {
		try {
			WebElement element = driver.findElement(by);
			Actions action = new Actions(driver);
			action.dragAndDropBy(element, xOffset, yOffset).perform();
			customWait(0.5);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
	}

	public void dragAndDrop(By fromElemet1, By toElement2) {
		try {
			Actions action = new Actions(driver);
			WebElement element1 = driver.findElement(fromElemet1);
			WebElement element2 = driver.findElement(toElement2);
			action.dragAndDrop(element1, element2).perform();
			customWait(0.5);
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(true);
		}
	}

	public void assertionByText(By by, String textName) {
		List<WebElement> searchResults = driver.findElements(by);
		for (int i = 0; i < searchResults.size(); i++) {
			WebElement elem = searchResults.get(i);
			String actualText = elem.getText();
			Assert.assertTrue(actualText.contains(textName));
		}
		int itemCount = searchResults.size();
		logger.info("Last Page Results " + itemCount);
		
		// //ul[@unselectable='unselectable']
		//*[@id="paging"]/nav/ul
		
		
		// //*[@id="paging"]/nav/ul/li[8]
		//*[@id="paging"]/nav/ul/li[8]/a

	}

	public String captureScreenshot() {
		String screenshotFilePath = null;
		try {
			screenshotFilePath = "C:/AutomationPractice bugs Screenshot/screenshot_" + getCurrentTime() + ".png";
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(screenshotFilePath));
		} catch (Exception e) {
			logger.error("Error: ", e);
			assertTrue(false);
		}
		logger.info("Screenshot File: " + screenshotFilePath);
		return screenshotFilePath;
	}

}
