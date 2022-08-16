package com.dbs.payment.backend.response;

public class TransactionResponse {

    private Long transactionId;

    private String recAccName;

    private String amount;

    private String date;

    private String status;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getRecAccName() {
        return recAccName;
    }

    public void setRecAccName(String recAccName) {
        this.recAccName = recAccName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
