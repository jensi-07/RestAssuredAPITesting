 package com.qa.tests;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PutAPITest extends TestBase{
	TestBase tb;
	String serviceUrl;
	String apiUrl;
	String url;
	String update;
	RestClient rc;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	
	public void setUp() throws ClientProtocolException, IOException {
		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		update = prop.getProperty("UpdateURL");

		url = serviceUrl + apiUrl + update;
		
}
	
	@Test
	public void putAPITest() throws ClientProtocolException, IOException  {
		 rc = new RestClient();
		 HashMap<String, String> headerMap = new HashMap<String, String>();
		 headerMap.put("Content-Type", "application/json");
		// headerMap.put("Content-Length", "<calculated when request is sent>");
		// headerMap.put("Host", "<calculated when request is sent>");
		 //headerMap.put("User-Agent", "PostmanRuntime/7.32.3");
	
		 //marselling
		 ObjectMapper mapper = new ObjectMapper();
		 Users users = new Users("jensi", "Autoation QA");
		 
		 //obj to json in String
		 String userJson = mapper.writeValueAsString(users);
		 System.out.println(userJson);
		 
		 //execute code
		 closeableHttpResponse = rc.put(url, userJson, headerMap);
		 
		 //a. status code
		 int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		 Assert.assertEquals(statuscode, tb.RESPONSE_STATUS_CODE_200);
		 
		 String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("Response Json from API---->" + responseJson);
			
		 Users userObj = mapper.readValue(responseString, Users.class);
		 System.out.println(userObj);
	}
}