package com.morris;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Stream;

public class SpeedHibernate {

    Configuration config = new Configuration().configure("hibernate.cfg.xml");
    SessionFactory factory = config.buildSessionFactory();
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();
    public void insertintoUser(String username,String password,String email,boolean isManager){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setManager(isManager);
        user.setId(1);
        session.persist(user);
        //transaction.commit();
    }
    public User query(int id){
        // the "from user" user needs to match the user class
        return session.get(User.class, id);
    }
    public void close(){
        session.close();
    }
}
