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
    public void insertintoUser(int id,String username,String password,String email,boolean isManager){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setManager(isManager);
        user.setId(id);
        session.persist(user);
        //transaction.commit();
    }
    public User query(int id){
        // the "from user" user needs to match the user class
        User output = session.get(User.class, id);
        nullcheck(output);
        return output;
    }
    public int countTable(String tablename){
        // RETURNS THE NUMBER OF ROWS IN A SPECIFIC TABLE
        Query<User> query = session.createQuery("from User", User.class);
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
    public User query(String username){
        // returns the first object that is selected via username
        // if there are multiple usernames then it will return the first
        Query<User> query = session.createQuery("from User where username = :username", User.class).setParameter("username",username);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
        nullcheck(output);
        return output;
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
