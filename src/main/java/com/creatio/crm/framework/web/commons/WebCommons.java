package com.creatio.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.constants.Constants;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	
	//This class will contain all the common methods related to web application automation using Selenium.
	
	public WebDriver driver = BasePage.getDriver();
	public Properties prop = PropUtil.readData("config.properties");	
	
	//Common method to launch the application and verify the title of the application.
	public void launchApplication() {
		driver.get(prop.getProperty("APP_URL"));
		String actualTitle = driver.getTitle();
		if(!actualTitle.equals(prop.getProperty("APP_TITLE"))) {
			Assert.fail("Application did not launch successfully. Expected title: " + prop.getProperty("APP_TITLE") + ", but got: " + actualTitle);
		}			
	}
	
	//Common method to scroll to the element.
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	//Common method to click on an element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}
	
	//Common method to click on an element using JavaScript
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	//Common method to perform a double click on the element.
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	//Common method to perform right-click on the element.
	public void rightClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	//Common method to hover over an element.
	public void hoverOverElement(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	//Common method to enter text into an input field.
	public void enterText(WebElement element, String text) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(text);
	}
	
	//Common method to select the checkbox element.
	public void selectCheckbox(WebElement checkbox, boolean status) {
		scrollToElement(checkbox);
		if(checkbox.isSelected() != status) {
			checkbox.click();
		}
	}
	
	//Common method to select the option from the drop-down.
	public void selectDropdownOption(WebElement dropdown, String option, String selectBy) {
		Select select = new Select(dropdown);
		switch(selectBy.toLowerCase()) {
			case "value":
				select.selectByValue(option);
				break;
			case "visibletext":
				select.selectByVisibleText(option);
				break;
			case "index":
				select.selectByIndex(Integer.parseInt(option));
				break;
			default:
				Assert.fail("Invalid selection method: " + selectBy);
		}	
	}
	
	//Common method to get the text of an element.
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	//Common method to get the attribute value of an element.
	public String getElementAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	//Common method to get the current URL from the web page.
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	//Common method to refresh the web page.
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	//Common method to get the window handle ID.
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	
	//Common method to wait by using Java Thread.sleep()
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Common method to wait by using implicit wait.
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAIT_TIME));
	}
	
	// Common method to wait by using explicit wait.(wait for locator)
	public void waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}

	// Common method to wait by using explicit wait.(wait for element visibility)
	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Common method to wait by using explicit wait.(wait for element visibility)
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Common method to wait by using explicit wait.(wait for element visibility)
	public void waitForElement(WebElement element,int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//Common method to take screenshot of the complete page.
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png";
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(destination));
		return destination;
	}
	
	//Common method to take screenshot of a specific element.
	public static String getScreenshot(WebElement element, String screenshotName) throws IOException {
		String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + ".png";
		File screenshotFile = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(destination));
		return destination;
	}

	//Common method to switch to the alert and accept/dismiss it.
	public void handleAlert(boolean accept) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		if (accept) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}

	}
	
	//Common method to switch to a specific frame using its WebElement.
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	//Common method to switch to a specific frame using it frame ID or Name.
	public void switchToFrame(String frameIDOrName) {
		driver.switchTo().frame(frameIDOrName);
	}
	
	//Common method to switch back to the main window from a frame.
	public void switchToMainWindow() {
		driver.switchTo().defaultContent();
	}	
	
	
}
