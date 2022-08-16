package com.dbs.payment.backend.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transactionid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customers customer;


    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currencycode;

//    @ManyToOne
//    @JoinColumn(name = "sender_bic")
//    private Banks senderBIC;

    @ManyToOne
    @JoinColumn(name = "receiver_bic")
    private Banks receiverBIC;

    @Column(name = "receiveraccountholdernumber")
    private String recAccountNumber;

    @Column(name = "receiveraccountholdername")
    private String recAccountName;


    @ManyToOne
    @JoinColumn(name = "transfer_type_code")
    private TransferType transferTypeCode;


    @ManyToOne
    @JoinColumn(name = "message_code")
    private MessageCode messageCode;

    @Column(name = "currencyamount")
    private Double currencyAmount;

    @Column(name = "transferfee")
    private Double transferFee;

    @Column(name = "INRAmount")
    private Double inrAmount;

    @Column(name = "transferdate")
    private Date transferDate;

    @Column(name ="status")
    private EStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Currency getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(Currency currencycode) {
        this.currencycode = currencycode;
    }

//    public Banks getSenderBIC() {
//        return senderBIC;
//    }
//
//    public void setSenderBIC(Banks senderBIC) {
//        this.senderBIC = senderBIC;
//    }

    public Banks getReceiverBIC() {
        return receiverBIC;
    }

    public void setReceiverBIC(Banks receiverBIC) {
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

    public TransferType getTransferTypeCode() {
        return transferTypeCode;
    }

    public void setTransferTypeCode(TransferType transferTypeCode) {
        this.transferTypeCode = transferTypeCode;
    }

    public MessageCode getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(MessageCode messageCode) {
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

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
