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
                // KEEP THE IF STATEMENT INSIDE TRY, IF NULL
                if (burse.getId() == user.getId()) {
                    // THERE IS A CRASH HERE BECAUSE THE USER ITSELF IS NULL FOR SOME REASON

                    System.out.println("ID MATCH");
                    boolean test = burse.getApproved();
                    if (test) {
                        allbursements.append("<p>");
                        System.out.println(burse.getAmount() + " AMOUNT ");
                        allbursements.append("The amount ").append(burse.getAmount()).append(" ");
                        allbursements.append("has been approved!");
                        allbursements.append("</p>");
                    } else {
                        allbursements.append("<p>");
                        allbursements.append("The amount ").append(burse.getAmount()).append(" ");
                        allbursements.append(" has been denied.");
                        allbursements.append(" With the burseID: ").append(burse.getBurseID()).append(".");
                        allbursements.append("</p>");
                    }
                }
            }catch (Exception e) {
                // WHEN WE CHECK THE BURSE.GETAPPROVED AND IT IS NULL;
                    System.out.println("CATCH FOUND");
                    break;
                }
            System.out.println("Check for idMatch in burser check " + x);
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
                     allbursements +

                "            </td>\n" +
                "            <td>\n" +
                "                <!--Post Requests-->\n" +
                "                <!-- Edit Current information with form -->\n" +
                "                <form action=\"com.morris.addrequest\" method=\"post\">\n" +
                "                    <h2>Update Information</h2>\n" +
                "                    <div>\n" +
                "                        <p>Request Amount:</p><input type=\"text\" name=\"amount\" placeholder=\"Amount\">\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        <p>Reasons:</p><input type=\"text\" name=\"reasons\" placeholder=\"reasons?\">\n" +
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
                "                    Current Username:\n"+
                "                    <p>"+username+"</p>\n" +
                "                </div>\n" +
                "                <div id=\"currentinfo\">\n" +
                "                    Current Email:\n" +
                "                    <p>"+user.getEmail()+"</p>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "            <td>\n" +
                "                <!-- Edit Current information with form -->\n" +
                "                <form action=\"com.morris.updateuser\" method=\"post\">\n" +
                "                    <h2>Update Information</h2>\n" +
                "                    <div>\n" +
                "                        <p>Username:</p><input type=\"text\" name=\"username\" placeholder=\"Enter Username\">\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        <p>Password:</p><input type=\"password\" name=\"password\" placeholder=\"Enter Password\">\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        <p>Email:</p><input type=\"email\" name=\"email\" placeholder=\"Enter Password\">\n" +
                "                    </div>\n" +
                "                    <div class=\"button\">\n" +
                "                        <input type=\"submit\" value=\"Update Information\">\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <br><br><br>";
        System.out.println(content);
        out.println(content);
    }
}