package com.morris;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class updaterequest extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int burserid = Integer.parseInt(request.getParameter("burserid"));
        // set to radial form
        String stringaccp = request.getParameter("isapproved");
        boolean accepted = false;
        if(Objects.equals(stringaccp, "true")){
            accepted = true;
        }
        System.out.println("UPDATE REQUEST IS " + accepted + " WITH THE BURSERID " + burserid);
        SpeedHibernate speedHibernate = new SpeedHibernate("reimbursement");
        speedHibernate.updateReimbursement(burserid,accepted);
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
