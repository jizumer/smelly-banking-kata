package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Pair<LocalDate, Integer>> transactions = new ArrayList<>();
    private List<Integer> balances = new ArrayList<>();

    private List<Transaction> transactionsWithAbstraction = new ArrayList<>();

    public void deposit(LocalDate date, int amount) {
        updateAccount(date, amount);
    }

    private void updateAccount(LocalDate date, int amount) {
        balances.add(calculateBalance() + amount);
        transactions.add(new Pair<>(date, amount));
    }


    public void withdraw(LocalDate date, int amount) {
        updateAccount(date, -amount);
    }

    private Integer calculateBalance() {
        Integer previousBalance = 0;
        if (balances.size() != 0) {
            previousBalance = calculateCurrentBalance();
        }
        return previousBalance;
    }

    private Integer calculateCurrentBalance() {
        return balances.get(balances.size() - 1);
    }

    private Integer calculateBalanceByTransaction(int transactionNumber) {
        return balances.get(transactionNumber);
    }


    public void printStatement() {
        System.out.println("Date\t\t||\tAmount\t||\tBalance");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i).first().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\t||\t" +
                    transactions.get(i).second() + "\t||\t" + calculateBalanceByTransaction(i));
        }
    }

    public double calculateAverageBalance() {
        return transactions
                .stream().
                mapToDouble(Pair::second)
                .sum() / transactions.size();
    }

    public double calculateAverageBalanceWithBonuses() {
        double averageBalanceWithBonuses = 0;
        for (int i = 0; i < transactions.size(); i++) {
            averageBalanceWithBonuses += transactions.get(i).second() * (1 + calculateBonus(i) / 100);
        }
        return averageBalanceWithBonuses / transactions.size();
    }

    private Double calculateBonus(int transactionNumber) {

        return Bonuses.yearlyBonuses
                .stream()
                .filter(b ->
                        b.getYear()
                                .equals(transactions
                                        .get(transactionNumber)
                                        .first()
                                        .getYear()))
                .findFirst()
                .map(Bonus::getBonus)
                .orElse(1.0);
    }
}
