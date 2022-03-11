package com.morris;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class addrequest extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String amount = request.getParameter("amount");
        System.out.println("INSERT AMOUNT: " + amount);
        SpeedHibernate speedHibernate = new SpeedHibernate("reimbursement");
        ServletContext servletContext = request.getServletContext();
        try {
            String username = (String) servletContext.getAttribute("username");
            SpeedHibernate asdf = new SpeedHibernate("User");
            User user = asdf.queryUser(username);
            try {
                speedHibernate.insertintoReimbursement(user.getId(), Double.parseDouble(amount));
            } catch (Exception e) {
                System.out.println("AMOUNT GIVEN NOT A DOUBLE; FAIL INSERT");
            }
        }catch(Exception e){
            System.out.println(" FAIL IN UPDATE");
        }
        request.getRequestDispatcher("com.morris.login").include(request, response);
    }
}
