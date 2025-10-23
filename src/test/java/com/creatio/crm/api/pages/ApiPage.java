package com.creatio.crm.api.pages;

import org.json.JSONObject;

public class ApiPage {
	
	public static String createRepoRequestBody(String name, String description, boolean isPrivate) {
		JSONObject body = new JSONObject();
		body.put("name", name);
		body.put("description", description);
		body.put("private", isPrivate);	
		return body.toString();
	}
	
	
	
}
