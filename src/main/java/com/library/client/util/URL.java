package com.library.client.util;

public class URL {

	// Configure
	private final static String HTTP_Host = "127.0.0.1";
	private final static String HTTP_Port = "8080";
	public final static Integer Timeout = 60;

	// User
	public final static String HTTP_User = "/user";
	public final static String User_Add = "/add";
	public final static String User_Delete = "/delete";
	public final static String User_GetByName = "/getbyname";
	public final static String User_GetById = "/getbyid";
	public final static String User_All = "/all";
	public final static String User_Login = "/login";
	public final static String User_Logout = "/logout";

	public final static String getURL(String group, String work) {
		return String.format("http://%s:%s%s%s", HTTP_Host, HTTP_Port, group, work);
	}
}
