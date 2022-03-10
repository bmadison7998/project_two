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
        SpeedHibernate speedHibernate = new SpeedHibernate();
        System.out.println(speedHibernate.query(1) + " TEST RUN");
        CreateServlet createServlet = new CreateServlet();
    }
}
