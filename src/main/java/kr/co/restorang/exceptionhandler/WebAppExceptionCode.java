package kr.co.restorang.exceptionhandler;

import org.springframework.http.HttpStatus;


public class WebAppExceptionCode {

	//USER EXCEPTIONS
	public static WebappException USER_ALREADY_REGISTERED_400_4000 = new WebappException(HttpStatus.BAD_REQUEST, 4000, "User has already registered.", "", "");
	public static WebappException USER_LOGIN_FAILED_400_4003 = new WebappException(HttpStatus.BAD_REQUEST, 4003, "Login failed.", "", "");
	public static WebappException USER_NOTFOUND_400_4005 = new WebappException(HttpStatus.BAD_REQUEST, 4005, "User not found.", "", "");
}
