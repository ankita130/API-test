package com.ankita.casestudy.apitest.utility;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIUtility {
	
/**
	* get API call method
	* Returns Response of the get API 
	* @author  Ankita Dash
	* @param  url : url  of the get API
	* 
	*/
public static Response get(String url)
	{		 
	Map<String,Object> headerMap = new HashMap<String,Object>();
	headerMap.put("charset", "UTF-8");
		  Response response = RestAssured.given()
		    .when()
		    .headers(headerMap)
		    .get(url)
		    .then()
		    .contentType(ContentType.JSON)
		    .extract().response();
		  
		  return response;
	}

/**
* put API call method
* Returns Response of the put API 
* @author  Ankita Dash
* @param  url : url of the put API
* @param  requestParams : body of the put request
* 
*/
	
public static Response put(String url,JSONObject requestParams)
{		 
	Map<String,Object> headerMap = new HashMap<String,Object>();
	headerMap.put("charset", "UTF-8");
	  Response response = RestAssured.given().body(requestParams)
	    .when()
	    .headers(headerMap)
	    .put(url)	    
	    .then()
	    .contentType(ContentType.JSON)
	    .extract().response();
	  
	  return response;
}

/**
* post API call method
* Returns Response of the post API 
* @author  Ankita Dash
* @param  url : url of the post API
* @param  body : body of the post request
* 
*/
public static Response post(String url,String body) {
	Map<String,Object> headerMap = new HashMap<String,Object>();
	headerMap.put("charset", "UTF-8");
	Response response = RestAssured.given().body(body)
		    .when()
		    .headers(headerMap)
		    .post(url)
		    .then()
		    .contentType(ContentType.JSON)
		    .extract().response();
	return response;
}

/**
* post API call method
* Returns Response of the post API 
* @author  Ankita Dash
* @param  url : url of the post API
* @param  requestParams : body of the post request
* 
*/
public static Response post(String url,JSONObject requestParams) {
	Map<String,Object> headerMap = new HashMap<String,Object>();
	headerMap.put("charset", "UTF-8");
	Response response = RestAssured.given().body(requestParams)
		    .when()
		    .headers(headerMap)
		    .post(url)
		    .then()
		    .contentType(ContentType.JSON)
		    .extract().response();
	return response;
}
/**
* delete API call method
* Returns Response of the delete API 
* @author  Ankita Dash
* @param  url : url of the delete API
* 
*/
public static Response delete(String url) {
	Map<String,Object> headerMap = new HashMap<String,Object>();
	headerMap.put("charset", "UTF-8");
	Response response = RestAssured.given()
		    .when()
		    .headers(headerMap)
		    .delete(url)
		    .then()
		    .contentType(ContentType.JSON)
		    .extract().response();
	return response;
}
}
