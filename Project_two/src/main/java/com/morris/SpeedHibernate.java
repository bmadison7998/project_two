package com.morris;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.util.Iterator;

import static java.lang.Double.isNaN;

public class SpeedHibernate {
    Configuration config;
    SessionFactory factory;
    Session session;
    Transaction transaction;

    public SpeedHibernate(String tablename) {
        config = new Configuration().configure(tablename.toLowerCase() + ".cfg.xml");

        factory = config.buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    public void insertintoUser(String username, String password, String email, boolean isManager) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setManager(false);
        user.setId(countTableUser());
        session.persist(user);
        // transaction.commit();
    }
    public void updateUser(int id,String username, String password, String email){
        User user = session.get(User.class, id);
        user.setUsername(username);
        user.setEmail((email));
        session.persist(user);
        transaction.commit();
    }

    public User queryUser(String username) {
        // returns the first object that is selected via username
        // if there are multiple usernames then it will return the first
        Query<User> query = session.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
        System.out.println("GOT " + output + " FROM USERNAME: " + username + ". SPEEDHIBERNATE");
        nullcheck(output);
        return output;
    }

    public User queryUser(int id) {
        // the "from user" user needs to match the user class
        Query<User> query = session.createQuery("from User where ID = :id", User.class);
        query.setParameter("id", id);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
        nullcheck(output);
        return output;
    }

    public int countTableUser() {
        // RETURNS THE NUMBER OF ROWS IN A SPECIFIC TABLE
        Query query = session.createQuery("SELECT COUNT(e) FROM User e");
        Iterator output = query.stream().iterator();
        return output.next().hashCode();
    }

    public void insertintoReimbursement(int _userid, double _amount) {
        reimbursement burse = new reimbursement();
        burse.setId(_userid);
        burse.setBurseID(countTableReimbursement());
        burse.setApproved(false);
        burse.setAmount(_amount);
        System.out.println(burse.getAmount());
        session.persist(burse);
        transaction.commit();
    }

    public reimbursement queryReimbursementID(int id) {
        // the "from user" user needs to match the user class
        Query<reimbursement> query = session.createQuery("from reimbursement where ID = :id", reimbursement.class);
        query.setParameter("id", id);
        // the "from user" user needs to match the user class
        reimbursement output = query.uniqueResult();
        nullcheckburse(output);
        return output;
    }

    public reimbursement queryReimbursement() {
        // the "from user" user needs to match the user class
        Query<reimbursement> query = session.createQuery("from reimbursement", reimbursement.class);
        // the "from user" user needs to match the user class
        reimbursement output = query.uniqueResult();
        nullcheckburse(output);
        return output;
    }

    public reimbursement queryReimbursementBurseID(int id) {
        // the "from user" user needs to match the user class
        Query<reimbursement> query = session.createQuery("from reimbursement where burseID = :id", reimbursement.class);
        query.setParameter("id", id);
        // the "from user" user needs to match the user class
        reimbursement output = query.uniqueResult();
        nullcheckburse(output);
        return output;
    }

    public int countTableReimbursement() {
        // RETURNS THE NUMBER OF ROWS IN A SPECIFIC TABLE
        Query query = session.createQuery("SELECT COUNT(e) FROM reimbursement e");
        Iterator output = query.stream().iterator();
        return output.next().hashCode();
    }
    public void updateReimbursement(int burseID,boolean approved){
        System.out.println(approved + " NEW BOOLEAN VALUE " + burseID + " BURSE ID");
        reimbursement burse = session.get(reimbursement.class, burseID);
        System.out.println(burse.getAmount() + " THE AMOUNT");
        try {
            burse.setApproved(approved);
            session.persist(burse);
            transaction.commit();
        }
        catch (Exception e){}
        }
    void nullcheckburse(reimbursement user) {
        if (user == null) {
            System.out.println(
                    "QUERY DID NOT FIND ROW WITH PARAMETER, " + user + " REMEMBER TO ADD HANDLING FOR NULL OBJECTS.");
        }
    }

    void nullcheck(User user) {
        if (user == null) {
            System.out.println("QUERY DID NOT FIND ROW WITH PARAMETER, USERNAME " + user
                    + " REMEMBER TO ADD HANDLING FOR NULL OBJECTS.");
        }
    }

    public void close() {
        session.close();
    }
}
