package com.jizumer;

import java.util.List;

public class Bonuses {

    private static final List<Pair<Integer, Double>> yearlyBonuses = List.of(
        new Pair(2018, 0.5),
        new Pair(2019, -0.75),
        new Pair(2020, 1.0),
        new Pair(2021, 1.25),
        new Pair(2022, 1.5),
        new Pair(2023, 1.75)
    );

    public static Double calculateBonus(int year) {
        return yearlyBonuses.stream()
            .filter(yearlyBonus -> yearlyBonus.first().equals(year))
            .findFirst()
            .map(Pair::second)
            .map(b -> b < 0 ? 0.0 : b)
            .orElse(1.0);
    }

}
