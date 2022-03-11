package com.morris;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class updateuser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  Get the values and update the database
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        // UPDATE THE ATTRIBUTES
        ServletContext servletContext = request.getServletContext();
        String _user = (String) servletContext.getAttribute("username");
        // ADD A UPDATE FUNCTION INTO SPEEDHIBERNATE
        SpeedHibernate speedHibernate = new SpeedHibernate("User");
        User user = speedHibernate.queryUser(_user);
        speedHibernate.updateUser(user.getId(),username,password,email);
        servletContext.setAttribute("username", username);
        request.getRequestDispatcher("com.morris.userpage").include(request, response);
    }
}
