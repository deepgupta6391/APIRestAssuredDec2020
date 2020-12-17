package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("get user go rest api feature implementation....")
@Feature("get user api feature.....")
public class GetUserTest {

	String baseURI="https://gorest.co.in";
	String basePath="/public-api/users";
	String token="5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420";
	
	@Description("get all users list api test...verify all users list from get call....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 1)
	public void getAllUserListAPITest() {
		Map<String,String> authToken=new HashMap<String,String>();  
		authToken.put("Authorization", "Bearer "+token);
		Response response=RestClient.doGet("JSON", baseURI, basePath, authToken, null, true);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	

	@Description("get all users list api test with query params...verify all users list from get call....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void getUserWithQueryParamsAPITest() {
		Map<String,String> authToken=new HashMap<String,String>();  
		authToken.put("Authorization", "Bearer "+token);
		Map<String,String> params=new HashMap<String,String>();
		params.put("name", "Aditya");
		params.put("gender", "Male");
		
		Response response=RestClient.doGet("JSON", baseURI, basePath, authToken, params, true);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
}
