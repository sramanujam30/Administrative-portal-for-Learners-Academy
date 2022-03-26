package com.simplilearn.learnersacademy.Administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.simplilearn.learnersacademy.Beans.*;


public class DBTransactions {
	
	protected MysqlDataSource dataSource = null;
	public DBTransactions(MysqlDataSource datasource)  {
			this.dataSource = datasource;
	}

	public List<Student> getStudents() throws SQLException {

		List<Student> students = new ArrayList<>();
		
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM students";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			
			while (result.next()) {

				int id = result.getInt("StudentID");
				String studentName = result.getString("StudentName");
				int age = result.getInt("Age");
				int aclass = result.getInt("classID");

				// create a new student object and add to list of students
				Student tempStudent = new Student(id, studentName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
		}
		return students;

	}

	public List<Teacher> getTeachers() throws SQLException {

		List<Teacher> teachers = new ArrayList<>();

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM Teachers";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) {

				int id = result.getInt("TeacherID");
				String firstName = result.getString("FirstName");
				String lastName = result.getString("LastName");

				// create a new teacher object and add it to list of teachers
				Teacher temp = new Teacher(id, firstName, lastName);
				teachers.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return teachers;

	}

	public List<Subject> getSubjects() throws SQLException {

		List<Subject> subjects = new ArrayList<>();

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			String sql = "SELECT * FROM Subjects";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {

				int id = result.getInt("SubjectID");
				String name = result.getString("SubjectName");

				// create new subject object and add it to subjects list
				Subject temp = new Subject(id, name);
				subjects.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return subjects;

	}

	public List<ClassBean> getClasses() throws SQLException {

		List<ClassBean> classes = new ArrayList<>();

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM classes order by sectionID";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {

				int id = result.getInt("classID");
				int section = result.getInt("sectionID");
				int subject = result.getInt("SubjectID");
				int teacher = result.getInt("TeacherID");
				String time = result.getString("ClassTime");
				
				//using SubjectID, TeacherID find the corresponding SubjectName and TeacherName
				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);
				String teacherName = tempTeacher.getFirstName() + " " +tempTeacher.getLastName();
				String subjectName = tempSubject.getSubjectName();
				

				// create new Class object and add to list of classes
				ClassBean temp = new ClassBean(id, section, teacherName, subjectName, time);
				classes.add(temp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return classes;

	}

	public Teacher loadTeacher(int teacherId) throws SQLException {

		Teacher teacher = null;

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			String sql = "SELECT * FROM teachers WHERE TeacherID = " + teacherId;
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {

				int id = result.getInt("TeacherID");
				String fname = result.getString("FirstName");
				String lname = result.getString("LastName");
				teacher = new Teacher(id, fname, lname);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return teacher;

	}

	public Subject loadSubject(int subjectId) throws SQLException {

		Subject subject = null;

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			String sql = "SELECT * FROM subjects WHERE SubjectID = " + subjectId;
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {

				int id = result.getInt("SubjectID");
				String name = result.getString("SubjectName");

				subject = new Subject(id, name);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return subject;

	}

	public ClassBean loadClass(int classId) throws SQLException {

		ClassBean tempClass = null;

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			String sql = "SELECT * FROM classes WHERE id = " + classId;
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {

				int id = result.getInt("id");
				int section = result.getInt("sectionID");
				int subject = result.getInt("SubjectID");
				int teacher = result.getInt("TeacherID");
				String time = result.getString("ClassTime");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacherName = tempTeacher.getFirstName() + " " + tempTeacher.getLastName();
				String subjectName = tempSubject.getSubjectName();
				
				tempClass = new ClassBean(id,section,subjectName,teacherName,time);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return tempClass;

	}

	public List<Student> loadClassStudents(int classId) throws SQLException {

		List<Student> students = new ArrayList<>();

		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			
			conn = dataSource.getConnection();

			String sql = "SELECT * FROM students WHERE classID = " + classId;
			statement = conn.createStatement();
			result = statement.executeQuery(sql);

			while (result.next()) {
				int studentID = result.getInt("StudentID");
				String studentName = result.getString("StudentName");
				int age = result.getInt("age");
				int aclass = result.getInt("classID");

				// create new student object and add to students list
				Student tempStudent = new Student(studentID, studentName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

		}
		return students;
	}

}