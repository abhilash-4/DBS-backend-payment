package com.dbs.payment.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfertype")
public class TransferType {

    @Id
    @Column(name = "transfercode")
    private String transferCode;

    @Column(name = "transferdescription")
    private String transferDescription;


    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    public String getTransferDescription() {
        return transferDescription;
    }

    public void setTransferDescription(String transferDescription) {
        this.transferDescription = transferDescription;
    }
}
