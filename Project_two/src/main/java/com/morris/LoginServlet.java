package com.morris;

import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
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

        SpeedHibernate speedHibernate = new SpeedHibernate();
        User user = speedHibernate.query(username);
        // inital username is "test", and "password
        if(user == null){
            // We need to check if its null first so that we don't throw an error
            // DO NOT DO ANY .GETUSERNAME OR ANYTHING LIKE THAT IT WILL 100% CRASH
            System.out.println("Sorry! invalid details.");
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
            rd.include(request, response);
        }
        else if (password.equals(user.getPassword())) {
            // debug purposes
            System.out.println("Password Accepted!");

            // used to go to the next page
            System.out.println("You are successfully logged in.");
            System.out.println("<br>Welcome " + username);
            ServletContext servletContext = getServletContext();
            servletContext.setAttribute("username",username);
            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/user.html");
            // runs the userpage servlet
            request.getRequestDispatcher("com.morris.userpage").include(request, response);
        }else{
            System.out.println("LOGIN FAILEED");
            RequestDispatcher requestdispatcher = request.getRequestDispatcher("/login.html");
            requestdispatcher.forward(request, response);
        }

        out.close();
    }

}
