package com.morris;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity (name="reimbursement")
public class reimbursement {
    @Id
    private int ID;
    private int burseID;
    private double amount;
    private boolean approved;


    // insert data through the constructor
    public reimbursement(int _id,int _burseid,double _amount,boolean _approved) {
        this.ID = _id;
        this.burseID = _burseid;
        this.amount = _amount;
        this.approved = _approved;
    }
    public reimbursement(){}


    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getBurseID() {
        return burseID;
    }
    public void setBurseID(int _burseid){this.burseID = _burseid;}
    public double getAmount(){return this.amount;}
    public void setAmount(double _amount){this.amount = _amount;}
    public boolean getApproved(){return this.approved;}
    public void setApproved(boolean test){this.approved = test;}

}
