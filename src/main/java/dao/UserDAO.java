package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import appConstants.MyConstants;
import model.PasswordEncryptionUsingAES;
import model.User;

public class UserDAO {
	
	public static Boolean findUser(String userEmail) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		String query = MyConstants.GET_USER_BY_EMAIL_QUERY;
		
		if(con != null) {
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, userEmail);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return true;
			}
			
			con.close();
		}
		
		return false;		
	}
	
	
	public static int registerUser(String query, User user)  {
		
		Connection con = DbConnection.getDbConnection();
		
		
		if(con != null) {
			
			try {
				
				if(!findUser(user.getEmail())) {		

					PreparedStatement statement = con.prepareStatement(query);
					
					String encryptedPassword = PasswordEncryptionUsingAES.encrypt(user.getEmail(), user.getPassword());
					
					statement.setString(1, user.getfName());
					statement.setString(2, user.getlName());
					statement.setString(3, user.getEmail());
					statement.setString(4, user.getUserImgUrl());
					statement.setString(5, user.getPhoneNum());
					statement.setString(6, user.getAddress());
					statement.setString(7, encryptedPassword);
					
					int insertedRows = statement.executeUpdate();
					
					if(insertedRows > 0) {
						return 1;
					}
					
				} else {
					return 0;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}

		}
		
		return -1;
	}
	
	
	public static User getUserDataUsingEmail(String userEmail) {
		
		Connection con = DbConnection.getDbConnection();		
		
		String query = "SELECT * FROM USERS WHERE  email = ?";
		
		User user = null;
	
		try {
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, userEmail);
			
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next()) {
				
				int userID = queryResult.getInt(1);				
				String fName = queryResult.getString(2);
				String lName = queryResult.getString(3);
				String email = queryResult.getString(4);
				String imgUrl  = queryResult.getString(5);
				String phoneNum = queryResult.getString(6);
				String address = queryResult.getString(7);
				String encryptedPassword = queryResult.getString(8);
				
				user = new User(fName, lName, phoneNum, address, email, encryptedPassword, imgUrl);
				
				user.setUserID(userID);
				
				return user;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		}

		return user;		
	}
	
	
	public static int logInUser(String userEmail, String userPassword) {
		
		Connection con = DbConnection.getDbConnection();
		
		if(con != null ) {
			
			try {
				
				if(findUser(userEmail)) {
					String getEncryptedPasswordQuery = "SELECT encryptedPassword FROM USERS WHERE email = ?";
					
					PreparedStatement statement = con.prepareStatement(getEncryptedPasswordQuery);
					statement.setString(1, userEmail);
					
					ResultSet queryResult = statement.executeQuery();
					
					String encryptedPassword = null;
					
					while(queryResult.next()) {
						encryptedPassword = queryResult.getString(1);
					}
					
					String decryptedPassword = PasswordEncryptionUsingAES.decrypt(encryptedPassword, userEmail);
										
					if(decryptedPassword.equals(userPassword)) {
						return 1;
					} else {
						return 2;
					}
					
				} else {
					return 0;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			
		}
		
		return -1;
	}
	
	
	public static String[] getCookiesData(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		String[] userData = {};
		
    	if(cookies != null){
    		
	    	for(Cookie cookie : cookies){
	    		
	    		if(cookie.getName().equals("userData")) {
	    			
	    			String cookieValue = cookie.getValue();
	    			
	    			userData = cookieValue.split("\\|");
	    			
	    			return userData;
	    			
	    		}
	    		
	    	}
	    	
    	}
    	
    	return userData;
	}
	
}
