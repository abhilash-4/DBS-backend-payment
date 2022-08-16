package com.dbs.payment.backend.DTO;

import com.dbs.payment.backend.models.Banks;
import com.dbs.payment.backend.models.MessageCode;
import com.dbs.payment.backend.models.TransferType;



public class TransactionDTO {

    private String customerId;

    private String currencyCode;

    private String receiverBIC;

    private String recAccountNumber;

    private String recAccountName;

    private String transferTypeCode;

    private String messageCode;

    private Double currencyAmount;

    private Double transferFee;

    private Double inrAmount;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getReceiverBIC() {
        return receiverBIC;
    }

    public void setReceiverBIC(String receiverBIC) {
        this.receiverBIC = receiverBIC;
    }

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

    public String getTransferTypeCode() {
        return transferTypeCode;
    }

    public void setTransferTypeCode(String transferTypeCode) {
        this.transferTypeCode = transferTypeCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
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
