package com.example.attendancesystem;

public class StudentBean {

	private int student_id;
	private String student_firstname;
	private String student_lastname;
	private String student_mobilenumber;
	private String student_department;
	private String student_class;
	private String student_username; // new field
	private String student_password; // new field

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer id) {
		this.student_id = id ;
	}

	public String getStudent_firstname() {
		return student_firstname;
	}

	public void setStudent_firstname(String student_firstname) {
		this.student_firstname = student_firstname;
	}

	public String getStudent_lastname() {
		return student_lastname;
	}

	public void setStudent_lastname(String student_lastname) {
		this.student_lastname = student_lastname;
	}

	public String getStudent_mobilenumber() {
		return student_mobilenumber;
	}

	public void setStudent_mobilenumber(String student_mobilenumber) {
		this.student_mobilenumber = student_mobilenumber;
	}
	public String getStudent_department() {
		return student_department;
	}

	public void setStudent_department(String student_department) {
		this.student_department = student_department;
	}

	public String getStudent_class() {
		return student_class;
	}

	public void setStudent_class(String student_class) {
		this.student_class = student_class;
	}

	public String getStudent_username() { // new getter
		return student_username;
	}

	public void setStudent_username(String student_username) { // new setter
		this.student_username = student_username;
	}

	public String getStudent_password() { // new getter
		return student_password;
	}

	public void setStudent_password(String student_password) { // new setter
		this.student_password = student_password;
	}
}
