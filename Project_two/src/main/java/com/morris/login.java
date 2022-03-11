package com.morris;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // WE USE THE LOGIN SERVLET SO WE CAN RELOAD THE USER PAGE ON REQUEST
        request.getRequestDispatcher("com.morris.userpage").include(request, response);
        // runs the userpage servlet
    }
}
