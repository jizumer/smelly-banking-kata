package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Pair<LocalDate, Integer>> transactions = new ArrayList<>();
    private List<Integer> balances = new ArrayList<>();

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

    public long calculateAverageBalance() {
        Integer averageBalance = 0;
        for (int i = 0; i < transactions.size(); i++) {
            averageBalance += transactions.get(i).second();
        }
        return averageBalance / transactions.size();
    }
}
