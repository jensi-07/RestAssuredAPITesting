package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	TestBase tb;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient rc;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	
	public void setUp() throws ClientProtocolException, IOException {
		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");

		url = serviceUrl + apiUrl;

}
	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		 rc = new RestClient();
		 HashMap<String, String> headerMap = new HashMap<String, String>();
		 headerMap.put("Content-Type", "application/json");
		// headerMap.put("Content-Length", "<calculated when request is sent>");
		// headerMap.put("Host", "<calculated when request is sent>");
		 //headerMap.put("User-Agent", "PostmanRuntime/7.32.3");
		 
		 
		 //jackson API(marshelling data)
		 ObjectMapper mapper = new ObjectMapper();
		 Users users = new Users("jensi", "Autoation QA");
		  
		 //object to jason
		// mapper.writeValue(new File("C:\\QA\\SeleniumWorkSpace\\RestAssuredAPITesting\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		 
		 //object to json in string
		 String userJson = mapper.writeValueAsString(users);
		 System.out.println(userJson);
		 
		 closeableHttpResponse = rc.post(url, userJson, headerMap);
	
		 //a. Status code
		 
		 int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		 Assert.assertEquals(statusCode, tb.RESPONSE_STATUS_CODE_201);
		 
		 //b. Json String
		 
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		 
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response Json from API---->" + responseJson);
		
	 Users userObj = mapper.readValue(responseString, Users.class);
	 System.out.println(userObj);
	 
	 Assert.assertTrue(users.getName().equals(userObj.getName()));
	 Assert.assertTrue(users.getJob().equals(userObj.getJob()));
	
	System.out.println(userObj.getId());
	System.out.println(userObj.getCreatedAt());
	
	
	
	
	
	
	
	}
	
}
