package com.simplilearn.learnersacademy.Beans;

public class ClassBean {

	int classID, sectionID; 
	String teacherName, subjectName;
	String classTime;
	public ClassBean(int classID, int sectionID, String teacherName, String subjectName, String classTime) {
		super();
		this.classID = classID;
		this.sectionID = sectionID;
		this.teacherName = teacherName;
		this.subjectName = subjectName;
		this.classTime = classTime;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public int getSectionID() {
		return sectionID;
	}
	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	@Override
	public String toString() {
		return "Class [classID=" + classID + ", sectionID=" + sectionID + ", teacherName=" + teacherName
				+ ", subjectName=" + subjectName + ", classTime=" + classTime + "]";
	}
	
	
	
}
