package com.morris;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.Iterator;

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
        user.setManager(isManager);
        user.setId(countTableUser() + 1);
        session.persist(user);
        // transaction.commit();
    }

    public User queryUser(String username) {
        // returns the first object that is selected via username
        // if there are multiple usernames then it will return the first
        Query<User> query = session.createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        // the "from user" user needs to match the user class
        User output = query.uniqueResult();
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
        Query<User> query = session.createQuery("from User", User.class);
        Iterator<User> count = query.stream().iterator();
        int counter = 1;
        for (boolean hasnext = true; hasnext;) {
            // THIS WILL ITERATE THROUGH EACH ELEMENT IN THE TABLE
            // AND COUNT THEM
            try {
                if (count.hasNext()) {
                    counter += 1;
                } else {
                    hasnext = false;
                }
            } catch (Exception e) {
                hasnext = false;
            }
        }
        return counter;
    }

    public void insertintoReimbursement(int _userid, double _amount) {
        reimbursement user = new reimbursement();
        user.setId(_userid);
        user.setBurseID(countTableReimbursement() + 1);
        // WE WANT THE APPROVED VALUE TO BE NULL
        user.setAmount(_amount);
        session.persist(user);
        // transaction.commit();
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
        Query<reimbursement> query = session.createQuery("from reimbursement", reimbursement.class);
        Iterator<reimbursement> count = query.stream().iterator();
        int counter = 1;
        for (boolean hasnext = true; hasnext;) {
            // THIS WILL ITERATE THROUGH EACH ELEMENT IN THE TABLE
            // AND COUNT THEM
            try {
                if (count.hasNext()) {
                    counter += 1;
                } else {
                    hasnext = false;
                }
            } catch (Exception e) {
                hasnext = false;
            }
        }
        return counter;
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
