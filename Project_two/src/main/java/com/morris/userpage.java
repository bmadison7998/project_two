package com.morris;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user.html")
public class userpage extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext servletContext = request.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        System.out.println("The current user is: " + username);
        servletContext.setAttribute("username","");
        RequestDispatcher rd = request.getRequestDispatcher("/user.html");
        rd.include(request, response);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("USERNAME IS " + username);
    }
}