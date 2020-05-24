package com.myshopify.ecom_optimus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentTest;
import com.myshopify.ecom_optimus.logManager.MyLogger;

public class EcomOptimusApplication {

	public WebDriver driver;
	public EcomOptimusLoginPage ecomOptimusLoginPage;
	public EcomOptimusHomePage ecomOptimusHomePage;
	public EcomOptimusCartPage ecomOptimusCartPage;

	public EcomOptimusApplication(String browserOrDevice, String path) throws Exception {

		if (browserOrDevice.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
			driver.get("http://ecom-optimus.myshopify.com");
		} else if (browserOrDevice.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", path);
			driver = new FirefoxDriver();
			driver.get("http://ecom-optimus.myshopify.com");
		}
	}

	public void initializeExtentTest(ExtentTest test) {

		ecomOptimusLoginPage = new EcomOptimusLoginPage(driver, test);
		MyLogger.log.info("EcomOptimusLoginPage instance is created");
		ecomOptimusHomePage = new EcomOptimusHomePage(driver, test);
		MyLogger.log.info("EcomOptimusHomePage instance is created");
		ecomOptimusCartPage = new EcomOptimusCartPage(driver, test);
		MyLogger.log.info("EcomOptimusCartPage instance is created");
	}
}
