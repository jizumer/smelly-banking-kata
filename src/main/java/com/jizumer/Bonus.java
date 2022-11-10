package com.jizumer;

public class Bonus {
    private final Integer year;
    private final Double bonus;

    public Bonus(Integer year, Double bonus) {
        this.year = year;
        this.bonus = bonus;
    }

    public Integer getYear() {
        return year;
    }

    public Double getBonus() {
        return (bonus < 0) ? 0.0 : bonus;
    }
}
