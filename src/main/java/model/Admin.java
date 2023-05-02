package model;

public class Admin {

	private String name, email, adminImg;
	
	public Admin(String name, String email, String adminImg) {
		this.name = name;
		this.email = email;
		this.adminImg = adminImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminImg() {
		return adminImg;
	}

	public void setAdminImg(String adminImg) {
		this.adminImg = adminImg;
	}
	
	
	
}
