package com.automation.testCases;

import com.automation.base.BaseApi;
import com.automation.utils.DataProviderUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class UserApiTest extends BaseApi {

	@Test
	public void getUsersTest() {
		// GET request to retrieve a list of users
		Response response = get("/api/users");

		// Validate status code and response schema
		Assert.assertEquals(response.statusCode(), 200);
		response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonSchema.json"));
	}

	@Test
	public void getUserByIdTest() {
		int userId = 2;
		// GET request to fetch user details by ID
		Response response = get("/api/users/" + userId);

		// Validate status code
		Assert.assertEquals(response.statusCode(), 200);
		response.then().assertThat().body("data.id", equalTo(userId));
	}

	@DataProvider(name = "createUserData")
	public Object[][] createUserDataProvider() {
		return new Object[][] { { DataProviderUtils.readJsonFromFile("src/main/resources/createUserData.json") } };
	}

	@Test(dataProvider = "createUserData")
	public void createUserTest(Map<String, Object> requestBody) {
		// POST request to create a new user
		Response response = given().contentType("application/json").body(requestBody).post("/api/users");

		// Validate status code
		Assert.assertEquals(response.statusCode(), 201);

		// Validate response body content using Hamcrest matchers
		response.then().assertThat().body("name", equalTo(requestBody.get("name")));
		response.then().assertThat().body("job", equalTo(requestBody.get("job")));
	}

	@DataProvider(name = "updateUserData")
	public Object[][] updateUserDataProvider() {
		return new Object[][] { { DataProviderUtils.readJsonFromFile("src/main/resources/updateUserData.json") } };
	}

	@Test(dataProvider = "updateUserData")
	public void updateUserTest(Map<String, Object> requestBody) {
		int userId = 2;
		// PUT request to update user information
		Response response = given().contentType("application/json").body(requestBody).put("/api/users/" + userId);

		// Validate status code
		Assert.assertEquals(response.statusCode(), 200);

		// Validate response body content using Hamcrest matchers
		response.then().assertThat().body("name", equalTo(requestBody.get("name")));
		response.then().assertThat().body("job", equalTo(requestBody.get("job")));
	}

	@Test
	public void deleteUserTest() {
		int userId = 2;
		// DELETE request to delete a user
		Response response = delete("/api/users/" + userId);

		// Validate status code
		Assert.assertEquals(response.statusCode(), 204);
	}
}
