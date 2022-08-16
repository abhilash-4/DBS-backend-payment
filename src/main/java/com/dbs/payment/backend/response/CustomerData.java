package com.dbs.payment.backend.response;

import javax.persistence.Column;
import javax.persistence.Id;

public class CustomerData {


    private String id;


    private String accHolderName;


    private boolean overdraft;


    private Double clearBalance;


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
}
