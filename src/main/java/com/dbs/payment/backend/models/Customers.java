package com.dbs.payment.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @Column(name = "customerid")
    private String id;

    @Column(name = "accountholdername")
    private String accHolderName;

    @Column(name="overdraftflag")
    private boolean overdraft;

    @Column(name = "clearbalance")
    private Double clearBalance;

    @Column(name = "customeraddress")
    private String address;

    @Column(name = "customercity")
    private String city;

    @Column(name = "customertype")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public boolean isOverdraft() {
        return overdraft;
    }

    public void setOverdraft(boolean overdraft) {
        this.overdraft = overdraft;
    }

    public Double getClearBalance() {
        return clearBalance;
    }

    public void setClearBalance(Double clearBalance) {
        this.clearBalance = clearBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
