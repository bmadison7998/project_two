import com.fasterxml.classmate.AnnotationConfiguration;
import com.morris.CreateServlet;
import com.morris.SpeedHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class test {
    public static void  main(String[] args){
        System.out.println("START TEST");
        // SETUP SPEED HIBERNATE TO WORK WITH A SPECIFIC TABLE
        SpeedHibernate speedHibernate = new SpeedHibernate("reimbursement");
        System.out.println(speedHibernate.queryReimbursementID(1).getAmount() + " TEST RUN");

        SpeedHibernate speedHibernate1 = new SpeedHibernate("User");
        System.out.println(speedHibernate1.queryUser(1).getUsername() + "USERNAME ");
        CreateServlet createServlet = new CreateServlet();
    }
}
