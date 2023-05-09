package utils;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageCookie {

	//Method start
	public static void removeCookies(HttpServletRequest request,  HttpServletResponse response, String cookieName) {
		// Clear cookie
		Cookie[] cookies = request.getCookies();
		
    	if(cookies != null){
    		
	    	for(Cookie cookie : cookies){
	    		
	    		if(cookie.getName().equals(cookieName)) {
		    		cookie.setMaxAge(0);
		    		response.addCookie(cookie);
	    		} else {
	    			cookie.setMaxAge(0);
		    		response.addCookie(cookie);
	    		}
	    	}
	    	
    	}   	
	}
	//Method end
	
	//Method start
	public static String[] getCookiesData(HttpServletRequest request, String cookieName) {
		
		Cookie[] cookies = request.getCookies();
		
		String[] cookieData = {};
		
		
    	if(cookies != null){
    		
	    	for(Cookie cookie : cookies){
	    		
	    		if(cookie.getName().equals(cookieName)) {
	    			
	    			String cookieValue = cookie.getValue();
	    			
	    			cookieData = cookieValue.split("\\|");
	    			
	    			
	    		} 
	    		
	    	}
	    	
    	}
    	
    	return cookieData;
	}
	//Method end
	
}
