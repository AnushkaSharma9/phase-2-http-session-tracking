package com.simpllearn.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// read http session 
		HttpSession session = request.getSession(false);
		if(session != null) {
			String useremail = (String) session.getAttribute("useremail");
			UUID token = (UUID) session.getAttribute("token");
			
			if(useremail.equals("admin@gmail.com")) {
				out.println("<h3 style='color:green'> Welcome to user profile '"+useremail+"' </h3>");
				out.println("<p> "+token+"</p>");
			} else {
				out.println("<h3 style='color:red'>Login Failed * Invalid credntials </h3>");
			}
		} else {
			out.println("<h3 style='color:red'>Invalid access, please login to see profile ! </h3>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
