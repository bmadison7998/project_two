package com.morris;

import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("admin") && password.equals("123")) {
            // debug purposes
            System.out.println("Password Accepted!");
            // used to go to the next page
            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/login.html");
            requestdispatcher.forward(request,response);

            System.out.println("You are successfully logged in.");
            System.out.println("<br>Welcome " + username);

            //using session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

        } else {
            System.out.println("Sorry! invalid details.");
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
            rd.include(request, response);
        }

        out.close();
    }

}