package com.jizumer;

import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final Integer amount;

    public Transaction(LocalDate date, Integer amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getAmount() {
        return amount;
    }
}
