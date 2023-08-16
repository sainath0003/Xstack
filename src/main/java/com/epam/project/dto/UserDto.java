package com.epam.project.dto;

import com.epam.project.entity.User;

public class UserDto {

	private int userId;
	private String userType;
	private String userName;
	private String password;
	private String customerName;
	private String emailId;

	public UserDto() {

	}

	public UserDto(User user) {

		super();
		this.userId = user.getUserId();
		this.userType = user.getUserType();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.customerName = user.getCustomerName();
		this.emailId = user.getEmailId();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "\nUser Details :\nuserId = " + userId + ",\nuserType = " + userType + ",\nuserName = " + userName
				+ ",\npassword = " + password + ",\ncustomerName = " + customerName + ",\nemailId = " + emailId + "\n";
	}

}
