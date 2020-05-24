/**
 * The EcomOptimusHomePage class extends BasePage(WebUIActions) class and uses basic functionality and common utilities on the elements of this page. This class initialize and implements
 * the elements of Ecom Optimus -- Home Page.
 *
 * @author  Riya Malhotra
 * @version 1.0
 * @since   2020-05-21
 */
package com.myshopify.ecom_optimus.pages;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.myshopify.ecom_optimus.logManager.MyLogger;
import com.myshopify.ecom_optimus.utils.WebUiActions;

public class EcomOptimusHomePage extends WebUiActions {
	
	public EcomOptimusHomePage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		MyLogger.log.info("EcomOptimusHomePage Objects are created");
	}

	// Search Icon
	@FindBy(xpath = "//*[@id=\"shopify-section-header\"]/div[2]/header/div/div[2]/div/button[1]")
	private WebElement searchIcon;

	// Search Bar
	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchBar;

	// List of searched item results
	@FindBy(className = "list-view-item")
	private List<WebElement> itemsList;

	// Add to cart button
	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	private WebElement addToCartButton;

	// popup heading
	@FindBy(xpath = "//h2[@id='CartPopupHeading']")
	private WebElement popupHeading;

	// close icon
	@FindBy(xpath = "//button[@class='cart-popup__close']")
	private WebElement closeIcon;

	// Cart Icon
	@FindBy(xpath = "//div[@id='CartCount']")
	private WebElement cartIcon;

	// Featured Collection
	@FindBy(xpath = "//h2[contains(text(),'Featured collection')]")
	private WebElement featuredCollection;

	// List of items in featured collection
	@FindBy(xpath = "//a[@href='/collections/frontpage/products/shirt-14']")
	private WebElement featuredItem;

	// Size--Drop down list
	@FindBy(xpath = "//select[@id='SingleOptionSelector-1']")
	private WebElement dropDownList;

	// size value on popup which shows size of the product added to the cart
	@FindBy(xpath = "//li[contains(text(),'Size:')]")
	private WebElement sizeValue;

	
	/*
	 * tapCartIcon function taps on Cart Icon
	 *
	 * @author Riya Malhotra
	 */
	
	public EcomOptimusHomePage tapCartIcon() throws Exception{
		try {
			tap(cartIcon);
			String path=takeScreenshot("//ActionScreenshots//tapCartIcon");
			loggerPass(path,"Tapped on Cart Icon");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapCartIcon");
			loggerFail(path,"Unable to tap on Cart Icon");
			Assert.fail();
		}
		return this;
	}

	/*
	 * tapSearchIcon function taps on Search Icon
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage tapSearchIcon() throws Exception {
		try {
			tap(searchIcon);
			String path=takeScreenshot("//ActionScreenshots//tapSearchIcon");
			loggerPass(path,"Tapped on Search Icon");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapSearchIcon");
			loggerFail(path,"Unable to tap on Search Icon");
			Assert.fail();
		}
		return this;
	}

	/*
	 * searchProduct function enters product in Search Bar
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage searchProduct() throws Exception {
		try {
			typeText(searchBar, "shirt");
			searchBar.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			String path=takeScreenshot("//ActionScreenshots//searchProduct");
			loggerPass(path,"Entered product");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//searchProduct");
			loggerFail(path,"Unable to enter product");
			Assert.fail();
		}
		return this;
	}

	/*
	 * selectItem function selects desired product
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage selectItem(int index) throws Exception {
		try {
			tap(itemsList.get(index));
			String path=takeScreenshot("//ActionScreenshots//selectItem");
			loggerPass(path,"Selected desired product");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//selectItem");
			loggerFail(path,"Unable to select product");
			Assert.fail();
		}
		return this;
	}

	/*
	 * tapAddToCartButton function taps on add to cart button
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage tapAddToCartButton() throws Exception {
		try {
			tap(addToCartButton);
			String path=takeScreenshot("//ActionScreenshots//tapAddToCartButton");
			loggerPass(path,"Tapped on add to cart button");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapAddToCartButton");
			loggerFail(path,"Unable to click on add to cart button");
			Assert.fail();
		}
		return this;
	}

	/*
	 * getPopupHeading function gets popup heading text
	 *
	 * @author Riya Malhotra
	 */
	public String getPopupHeading() throws Exception {
		String heading = waitToAppear(popupHeading).getText();
		return heading;
	}

	/*
	 * getSizeOnPopup function gets size of the product added to the cart.
	 *
	 * @author Riya Malhotra
	 */
	public String getSizeOnPopup() throws Exception {
		String heading = waitToAppear(sizeValue).getText();
		String size[] = heading.split(" ");
		String productSize = size[1];
		return productSize;
	}

	/*
	 * tapCloseIcon function taps on Close Icon
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage tapCloseIcon() throws Exception {
		try {
			tap(closeIcon);
			String path=takeScreenshot("//ActionScreenshots//tapCloseIcon");
			loggerPass(path,"Tapped on close icon");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapCloseIcon");
			loggerFail(path,"Unable to tap on close icon");
			Assert.fail();
		}
		return this;
	}

	/*
	 * scrollToFeaturedCollection function scrolls to Featured Collection
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage scrollToFeaturedCollection() throws Exception {
		try {
			scrollToElement(featuredCollection);
			Thread.sleep(2000);
			String path=takeScreenshot("//ActionScreenshots//scrollToFeaturedCollection");
			loggerPass(path,"Scrolled to featured collection");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//scrollToFeaturedCollection");
			loggerFail(path,"Unable to scroll to featured collection");
			Assert.fail();
		}
		return this;
	}

	/*
	 * selectFeaturedItem function selects desired product from featured collection
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage selectFeaturedItem() throws Exception {
		try {
			tap(featuredItem);
			String path=takeScreenshot("//ActionScreenshots//selectFeaturedItem");
			loggerPass(path,"Selected item from featured collection");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//selectFeaturedItem");
			loggerFail(path,"Unable to select item from featured collection");
			Assert.fail();
		}
		return this;
	}

	/*
	 * selectSizeFromDropDown function selects desired size from drop down list.
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusHomePage selectSizeFromDropDown(String size) throws Exception {
		try {
			Select s = new Select(dropDownList);
			s.selectByVisibleText(size);
			Thread.sleep(1000);
			String path=takeScreenshot("//ActionScreenshots//selectSizeFromDropDown");
			loggerPass(path,"Selected size: " + size);
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//selectSizeFromDropDown");
			loggerFail(path,"Unable to select size");
			Assert.fail();
		}
		return this;
	}

}
