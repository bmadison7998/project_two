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
        ServletContext servletContext = request.getServletContext();
        if((boolean) servletContext.getAttribute("ismanager")){
            // LOGIN AS MANAGER
            request.getRequestDispatcher("com.morris.managerpage").include(request,response);
        }   else {
            // LOGIN AS USER
            request.getRequestDispatcher("com.morris.userpage").include(request, response);
        }
    }
}
