package com.epam.project.entity;

import com.epam.project.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name ="users", uniqueConstraints = @UniqueConstraint(columnNames = { "userName" }))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userType;
	private String userName;
	private String password;
	private String customerName;
	private String emailId;

	public User() {
		super();
	}

	public User(String userType, String userName, String password, String customerName, String emailId) {
		super();
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.customerName = customerName;
		this.emailId = emailId;
	}
	public User(UserDto user) {

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
