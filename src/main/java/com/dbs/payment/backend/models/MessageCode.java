package com.dbs.payment.backend.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class MessageCode {

    @Id
    private String messagecode;

    @NotNull
    private String instruction;


    public String getMessagecode() {
        return messagecode;
    }

    public void setMessagecode(String messagecode) {
        this.messagecode = messagecode;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
