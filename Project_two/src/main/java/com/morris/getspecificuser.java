package com.morris;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class getspecificuser extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        StringBuilder allbursements = new StringBuilder();
        ServletContext servletContext = request.getServletContext();

        SpeedHibernate speedHibernate = new SpeedHibernate("User");
        int user = Integer.parseInt(request.getParameter("specuser"));

        SpeedHibernate burser = new SpeedHibernate("reimbursement");
        for(int x = 0; x != -1; x++){
            reimbursement burse = burser.queryReimbursementBurseID(x);
            try {
                // KEEP THE IF STATEMENT INSIDE TRY, IF NULL
                if (burse.getId() == user) {
                    // THERE IS A CRASH HERE BECAUSE THE USER ITSELF IS NULL FOR SOME REASON

                    System.out.println("ID MATCH");
                    boolean test = burse.getApproved();
                    if (test) {
                        allbursements.append("<p>");
                        allbursements.append("UserID : " + burse.getId() + " with ");
                        allbursements.append("the reimbursement amount ").append(burse.getAmount()).append(" ");
                        allbursements.append("has been approved!");
                        allbursements.append("</p><br>");
                    } else if(!test) {
                        allbursements.append("<p>");
                        allbursements.append("UserID : " + burse.getId() + " with ");
                        allbursements.append("the reimbursement amount ").append(burse.getAmount()).append(" ");
                        allbursements.append(" has been denied.");
                        allbursements.append("</p>");
                    }
                }
            }catch (Exception e) {
                // WHEN WE CHECK THE BURSE.GETAPPROVED AND IT IS NULL;
                System.out.println("CATCH FOUND");
                break;
            }
        }
        servletContext.setAttribute("specuser", allbursements);

        // WE USE THE LOGIN SERVLET SO WE CAN RELOAD THE USER PAGE ON REQUEST
        if((boolean) servletContext.getAttribute("ismanager")){
            // LOGIN AS MANAGER
            request.getRequestDispatcher("com.morris.managerpage").include(request,response);
        }   else {
            // LOGIN AS USER
            request.getRequestDispatcher("com.morris.userpage").include(request, response);
        }
    }
}
