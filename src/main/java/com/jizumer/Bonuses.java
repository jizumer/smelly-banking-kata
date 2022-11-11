package com.jizumer;

import java.util.List;

public class Bonuses {

    private static final List<Bonus> yearlyBonuses = List.of(
        new Bonus(2018, 0.5),
        new Bonus(2019, -0.75),
        new Bonus(2020, 1.0),
        new Bonus(2021, 1.25),
        new Bonus(2022, 1.5),
        new Bonus(2023, 1.75)
    );

    public static Double calculateBonus(int year) {
        return yearlyBonuses.stream()
            .filter(yearlyBonus -> yearlyBonus.getYear() == year)
            .findFirst()
            .map(Bonus::getAmount)
            .map(b -> b < 0 ? 0.0 : b)
            .orElse(1.0);
    }

}
