package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Pair<LocalDate, Integer>> transactions = new ArrayList<>();
    private List<Integer> balances = new ArrayList<>();

    private List<Pair<Integer, Double>> yearlyBonuses = List.of(
            new Pair(2018, 0.5),
            new Pair(2019, -0.75),
            new Pair(2020, 1.0),
            new Pair(2021, 1.25),
            new Pair(2022, 1.5),
            new Pair(2023, 1.75)
    );

    public void deposit(LocalDate date, int amount) {
        Integer previousBalance = 0;
        if (balances.size() != 0) {
            previousBalance = balances.get(balances.size() - 1);
        }
        balances.add(previousBalance + amount);
        transactions.add(new Pair<>(date, amount));
    }

    public void withdraw(LocalDate date, int amount) {
        Integer previousBalance = 0;
        if (balances.size() != 0) {
            previousBalance = balances.get(balances.size() - 1);
        }
        balances.add(previousBalance - amount);
        transactions.add(new Pair<>(date, -amount));
    }

    public void printStatement() {
        System.out.println("Date\t\t||\tAmount\t||\tBalance");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).first().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\t||\t" +
                    transactions.get(i).second() + "\t||\t" + balances.get(i));
        }
    }

    public double calculateAverageBalance() {
        double averageBalance = 0;
        for (int i = 0; i < transactions.size(); i++) {
            averageBalance += transactions.get(i).second();
        }
        return averageBalance / transactions.size();
    }

    public double calculateAverageBalanceWithBonuses() {
        return accumulateBalance() / transactions.size();
    }

    private double accumulateBalance() {
        double accumulatedBalance = 0;
        for (int i = 0; i < transactions.size(); i++) {
            int year = transactions.get(i).first().getYear();
            Integer amount = transactions.get(i).second();
            accumulatedBalance += amount * (1 + calculateBonus(year) / 100);
        }
        return accumulatedBalance;
    }

    private Double calculateBonus(int year) {
        Double bonus = 1.0;
        for (int j = 0; j < yearlyBonuses.size(); j++) {

            if (yearlyBonuses.get(j).first() == year) {
                bonus = yearlyBonuses.get(j).second();
                break;
            }
        }
        if (bonus < 0) {
            bonus = 0.0;
        }
        return bonus;
    }


}
