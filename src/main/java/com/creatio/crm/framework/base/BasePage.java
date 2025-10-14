package com.creatio.crm.framework.base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.creatio.crm.framework.utilities.PropUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BasePage {
	
	//This class will have all the common methods and configurations to begin the test execution 
	//(like browser setup and teardown) and additional common methods related to that.
	
	private static WebDriver driver;
	
	//Common method to launch a browser.
	@Before
	public void setupBrowser() {
		String browserName = PropUtil.readData("Config.properties").getProperty("BROWSER");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver =new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver =new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver =new EdgeDriver();
		} else {
			Assert.fail("Browser not supported");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	// common method to tear down the driver.
	@After(order=0)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// common method to tear down the driver.
	@After(order=1)
	public void failTestListener(Scenario scenario) {
		if(scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
	
	//Common method to share the webdriver instance with other classes.
	public static WebDriver getDriver() {
		return driver;
	}
	
	//Common method to update the web driver instance from other classes.
	public static void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

}
