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
        SpeedHibernate speedHibernate = new SpeedHibernate("User");
        servletContext.setAttribute("username","");
        RequestDispatcher rd = request.getRequestDispatcher("/user.html");
        rd.include(request, response);

        //  SPEEDHIBERNATE HANDLES ALL CONNECTIONS TO THE SERVER
        // NEED TO MAKE IT SO THAT WE LOOP THROUGH ALL OF THE CONTENT IN THE REINBURSMENT TABLE AND SHOW WHAT MATCHES TO THE USER ID
        User user = speedHibernate.queryUser((String) servletContext.getAttribute("username"));

        SpeedHibernate burser = new SpeedHibernate("reimbursement");
        String allbursements = "";
        for(int x = 0; x < burser.countTableReimbursement(); x++){
            reimbursement burse = burser.queryReimbursementBurseID(x);
            if(burse.getId() == user.getId()) {
                // THERE IS A CRASH HERE BECAUSE THE USER ITSELF IS NULL FOR SOME REASON
                allbursements += "<p>";
                allbursements += "The amount " + burse.getAmount() + " ";
                try {
                    boolean test = burse.getApproved();
                    if (test) {
                        allbursements += "has been approved!";
                    } else {
                        allbursements += " has been denied.";
                    }
                } catch (Exception e) {
                    allbursements += " has not been checked yet.";
                }
                allbursements += "</p>";
            }
            System.out.println("Check for idMatch in burser check " + x);
        }
        // WRITE THE CONTENT OF THE PAGE HERE
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String content = "<div>\n" +
                "        <!-- Submit reimbursement request -->\n" +
                "    </div>\n" +
                "    <table class=\"userpagetable\">\n" +
                "        <tr>\n" +
                "            <td>\n" +
                "                <!--View requests-->\n" +
                "                <!--Foreach reimbursement request with the id under this user-->\n" +
                allbursements+
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
                "                        <input type=\"submit\" value=\"Login\">\n" +
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
                "                    Current Username:\n" +
                "                    <p>asdf</p>\n" +
                "                </div>\n" +
                "                <div id=\"currentinfo\">\n" +
                "                    Current Email:\n" +
                "                    <p>asdf</p>\n" +
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
                "                        <input type=\"submit\" value=\"Login\">\n" +
                "                    </div>\n" +
                "                </form>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "    <br><br><br>";
        out.println(content);
    }
}