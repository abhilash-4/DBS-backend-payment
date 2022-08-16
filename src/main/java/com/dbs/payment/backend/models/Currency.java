package com.dbs.payment.backend.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Currency {

    @Id
    @Column(name = "currencycode")
    private String currencyCode;


    @Column(name = "currencyname")
    private String currencyName;

    @Column(name = "conversionrate")
    private Double conversionRate;


    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
