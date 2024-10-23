package com.automation.base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseApi {
	 @BeforeClass
	    public void setup() {
	        RestAssured.baseURI = "https://reqres.in";
	    }
}
