package com.WebstaurantStore.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import com.WebstaurantStore.libraries.Base;

public class CartPage extends Base {
	final static Logger logger = Logger.getLogger(CartPage.class);

	public CartPage() {

	}

	public CartPage emptyCart() {
		logger.info("Clicking on the Empty Cart Button");
		myLib.clickButton(By.xpath("//a[text()='Empty Cart']"));
		logger.info("Waiting for the cart to be empty");
		myLib.customWait(2);
		logger.info("Clicking on the Empty Cart Button on the POP-UP");
		myLib.clickButton(By.xpath("//button[text()='Empty Cart']"));
		return this;

	}

	public CartPage verifyEmptyCartText() {
		myLib.customWait(3);
		logger.info("Waiting for the Cart Empty Text to be visible");
		logger.info("Verifying that the cart is actully empty");
		String emptyCartText = driver.findElement(By.xpath("//p[text()='Your cart is empty.']")).getText();
		String expectedText = "Your cart is empty.";
		Assert.assertEquals(expectedText, emptyCartText);
		logger.info("The text is : " + emptyCartText);

		return this;
	}
}
