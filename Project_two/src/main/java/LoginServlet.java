import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("navbar.html").include(request,response);

        System.out.println( "Project started..." );

        //create a configuration object
        Configuration config = new Configuration();

        //read the configuration and load the object
        config.configure("hibernate.cfg.xml");

        //create the factory
        SessionFactory factory = config.buildSessionFactory();

        //open the session
        Session hybSess = factory.openSession();

        //begin transaction
        Transaction t = hybSess.beginTransaction();

        Query query = hybSess.createQuery("from users", User.class);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.equals("admin") && password.equals("123")) {
            out.println("You are successfully logged in.");
            out.println("<br>Welcome " + username);

            //using session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

        } else {
            out.println("Sorry! invalid details.");
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
            rd.include(request, response);
        }

        out.close();

    }

}
