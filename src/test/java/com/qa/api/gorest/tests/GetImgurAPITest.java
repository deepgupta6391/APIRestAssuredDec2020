package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object,Object> tokenMap;
	String accessToken;
	String accountUsername;
	String refreshToken;
	
	String baseURI="https://api.imgur.com";
	
	
	@BeforeMethod
	public void setUp() {
		tokenMap=Token.getAccessToken();
		accessToken=tokenMap.get("access_token").toString();
		accountUsername=tokenMap.get("account_username").toString();
		refreshToken=tokenMap.get("refresh_token").toString();		
	}
	
	
	@Test
	public void getAccountBlockStatusTest() {
		String basePath="/account/v1/"+accountUsername+"/block";
		Map<String,String> authToken=Token.getAuthToken();
		Response response=RestClient.doGet(null, baseURI, basePath, authToken, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void getAccountImageTest() {
		String basePath="/3/account/me/images";
		Map<String,String> authToken=Token.getAuthToken();
		Response response=RestClient.doGet(null, baseURI, basePath, authToken, null, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void uploadImagePostAPITest() {
		String basePath="/3/upload";
		Map<String,String> clientIdMap=Token.getClientId();
		
		Map<String,String> formMap=new HashMap<String,String>();
		formMap.put("title", "The title of the image is desert");
		formMap.put("description", "This is the picture of the desert");		
		Response response=RestClient.doPost("multipart", baseURI, basePath, clientIdMap, null, true, formMap);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
}
