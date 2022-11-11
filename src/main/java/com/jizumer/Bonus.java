package com.jizumer;

public class Bonus {

    private final int year;
    private final Double amount;

    public Bonus(int year, Double amount) {
        this.year = year;
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public Double getAmount() {
        return amount;
    }
}
