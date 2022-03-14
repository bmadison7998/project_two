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
public class managerpage extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ServletContext servletContext = request.getServletContext();
        String username = (String) servletContext.getAttribute("username");
        System.out.println("CURRENT USERNAME " + username);
        // USE THIS FOR LOGGING OUT
        //servletContext.setAttribute("username","");

        RequestDispatcher rd = request.getRequestDispatcher("/user.html");
        rd.include(request, response);
        // LOAD IN THE PAGE THEN ADD TO IT

        //  SPEEDHIBERNATE HANDLES ALL CONNECTIONS TO THE SERVER
        // NEED TO MAKE IT SO THAT WE LOOP THROUGH ALL OF THE CONTENT IN THE REINBURSMENT TABLE AND SHOW WHAT MATCHES TO THE USER ID
        SpeedHibernate speedHibernate = new SpeedHibernate("User");
        User user = speedHibernate.queryUser(username);

        SpeedHibernate burser = new SpeedHibernate("reimbursement");
        StringBuilder allbursements = new StringBuilder();
        for(int x = 0; x != -1; x++){
            reimbursement burse = burser.queryReimbursementBurseID(x);
            try {
                    boolean test = burse.getApproved();
                    if (test) {
                        allbursements.append("<p>");
                        allbursements.append("UserID : " + burse.getId() + " with ");
                        allbursements.append("the reimbursement amount ").append(burse.getAmount()).append(" ");
                        allbursements.append("has been approved!");
                        allbursements.append(" With the burseID: ").append(burse.getBurseID()).append(".");
                        allbursements.append("</p>");
                    } else if(!test) {
                        allbursements.append("<p>");
                        allbursements.append("UserID : " + burse.getId() + " with ");
                        allbursements.append("the reimbursement amount ").append(burse.getAmount()).append(" ");
                        allbursements.append(" has been denied.");
                        allbursements.append(" With the burseID: ").append(burse.getBurseID()).append(".");
                        allbursements.append("</p>");
                } else {
                        allbursements.append("<p>");
                        allbursements.append("UserID : " + burse.getId() + " with ");
                        allbursements.append("the reimbursement amount ").append(burse.getAmount()).append(" ");
                        allbursements.append(" has not been seen yet.");
                        allbursements.append(" With the burseID: ").append(burse.getBurseID()).append(".");
                        allbursements.append("</p>");
                    }
            }catch (Exception e) {
                // WHEN WE CHECK THE BURSE.GETAPPROVED AND IT IS NULL;
                System.out.println("CATCH FOUND");
                break;
            }
            System.out.println("Check for idMatch in burser check " + x);
        }

        StringBuilder listuser = new StringBuilder();
        for(int x = 0; x != -1; x++){
            User currentuser = speedHibernate.queryUser(x);
            try {
                listuser.append("<p>");
                listuser.append("UserID : " + currentuser.getId());
                listuser.append(", Username : " + currentuser.getUsername());
                listuser.append("</p>");
                listuser.append("<br>");
            }catch (Exception e) {
                // WHEN WE CHECK THE BURSE.GETAPPROVED AND IT IS NULL;
                System.out.println("CATCH FOUND");
                break;
            }
        }


        // WRITE THE CONTENT OF THE PAGE HERE
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        final String content = "<div>\n" +
                "        <!-- Submit reimbursement request -->\n" +
                "    </div>\n" +
                "    <table class=\"userpagetable\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <!--View requests-->\n" +
                "                <!--Foreach reimbursement request with the id under this user-->\n" +
                "<h2>All reimbursements</h2>"+
                allbursements +

                "            </td>\n" +
                "            <td>\n" +
                "                <!--Post Requests-->\n" +
                "                <!-- Edit Current information with form -->\n" +
                "                <form action=\"com.morris.updaterequest\" method=\"post\">\n" +
                "                    <h2>Update reimbursement for UserID</h2>\n" +
                "                    <div>\n" +
                "                        <p>BurserID:</p><input type=\"number\" min=\"0\" max=\"" + (burser.countTableReimbursement() - 1) + "\" step=\"1\" name=\"burserid\" value=\"0\">\n" +
                "                    </div>\n" +
                "                    <div id=\"currentinfo\">\n" +
                "<p>Approve "+
                "                    <input type=\"radio\" id=\"approved\" name=\"isapproved\" checked=\"true\" value=\"true\"></p>" +
                "<p>Reject "+
                "                    <input type=\"radio\" id=\"rejected\" name=\"isapproved\" value=\"false\"></p>" +
                "                    </div>\n" +
                "                    <div class=\"button\">\n" +
                "                        <input type=\"submit\" value=\"Send Request\">\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <table class=\"userpagetable\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <!-- View current information -->\n" +
                "                <div id=\"currentinfo\">\n" +
                "<h2>User List</h2>"+
                listuser +
                "                </div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "                <!-- Edit Current information with form -->\n" +
                "                <form action=\"com.morris.getspecificuser\" method=\"post\">\n" +
                "                    <h2>Get the reimbursements from UserID</h2>\n" +
                "                    <div>\n" +
                "                        <p>BurserID:</p><input type=\"number\" min=\"0\" max=\"" + (speedHibernate.countTableUser() - 1) + "\" step=\"1\" name=\"specuser\" value=\"0\">\n" +
                "                    </div>\n" +
                "                    <div class=\"button\">\n" +
                "                        <input type=\"submit\" value=\"Update Information\">\n" +
                "                    </div>\n" +
                "<div> " + servletContext.getAttribute("specuser") + "</div>" +
                "                </form>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <br><br><br>";
        out.println(content);
    }
}