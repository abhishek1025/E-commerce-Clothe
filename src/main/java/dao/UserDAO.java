package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import appConstants.MyConstants;
import model.Admin;
import model.PasswordEncryptionUsingAES;
import model.User;

public class UserDAO {
	
	public static Boolean findUser(String userEmail, String accountType) throws SQLException {
		
		Connection con = DbConnection.getDbConnection();
		
		String getUserQuery = MyConstants.GET_USER_BY_EMAIL_QUERY;
		
		String getAdminQuery = MyConstants.GET_ADMIN_BY_EMAIL_QUERY;
		
		PreparedStatement statement = null;
				
		if(con != null) {
			
			if(accountType.equals("admin")) {
				
				statement = con.prepareStatement(getAdminQuery);
				
			} else {
				
				statement = con.prepareStatement(getUserQuery);
				
			}
		
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
				
				if(!findUser(user.getEmail(), "user")) {		

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
	
	
	public static Admin getAminDataUsingEmail(String userEmail) {
			
			Connection con = DbConnection.getDbConnection();		
			
			String query = "SELECT name, email, adminImg FROM ADMINS WHERE email = ?";
			
			Admin admin = null;
		
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setString(1, userEmail);
				
				ResultSet queryResult = statement.executeQuery();
				
				while(queryResult.next()) {
	
					admin = new Admin(queryResult.getString(1), queryResult.getString(2), queryResult.getString(3));
					
				}
				
				return admin;
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return admin;
			}
		
		}
		
	
	
	public static int logInUser(String userEmail, String userPassword, String accountType) {
		
		Connection con = DbConnection.getDbConnection();
		
		String getEncryptedPasswordQuery = "";
		
		if(con != null ) {
			
			try {
				
				if(findUser(userEmail, accountType)) {
					
					if(accountType.equals("admin")) {
						
						getEncryptedPasswordQuery =  "SELECT encryptedPassword FROM ADMINS WHERE email = ?";
						
					} else {
						
						getEncryptedPasswordQuery = "SELECT encryptedPassword FROM USERS WHERE email = ?";
						
					}
					
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
	
	
	public static Boolean updateUserDetails(User user) {
		
		Connection con = DbConnection.getDbConnection();
		
		String query = "UPDATE users SET firstName = ?, lastName = ?, userImg = ?, phoneNumber = ?, address = ? WHERE email = ?";
		
		if(con != null) {
			
			try {
				
				PreparedStatement statement = con.prepareStatement(query);
				
				statement.setString(1, user.getfName());
				statement.setString(2, user.getlName());
				statement.setString(3, user.getUserImgUrl());
				statement.setString(4, user.getPhoneNum());
				statement.setString(5, user.getAddress());
				statement.setString(6, user.getEmail());
				
				int queryResult = statement.executeUpdate();
				
				if(queryResult > 0) {
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
				
			} finally {
			    try {
			    	con.close();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}
			
		}
		
		return null;
	}
	
	
	public static Boolean changePassword(String ecryptedPassword, String email, String oldPassword, String newPassword) {
		
		Connection con = DbConnection.getDbConnection();
		
		String query = "UPDATE users SET encryptedPassword = ? WHERE email = ?";
		
		if(con != null) {
			
			String decryptedPassword = PasswordEncryptionUsingAES.decrypt(ecryptedPassword, email);
			
			System.out.print(decryptedPassword);
			
			if(decryptedPassword.equals(oldPassword)) {
				
				try {
					
					String newEncryptedPassword = PasswordEncryptionUsingAES.encrypt(email, newPassword);
					
					PreparedStatement statement = con.prepareStatement(query);
					
					statement.setString(1, newEncryptedPassword);
					statement.setString(2, email);
					
					int queryResult = statement.executeUpdate();
					
					if(queryResult > 0) {
						
						return true;
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
					return true;
				}
				
			}
			
			return false;
			
		}
		
		return null;	
			
	}
	
}
