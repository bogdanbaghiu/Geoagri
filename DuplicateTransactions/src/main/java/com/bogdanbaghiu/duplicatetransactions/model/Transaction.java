package com.bogdanbaghiu.duplicatetransactions.model;

import java.util.Date;

public class Transaction {

    private int id;
    private String sourceAccount;
    private String targetAccount;
    private int amount;
    private Date time;

    public Transaction() {
    }

    public Transaction(String sourceAccount, String targetAccount, int amount, Date time) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.time = time;
    }

    public Transaction(int id, String sourceAccount, String targetAccount, int amount, Date time) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

