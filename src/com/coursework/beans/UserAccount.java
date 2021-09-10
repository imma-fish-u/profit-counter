package com.coursework.beans;

public class UserAccount {

  public static final String ROLE_ADMIN = "admin";
  public static final String ROLE_USER  = "user";

  private String id;
  private String userName;
  private String role;
  private String password;
  private String email;
  private Boolean accepted;

  public UserAccount() {

  }
  
  public UserAccount(String userName, String password, String email, Boolean accepted) {
	  this.userName = userName;
	  this.password = password;
	  this.email = email;
	  this.accepted =  accepted;
  }
  
  public String getId() {
	  return id;
  }
  
  public void setId(String id) {
	  this.id = id;
  }
  
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRole() {
	if (role == null)
		return ROLE_USER;
	else
		return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
	    return email;
	  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public Boolean getAccepted() {
	    return accepted;
  }

	public void setAccepted(Boolean accepted) {
	  this.accepted = accepted;
	}
  
}