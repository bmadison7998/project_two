package com.morris;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Iterator;

public class SpeedHibernate {
    Configuration config = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory factory = config.buildSessionFactory();
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    public void insertintoReimbursements(String username,String password,String email,boolean isManager){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setManager(isManager);
        user.setId(countTable("reimbursements") + 1);
        session.persist(user);
        //transaction.commit();
    }
    public void insertintoUser(String username,String password,String email,boolean isManager){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setManager(isManager);
        user.setId(countTable("User") + 1);
        session.persist(user);
        //transaction.commit();
    }
    public User query(String tablename,String username){
        // returns the first object that is selected via username
        // if there are multiple usernames then it will return the first
        Query<User> query = session.createQuery("from :tablename where username = :username", User.class);
        query.setParameter("username",username);
        query.setParameter("tablename",tablename);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
        nullcheck(output);
        return output;
    }
    public User query(String tablename, int id){
        // the "from user" user needs to match the user class
        Query<User> query = session.createQuery("from :tablename where ID = :id", User.class);
        query.setParameter("id",id);
        query.setParameter("tablename",tablename);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
        nullcheck(output);
        return output;
    }
    public int countTable(String tablename){
        // RETURNS THE NUMBER OF ROWS IN A SPECIFIC TABLE
        Query<User> query = session.createQuery("from :tablename", User.class);
        query.setParameter("tablename",tablename);
        Iterator<User> count = query.stream().iterator();
        int counter = 1;
        for(boolean hasnext = true; hasnext;) {
            // THIS WILL ITERATE THROUGH EACH ELEMENT IN THE TABLE
            // AND COUNT THEM
            try {
                if (count.hasNext()) {
                    counter += 1;
                }
            } catch (Exception e) {
                hasnext = false;
            }
        }
        return counter;
    }
    void nullcheck(User user){
        if(user == null){
            System.out.println("QUERY DID NOT FIND ROW WITH PARAMETER, USERNAME " + user + " REMEMBER TO ADD HANDLING FOR NULL OBJECTS.");
        }
    }
    public void close(){
        session.close();
    }
}
