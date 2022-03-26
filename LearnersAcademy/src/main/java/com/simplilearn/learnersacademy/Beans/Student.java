package com.simplilearn.learnersacademy.Beans;

public class Student {
	
	int StudentID;
	String StudentName;
	int Age;
	int classname;
	public Student(int studentID, String studentName, int age, int classname) {
		super();
		StudentID = studentID;
		StudentName = studentName;
		Age = age;
		this.classname = classname;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getClassname() {
		return classname;
	}
	public void setClassname(int classname) {
		this.classname = classname;
	}
	@Override
	public String toString() {
		return "Student [StudentID=" + StudentID + ", StudentName=" + StudentName + ", Age=" + Age + ", classname="
				+ classname + "]";
	}
	
	
	
	

}
