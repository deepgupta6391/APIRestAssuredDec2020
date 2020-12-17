package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Token {

	public static Map<Object,Object> appTokenMap;
	public static Map<String,String> tokenMap=new HashMap<String,String>();
	public static String clientId = "f81e42d5b8c37fd";
	
	
	public static Map<Object, Object> getAccessToken() {
		
		Map<String,String> formParams=new HashMap<>();
		formParams.put("refresh_token", "87dff47ffa184704897c8647f1982a7eba7ef4ab");
		formParams.put("client_id", "f81e42d5b8c37fd");
		formParams.put("client_secret", "fcaf2d47b197c0f26655eab834d561a40d5d33ea");
		formParams.put("grant_type", "refresh_token");
		
		JsonPath tokenJson=
						given()
							.formParams(formParams)
						.when()
							.post("https://api.imgur.com/oauth2/token")
						.then()
							.extract().jsonPath();
		System.out.println(tokenJson.getMap(""));

		appTokenMap= tokenJson.getMap("");
		return appTokenMap;
	}
	
	public static Map<String, String> getAuthToken() {
		String authToken=appTokenMap.get("access_token").toString();
		System.out.println("Auth Token ====> "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
	}
	
	public static Map<String, String> getClientId() {
		System.out.println("Client id is ====> "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
	}
}
