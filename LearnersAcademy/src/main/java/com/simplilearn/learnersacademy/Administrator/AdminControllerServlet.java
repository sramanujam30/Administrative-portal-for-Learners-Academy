package com.simplilearn.learnersacademy.Administrator;

import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.simplilearn.learnersacademy.Beans.*;

/**
 * Servlet implementation class MyFirstServlet
 */
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected DBTransactions dbRetrieve;
	protected MysqlDataSource datasource = null;
	protected String choice = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {

		super.init();
		try {
			  
			datasource = new MysqlDataSource();
			datasource.setURL("jdbc:mysql://localhost:3306/learnersacademy");
			datasource.setUser("root");
			datasource.setPassword("Pswd4mysql!");
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learnersacademy", "root", "Pswd4mysql!");
			dbRetrieve = new DBTransactions(datasource);
			System.out.println("Database object: "+datasource);
			

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DoPost called");
		try {

			// read the "command" parameter
			choice = request.getParameter("choice");
			System.out.println("Initial choice value is: "+choice);

			if (choice == null) {
				choice = "CLASSES";
				System.out.println("the choice now is changed to "+choice);
			}
			
			// if no cookies
			else if (!getCookies(request, response) && (!choice.equals("LOGIN"))) {

				response.sendRedirect("login.jsp");
			}

			
				System.out.println("Came inside else block...the choice now is: "+choice);
				// if there is no command, how to handle

				// route the data to the appropriate method
				switch (choice) {

				case "STUDENTS":
					studentsList(request, response);
					break;

				case "TEACHERS":
					teachersList(request, response);
					break;

				case "SUBJECTS":
					subjectList(request, response);
					break;

				case "CLASSES":
					classesList(request, response);
					break;

				case "ST_LIST":
					classStudentsList(request, response);
					break;

				case "LOGIN":
					login(request, response);
					break;
				
				default:
					classesList(request, response);
					break;

				}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	protected boolean getCookies(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean check = false;
		Cookie[] cookies = request.getCookies();
		// Find the cookie of interest in arrays of cookies
		for (Cookie cookie : cookies) {
			 
			if (cookie.getName().equals("admin") && cookie.getValue().equals("admin")) {
				check = true;
				break;
			} 

		}
		
		
		return check;

	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username.toLowerCase().equals("admin") && password.toLowerCase().equals("admin")) {

			Cookie cookie = new Cookie(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			// Setting the maximum age to 1 day
			cookie.setMaxAge(86400); // 86400 seconds in a day
			
			

			// Send the cookie to the client
			response.addCookie(cookie);
			
			classesList(request, response);
			
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}
	protected void classesList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get subjects from db util
		List<ClassBean> classes = dbRetrieve.getClasses();
		
		/*for(var bean:classes) {
			System.out.println(bean);
		}*/

		// add classes to the request
		request.setAttribute("CLASSES_LIST", classes);
		request.setAttribute("CLASSES", choice);
		//System.out.println("the request is: "+request);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classes-list.jsp");
		dispatcher.forward(request, response);

	}
	
	protected void studentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = dbRetrieve.getStudents();

		// add students to the request
		request.setAttribute("STUDENT_LIST", students);

		// send it to the jsp view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentslist.jsp");
		dispatcher.forward(request, response);

	}

	protected void teachersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Teacher> teachers = dbRetrieve.getTeachers();

		// add students to the request
		request.setAttribute("TEACHERS_LIST", teachers);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacherslist.jsp");
		dispatcher.forward(request, response);

	}

	protected void subjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get subjects from db util
		List<Subject> subjects = dbRetrieve.getSubjects();

		// add subjects to the request
		request.setAttribute("SUBJECTS_LIST", subjects);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/subjectslist.jsp");
		dispatcher.forward(request, response);

	}



	private void classStudentsList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String classID = request.getParameter("classId");
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		int classi = Integer.parseInt(classID);
		System.out.println("classID is" +classi);
		System.out.println("sectionID is" +section);
		System.out.println("subjectName is" +subject);

		// get subjects from db util
		List<Student> students = dbRetrieve.loadClassStudents(classi);

		// add subjects to the request
		request.setAttribute("STUDENTS_LIST", students);
		request.setAttribute("SECTION", section);
		request.setAttribute("SUBJECT", subject);

		// send it to the jSP view page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/students-class.jsp");
		dispatcher.forward(request, response);

	}
}
