/**
 * The SearchAndAddToCart class specifies below test cases:
 * 1) Search for item,add to cart and validate if product is successfully added to the cart
 * 2) Increase the product quantity and validate the price
 * 3) Add product From featured collection and validate if product is successfully added to the cart
 * 4) Add Product with multiple sizes and validate if different size products are added
 * 
 * @author  Riya Malhotra
 * @version 1.0
 * @since   2020-05-21
 */
package com.myshopify.ecom_optimus.tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.myshopify.ecom_optimus.pages.EcomOptimusApplication;
import com.myshopify.ecom_optimus.utils.WebUiActions;

public class SearchAndAddToCart {

	EcomOptimusApplication ecomOptimus;
	ExtentTest test;
	ExtentReports extent;

	@BeforeClass
	public void createExtentReport() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/ExtentReport/extent" + timeStamp + ".html"));

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Parameters("browserOrDevice")
	@BeforeMethod
	public void initializeDriver(String browserOrDevice) throws Exception {
		if (browserOrDevice.equalsIgnoreCase("Chrome")) {
			String path = WebUiActions.getPropertiesFileData("chromeDriverPath");
			ecomOptimus = new EcomOptimusApplication(browserOrDevice, path);
		} else if (browserOrDevice.equalsIgnoreCase("Firefox")) {
			String path = WebUiActions.getPropertiesFileData("geckoDriverPath");
			ecomOptimus = new EcomOptimusApplication(browserOrDevice, path);
		}

	}

	@Test(priority = 1, description = "Search for item,add to cart and validate if product is successfully added to the cart")
	public void searchProductAndAddToCart() throws Exception {
		test = extent.createTest("searchProductAndAddToCart");
		ecomOptimus.initializeExtentTest(test);
		ecomOptimus.ecomOptimusLoginPage.tapPasswordBtn().enterPassword().tapEnterButton();
		ecomOptimus.ecomOptimusHomePage.tapSearchIcon().searchProduct().selectItem(1).tapAddToCartButton();
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getPopupHeading(), "JUST ADDED TO YOUR CART");
		ecomOptimus.ecomOptimusHomePage.tapCloseIcon();
	}

	@Test(priority = 2, description = "Increase the product quantity and validate the price")
	public void increaseProductQuantityAndValidatePrice() throws Exception {
		test = extent.createTest("increaseProductQuantityAndValidatePrice");
		ecomOptimus.initializeExtentTest(test);
		ecomOptimus.ecomOptimusLoginPage.tapPasswordBtn().enterPassword().tapEnterButton();
		ecomOptimus.ecomOptimusHomePage.tapSearchIcon().searchProduct().selectItem(1).tapAddToCartButton()
				.tapCloseIcon().tapCartIcon();
		ecomOptimus.ecomOptimusCartPage.increaseQuantity();
		Double basePrice = ecomOptimus.ecomOptimusCartPage.getBasePrice();
		Double totalPrice = ecomOptimus.ecomOptimusCartPage.getTotalPrice();
		Assert.assertEquals(basePrice * 2, totalPrice);
	}

	@Test(priority = 3, description = "Add product From featured collection and validate if product is successfully added to the cart")
	public void addProductFromFeaturedCollection() throws Exception {
		test = extent.createTest("addProductFromFeaturedCollection");
		ecomOptimus.initializeExtentTest(test);
		ecomOptimus.ecomOptimusLoginPage.tapPasswordBtn().enterPassword().tapEnterButton();
		ecomOptimus.ecomOptimusHomePage.scrollToFeaturedCollection().selectFeaturedItem().tapAddToCartButton();
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getPopupHeading(), "JUST ADDED TO YOUR CART");
	}

	@Test(priority = 4, description = "Add Product with multiple sizes and validate if different size products are successfully added to the cart")
	public void addProductsWithMultipleSizes() throws Exception {
		test = extent.createTest("addProductsWithMultipleSizes");
		ecomOptimus.initializeExtentTest(test);
		ecomOptimus.ecomOptimusLoginPage.tapPasswordBtn().enterPassword().tapEnterButton();
		ecomOptimus.ecomOptimusHomePage.tapSearchIcon().searchProduct().selectItem(0).selectSizeFromDropDown("S")
				.tapAddToCartButton();
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getPopupHeading(), "JUST ADDED TO YOUR CART");
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getSizeOnPopup(), "S");
		ecomOptimus.ecomOptimusHomePage.selectSizeFromDropDown("L").tapAddToCartButton();
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getPopupHeading(), "JUST ADDED TO YOUR CART");
		Assert.assertEquals(ecomOptimus.ecomOptimusHomePage.getSizeOnPopup(), "L");
	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws Exception {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = WebUiActions.takeScreenshot("//FailedTestCase//" + testResult.getName());
			test.addScreenCaptureFromPath(path);
			test.info("Test case: " + testResult.getName() + " has failed");
		}
		ecomOptimus.driver.quit();
	}

	@AfterClass
	public void flush() {
		extent.flush();
	}

}
