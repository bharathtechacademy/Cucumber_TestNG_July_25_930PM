package com.creatio.crm.framework.api.commons;

import static io.restassured.RestAssured.given;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.creatio.crm.framework.utilities.PropUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APICommons {
	
	//This class will contain all the common methods to automate web services in terms of functionality.
	
	public static Response response = null;
	static Properties prop = PropUtil.readData("Config.properties");
	
	//Common method to get the response by providing requestType, endpoint, and requestBody.
	public static void getResponse(String requestType, String endpoint, String requestBody) {
		RestAssured.baseURI = prop.getProperty("BASE_URL");
		String token = prop.getProperty("TOKEN");		
		
		switch (requestType.toUpperCase()) {
			case "GET":
				response = given().headers("Authorization",token).headers("Accept","application/vnd.github+json").when().get(endpoint);
				break;
			case "POST":
				response = given().headers("Authorization",token).headers("Accept","application/vnd.github+json").body(requestBody).when().post(endpoint);
				break;
			case "PUT":
				response = given().headers("Authorization",token).headers("Accept","application/vnd.github+json").body(requestBody).when().put(endpoint);
				break;
			case "PATCH":
				response = given().headers("Authorization",token).headers("Accept","application/vnd.github+json").body(requestBody).when().patch(endpoint);
				break;
			case "DELETE":
				response = given().headers("Authorization",token).headers("Accept","application/vnd.github+json").when().delete(endpoint);
				break;
			default:
				throw new IllegalArgumentException("Invalid request type: " + requestType);
		}		
	}
	
	//Common method to validate the status code of the response.
	public static void validateResponseCode(int expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode, "Status code does not match!");
	}
	
	//Common method to validate the status line message of the response.
	public static void validateResponseMessage(String expectedStatusMessage) {
		String actualStatusLine = response.getStatusLine();
		Assert.assertTrue(actualStatusLine.contains(expectedStatusMessage), "Status message does not match!");
	}
	
	//Common method to validate the response time.
	public static void validateResponseTime(long expectedResponseTimeInSeconds) {
		long actualResponseTime = response.getTimeIn(TimeUnit.SECONDS);
		Assert.assertTrue(actualResponseTime <= expectedResponseTimeInSeconds, "Response time exceeds the expected limit!");
	}
	
	//Common method to validate the response body. Keys and values.
	public static void validateResponseBody(String key, String expectedValue) {
		String actualValue = response.jsonPath().getString(key);
		Assert.assertEquals(expectedValue, actualValue, "Response body value does not match for key: " + key);
	}
	
	//Common method to validate the response header Keys and values.
	public static void validateResponseHeader(String headerKey, String expectedHeaderValue) {
		String actualHeaderValue = response.getHeader(headerKey);
		Assert.assertEquals(expectedHeaderValue, actualHeaderValue, "Response header value does not match for key: " + headerKey);
	}
}
