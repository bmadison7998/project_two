package com.morris;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

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
    public List<User> query(){
        // the "from user" user needs to match the user class
        org.hibernate.query.Query<User> query = session.createQuery("from User", User.class);
        return query.list();
    }
    public List<User> query(String where){
        // the "from user" user needs to match the user class
        String whereat = " ";
        whereat += where;
        org.hibernate.query.Query<User> query = session.createQuery("from User" + whereat, User.class);
        return query.list();
    }
    public void close(){
        session.close();
    }
}
