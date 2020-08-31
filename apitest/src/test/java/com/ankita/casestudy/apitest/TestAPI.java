package com.ankita.casestudy.apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.ankita.casestudy.apitest.utility.APIUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.response.Response;

public class TestAPI {
	private static Logger logger=LogManager.getLogger(TestAPI.class);
	 
@Test(description="test case 1: will be validating the status code,schema and number of records should be greater than 100")
public void test_1()
{
	String url="https://jsonplaceholder.typicode.com/posts";
	Response response=APIUtility.get(url);
	JSONArray JSONResponseBody = new JSONArray(response.body().asString());
	Assert.assertEquals(response.getStatusCode(),200);
	Assert.assertTrue(JSONResponseBody.length()>=100);
	logger.info("Response :"+response.asString());
	for(Object userJson:JSONResponseBody)
	 {
		 assertThat(userJson.toString(), matchesJsonSchemaInClasspath("user.json"));
	 }
	
}

@Test(description="test case 2: validate whether user is able to fetch first record")
public void test_2()
{
	String url="https://jsonplaceholder.typicode.com/posts/1";
	String url1="https://jsonplaceholder.typicode.com/posts";
	Response response=APIUtility.get(url);
	Assert.assertEquals(response.getStatusCode(),200);
	
	// fetching the first element of the posts api to match with the current result
	Response response1=APIUtility.get(url1);	
	Assert.assertEquals(response1.getStatusCode(),200);	
	JSONArray JSONResponseBody = new   JSONArray(response1.body().asString());
	JSONObject firstElement=JSONResponseBody.getJSONObject(0);	
	
	assertThat(response.body().asString(), matchesJsonSchemaInClasspath("user.json"));
	logger.info("Response :"+response.asString());
	JSONObject obj = new JSONObject(response.body().asString());	
	Assert.assertEquals(obj.get("id"),firstElement.get("id"));
	Assert.assertEquals(obj,firstElement);
	 
}

@Test(description="test case 3: validate whether status code is 404 or not")
public void test_3()
{
	String url="https://jsonplaceholder.typicode.com/invalidposts";
	Response response=APIUtility.get(url);
	Assert.assertEquals(response.getStatusCode(),404);	
	logger.info("Response :"+response.asString());
	 
}

@Test(description="test case 4: validate post request")
public void test_4()
{
	String url="https://jsonplaceholder.typicode.com/posts";
	String requestBody="{\r\n" + 
			"\"title\": \"foo\",\r\n" + 
			"\"body\": \"bar\",\r\n" + 
			"\"userId\": 1\r\n" + 
			"}";
	
	logger.info("Request : "+requestBody);
	
	Response response=APIUtility.post(url,requestBody);
	Assert.assertEquals(response.getStatusCode(),201);
	assertThat(response.body().asString(), matchesJsonSchemaInClasspath("userUpdateSchema.json"));
	JSONObject responseJsonObj = new JSONObject(response.body().asString());
	Assert.assertNotNull(responseJsonObj.get("id"));
	Assert.assertEquals(responseJsonObj.get("id"),101);
	logger.info("Response :"+response.asString());
	
	 
}

@Test(description="test case 5: validate put request")
public void test_5()
{
	String url="https://jsonplaceholder.typicode.com/posts/1";
	JSONObject requestParams = new JSONObject();
	requestParams.put("id",1); 
	requestParams.put("title", "abc"); 
	requestParams.put("body", "xyz");
	requestParams.put("userId", 1);
	
	logger.info("Request : "+requestParams.toString());
	
	Response response=APIUtility.put(url,requestParams);
	Assert.assertEquals(response.getStatusCode(),200);
	assertThat(response.body().asString(), matchesJsonSchemaInClasspath("userUpdateSchema.json"));
	JSONObject responseJsonObj = new JSONObject(response.body().asString());
	Assert.assertEquals(responseJsonObj.get("id"),1);
	
	logger.info("Response :"+response.asString());
	
	 
}

@Test(description="test case 6: validate delete request")
public void test_6()
{
	String url="https://jsonplaceholder.typicode.com/posts/1";
	
	Response response=APIUtility.delete(url);
	Assert.assertEquals(response.getStatusCode(),200);
	logger.info("Response :"+response.asString());
	
	 
}
}
