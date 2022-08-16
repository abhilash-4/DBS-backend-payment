package com.dbs.payment.backend.DTO;

public class TransactionItem {

    private String recAccountNumber;

    private String recAccountName;

    private Double currencyAmount;

    private Double transferFee;

    private Double inrAmount;


    public String getRecAccountNumber() {
        return recAccountNumber;
    }

    public void setRecAccountNumber(String recAccountNumber) {
        this.recAccountNumber = recAccountNumber;
    }

    public String getRecAccountName() {
        return recAccountName;
    }

    public void setRecAccountName(String recAccountName) {
        this.recAccountName = recAccountName;
    }

    public Double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public Double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(Double transferFee) {
        this.transferFee = transferFee;
    }

    public Double getInrAmount() {
        return inrAmount;
    }

    public void setInrAmount(Double inrAmount) {
        this.inrAmount = inrAmount;
    }
}
