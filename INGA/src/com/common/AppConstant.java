package com.common;

public class AppConstant {

	/************* registration request parameters ****************/
	public static final String username = "data[User][username]";
	public static final String email = "data[User][email]";
	public static final String first_name = "data[User][firstname]";
	public static final String last_name = "data[User][lastname]";
	public static final String password = "data[User][password]";
	public static final String device_token = "data[User][device_token]";

	/************* registration response parameters ****************/
	public static final String error = "error";
	public static final String message = "msg";
	public static final String errorCode = "0";

	/** registration validation parameters **/
	public static final String emailValidation = "Enter valid email format";
	public static final String emptyValidation = "email or password or username is empty";
	public static final String confirmPasswordValidation = "Confirm password not same as password";

	/** logged user info prefrencence keys **/
	public static final String prefName = "LoggedInfo";
	public static final String loggedId = "id";
	public static final String loggedUsername = "username";
	public static final String loggedDeviceToken = "device_token";
	public static final String loggedEmail = "email";
	
	/************* registration response and url parameters ****************/
	public static final String user = "User";
	public static final String loginUrl = "app_userlogin";
	
	
	
	
	/** login validation parameters **/
	public static final String emptyFields="Email or password empty";

}
