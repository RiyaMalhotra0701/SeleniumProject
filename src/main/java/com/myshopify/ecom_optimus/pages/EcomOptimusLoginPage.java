/**
 * The EcomOptimusLoginPage class extends BasePage(WebUIActions) class and uses basic functionality and common utilities on the elements of this page. This class initialize and implements
 * the elements of Ecom Optimus -- Login Page.
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

public class EcomOptimusLoginPage extends WebUiActions {
	
	public EcomOptimusLoginPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		MyLogger.log.info("EcomOptimusLoginPage Objects are created");
	}
	
	//Password button
	@FindBy(xpath = "/html/body/div[1]/header/div/div/a")
	private WebElement passwordButton;
	
	//Password button
	@FindBy(xpath = "//a[contains(text(),'Enter using password')]")
	private WebElement passwordBtn;
		
	//Password input field
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordInputField;

	//Enter button
	@FindBy(xpath = "//button[contains(text(),'Enter')]")
	private WebElement enterBtn;
	
	/*
	 * tapPasswordBtn function taps on Password Btn
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusLoginPage tapPasswordBtn() throws Exception {
		try {
			tap(passwordBtn);
			String path=takeScreenshot("//ActionScreenshots//tapPasswordBtn");
			loggerPass(path,"Tapped on Password button");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapPasswordBtn");
			loggerFail(path,"Unable to tap on Password button");
			Assert.fail();
		}
		return this;
	}
	
	/*
	 * enterPassword function enters the password
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusLoginPage enterPassword() throws Exception {
		try {
			String pwd=getPropertiesFileData("password");
			typeText(passwordInputField,pwd);
			String path=takeScreenshot("//ActionScreenshots//enterPassword");
			loggerPass(path,"Entered password");
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//enterPassword");
			loggerFail(path,"Unable to enter Password");
			Assert.fail();
		}
		return this;
	}
	
	/*
	 * tapEnterButton function taps on Enter Btn
	 *
	 * @author Riya Malhotra
	 */
	public EcomOptimusLoginPage tapEnterButton() throws Exception {
		try {
			Thread.sleep(3000);		
		    tap(enterBtn);
		    String path=takeScreenshot("//ActionScreenshots//tapEnterButton");
			loggerPass(path,"Tapped on enter button");	   
		} catch (Exception e) {
			String path=takeScreenshot("//FailedScreenshots//tapEnterButton");
			loggerFail(path,"Unable to tap on enter button");
			Assert.fail();
		}
		return this;
	}
}
