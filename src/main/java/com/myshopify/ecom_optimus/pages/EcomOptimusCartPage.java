/**
 * The EcomOptimusCartPage class extends BasePage(WebUIActions) class and uses basic functionality and common utilities on the elements of this page. This class initialize and implements
 * the elements of Ecom Optimus -- Cart Page.
 *
 * @author  Riya Malhotra
 * @version 1.0
 * @since   2020-05-21
 */
package com.myshopify.ecom_optimus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.myshopify.ecom_optimus.logManager.MyLogger;
import com.myshopify.ecom_optimus.utils.WebUiActions;

public class EcomOptimusCartPage extends WebUiActions {

	public EcomOptimusCartPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		MyLogger.log.info("EcomOptimusCartPage Objects are created");
	}

	// Quantity
	@FindBy(xpath = "//input[@name='updates[]']")
	private WebElement quantity;

	// Base price for 1 item
	@FindBy(xpath = "//*[@id='shopify-section-cart-template']/div/div[1]/form/table/tbody/tr/td[2]/div[1]/dl/div/dd")
	private WebElement basePrice;
	
	// Total price
	@FindBy(xpath = "//span[@class='cart-subtotal__price']")
	private WebElement totalPrice;

	/*
	 * increaseQuantity function taps on Quantity Icon to increase it
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusCartPage increaseQuantity() throws Exception {
		try {
			String x = waitToAppear(quantity).getAttribute("value");
			int val = Integer.parseInt(x);
			quantity.clear();
			Integer val1 = val + 1;
			typeText(quantity, val1.toString());
			String path=takeScreenshot("//ActionScreenshots//increaseQuantity");
			loggerPass(path,"Increased quantity");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//increaseQuantity");
			loggerFail(path,"Unable to increase quantity");
			Assert.fail();
		}
		return this;
	}

	/*
	 * getBasePrice function gets the base price of 1 item
	 * 
	 *
	 * @author Riya Malhotra
	 */
	public Double getBasePrice() throws Exception {
		try {
			Thread.sleep(2000);
			String text = waitToAppear(basePrice).getText();
			String words[] = text.split(" ");
			String words1[] = words[1].split(",");
			String base = words1[0] + words1[1];
			Double basePrice = Double.parseDouble(base);
			String path=takeScreenshot("//ActionScreenshots//getBasePrice");
			loggerPass(path,"Fetched base price: ");
			System.out.println(basePrice);
			return basePrice;
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//getBasePrice");
			loggerFail(path,"Unable to fetch base price");
			Assert.fail();
			return null;
		}
	}

	/*
	 * getTotalPrice function gets the total price
	 * 
	 *
	 * @author Riya Malhotra
	 */
	public Double getTotalPrice() throws Exception {
		try {
			Thread.sleep(2000);
			String text = waitToAppear(totalPrice).getText();
			String words[] = text.split(" ");
			String words1[] = words[1].split(",");
			String total = words1[0] + words1[1];
			Double subTotal = Double.parseDouble(total);
			String path=takeScreenshot("//ActionScreenshots//getTotalPrice");
			loggerPass(path,"Fetched Total price: ");
			System.out.println(subTotal);
			return subTotal;
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//getTotalPrice");
			loggerFail(path,"Unable to fetch total price");
			Assert.fail();
			return null;
		}
	}
}
