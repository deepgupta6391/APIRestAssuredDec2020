package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("create user go rest api feature implementation.....")
@Feature("create user api feature....")
public class CreateUserTest {

	String baseURI="https://gorest.co.in";
	String basePath="/public-api/users";
	String token="5c0be83a212c355886eda936d41fe0e1108cd47940062c895b93515377040420";
	
	
	@DataProvider
	public Object[][] getUserData(){
		Object userdata[][]=ExcelUtil.getTestData("userdata");
		return userdata;
	}
	
	
//	@Test(dataProvider = "getUserData")
//	public void createUserAPIPOSTTest(String name,String email,String gender,String status) {
//		User user=new User("Ads", "Ads@gmail.com", "Female", "Active");
//		User user=new User(name, email, gender, status);
//			
//		Response response=RestClient.doPost("JSON", baseURI, basePath, token, null, true, user);
//		System.out.println(response.getStatusCode());
//		System.out.println(response.prettyPrint());
//	}
	
	@Description("create a user api test...verify create user from post call....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getUserData")
	public void createUserAPIPOSTTest(Hashtable<String, String> data) {			
		Map<String,String> authToken=new HashMap<String,String>();  
		authToken.put("Authorization", "Bearer "+token);
		
		User user=new User(data.get("name"),data.get("email"), data.get("gender"), data.get("status"));
		Response response=RestClient.doPost("JSON", baseURI, basePath, authToken, null, true, user);
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println("=======================================");
	}
}
