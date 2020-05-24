/**
 * The WebUiActions class defines all basic and common utilities such as click, sendKeys, scroll etc.
 * which can further be used by multiple classes and test cases.
 *
 * @author  Riya Malhotra
 * @version 1.0
 * @since   2020-05-21
 */
package com.myshopify.ecom_optimus.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.myshopify.ecom_optimus.logManager.MyLogger;
import java.time.Duration;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WebUiActions {
	public static WebDriver driver;
	ExtentTest test;
	
	public WebUiActions(WebDriver driver, ExtentTest test) {
		WebUiActions.driver = driver;
		this.test=test;
		PageFactory.initElements(driver, this);
		MyLogger.log.info("WebUiActions Objects instance is created");
	}

	public WebElement waitToAppear(WebElement element) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
					.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
			return wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException toe) {
			MyLogger.log.info("No element was found within specified wait time " + element.toString());
			throw new Exception();
		}
	}

	public String getText(WebElement element) {
		try {
			return waitToAppear(element).getText();
		} catch (Exception e) {
			return null;
		}
	}

	public WebUiActions clearText(WebElement element) throws Exception {
		try {
			waitToAppear(element).clear();
		} catch (Exception e) {
			throw new Exception();
		}
		return this;
	}

	public WebUiActions typeText(WebElement element, String text) throws Exception {
		try {
			waitToAppear(element).sendKeys(text);
		} catch (Exception e) {
			throw new Exception();
		}
		return this;
	}

	public WebUiActions tap(WebElement element) throws Exception {
		MyLogger.log.info("Inside tap");
		try {
			waitToAppear(element).click();
		} catch (Exception e) {
			throw new Exception();
		}
		return this;
	}

	public WebUiActions scrollToElement(WebElement visibleElement) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", visibleElement);

		} catch (Exception e) {
			throw new Exception();
		}
		return this;
	}

	public static String getPropertiesFileData(String data) throws IOException {
		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "\\src\\resources\\java\\" + "testData.properties");
		Properties p = new Properties();
		p.load(reader);
		return p.getProperty(data);
	}
	
	public static String takeScreenshot(String fileName) throws Exception {
		String dest;
		try{
			fileName=fileName+".png";
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			dest=System.getProperty("user.dir")+"//ExtentReport//"+fileName;
			File tmp=new File(dest);
			if(tmp.exists()) {
				tmp.delete();
			}
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//ExtentReport//"+fileName));
			
		}
		 catch (Exception e) {
				throw new Exception();
			}
		return dest;
	}
	
	public void loggerPass(String imagePath, String message) throws IOException {
		test.addScreenCaptureFromPath(imagePath);
		test.log(Status.PASS, message);
		MyLogger.log.info(message);
	}
	
	public void loggerFail(String imagePath, String message) throws IOException {
		test.addScreenCaptureFromPath(imagePath);
		test.log(Status.FAIL, message);
		MyLogger.log.error(message);
	}
}
