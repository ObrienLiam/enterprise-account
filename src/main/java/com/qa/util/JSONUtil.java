package com.qa.util;

import com.google.gson.Gson;

public class JSONUtil {

	private Gson gson;
	
	public JSONUtil() 
	{
		this.gson = new Gson();
	}
	
	public String getJSONForObject(Object accountMap)
	{
		return gson.toJson(accountMap);
	}
	
	public <T> T getObjectForJSON(String jsonString, Class<T> usedClass)
	{
		return gson.fromJson(jsonString, usedClass);
	}
	
}
