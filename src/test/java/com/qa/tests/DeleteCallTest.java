package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class DeleteCallTest extends TestBase{
	TestBase tb;
	String serviceUrl;
	String apiUrl;
	String url;
	String deleteUrl;
	RestClient rc;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		tb = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
deleteUrl = prop.getProperty("DeleteURL");
		url = serviceUrl + apiUrl + deleteUrl;

}
	@Test
	public void deleteAPIUser() throws ClientProtocolException, IOException {
		rc = new RestClient();

		closeableHttpResponse = rc.delete(url);
		
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_204);

	}
}
