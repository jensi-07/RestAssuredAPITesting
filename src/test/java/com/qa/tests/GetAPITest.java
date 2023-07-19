package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
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

	@Test(priority = 1)
	public void getAPIWithoutHeadersTest() throws ClientProtocolException, IOException {
		rc = new RestClient();

		closeableHttpResponse = rc.get(url);

		// a. Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);

		// 1.get page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per Page---->" + perPageValue);

		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// 2.get total
		String total = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total---->" + total);

		Assert.assertEquals(Integer.parseInt(total), 12);

		// 3. 1st data value
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String Id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");

		System.out.println(lastName);
		System.out.println(Id);
		System.out.println(avatar);
		System.out.println(firstName);
		System.out.println(email);

		// c. All header
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeader = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array---->" + allHeader);
		
	}

	@Test(priority = 2)
	public void getAPIWithHeadersTest() throws ClientProtocolException, IOException {
		rc = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Content-Length", "<calculated when request is sent>");
		headerMap.put("Host", "<calculated when request is sent>");
		headerMap.put("User-Agent", "PostmanRuntime/7.32.3");
		headerMap.put("Content-Type", "application/json");

		closeableHttpResponse = rc.get(url);

		// a. Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code---->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->" + responseJson);

		// 1.get page value
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of per Page---->" + perPageValue);

		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// 2.get total
		String total = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of total---->" + total);

		Assert.assertEquals(Integer.parseInt(total), 12);

		// 3. 1st data value
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String Id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");

		System.out.println(lastName);
		System.out.println(Id);
		System.out.println(avatar);
		System.out.println(firstName);
		System.out.println(email);

		// c. All header
		Header[] headerArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeader = new HashMap<String, String>();

		for (Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array---->" + allHeader);

	}

}
