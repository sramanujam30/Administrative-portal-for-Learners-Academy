package com.simplilearn.learnersacademy.Beans;

public class Teacher {

	int teacherID;
	String firstName, lastName;
	
	public Teacher(int teacherID, String firstName, String lastName) {
		super();
		this.teacherID = teacherID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
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

	@Override
	public String toString() {
		return "Teacher [teacherID=" + teacherID + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}	

}
