package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public static final int RESPONSE_STATUS_CODE_200 = 200;
	public static final int RESPONSE_STATUS_CODE_500 = 500;
	public static final int RESPONSE_STATUS_CODE_400 = 400;
	public static final int RESPONSE_STATUS_CODE_401 = 401;
	public static final int RESPONSE_STATUS_CODE_201 = 201;
	public static final int RESPONSE_STATUS_CODE_204 = 204;
	public Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\QA\\SeleniumWorkSpace\\RestAssuredAPITesting\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
