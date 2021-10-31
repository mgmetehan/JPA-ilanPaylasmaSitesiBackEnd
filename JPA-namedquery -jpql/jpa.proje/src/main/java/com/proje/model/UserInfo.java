package com.proje.model;

import java.util.Date;

public class UserInfo {

	private String username;

	private String firstName;

	private String lastName;

	private Date birthOfDate;

	public UserInfo() {
	}

	public UserInfo(String username, String firstName, String lastName, Date birthOfDate) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthOfDate = birthOfDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

}
