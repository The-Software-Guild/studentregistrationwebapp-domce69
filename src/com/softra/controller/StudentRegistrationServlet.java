package com.softra.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softra.dao.StudentDao;
import com.softra.model.Student;
import com.softra.service.StudentService;

/**
 * Servlet implementation class StudentRegistrationServlet
 */
public class StudentRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	StudentService service;
	
    public StudentRegistrationServlet() {
        super();
        service = new StudentService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doGet");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int mobile = Integer.parseInt(request.getParameter("mobile"));
		String address = request.getParameter("address");
		
		Student s = new Student(name, age, mobile, address);
		request.setAttribute("stu", s);
		
		service.saveStud(s);
				
		RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
