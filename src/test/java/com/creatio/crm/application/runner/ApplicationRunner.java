package com.creatio.crm.application.runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="ApplicationFeatures",
		glue= {"com.creatio.crm.application.stepDefinitions","com.creatio.crm.framework.base"},
		plugin = {"pretty","html:Reports/AutomationReport.html"}
		
		)



public class ApplicationRunner extends AbstractTestNGCucumberTests{
	
	@Test
	public void runApplicationTests() {
		System.out.println("Execution started for application test scenarios...");
	}

}



