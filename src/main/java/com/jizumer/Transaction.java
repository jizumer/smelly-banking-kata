package com.jizumer;

import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final Integer amount;

    private final Integer balance;


    public Transaction(LocalDate date, Integer amount, Integer balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getBalance() {
        return balance;
    }
}
