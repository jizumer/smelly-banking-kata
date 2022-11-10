package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Pair<LocalDate, Integer>> transactions = new ArrayList<>();
    private List<Integer> balances = new ArrayList<>();

    private final Transactions transactionsWithAbstraction = new Transactions();

    public void deposit(LocalDate date, int amount) {
        updateAccount(date, amount);
    }

    private void updateAccount(LocalDate date, int amount) {
        transactionsWithAbstraction.addTransaction(date, amount);
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

        for (int i = 0; i < transactionsWithAbstraction.size(); i++) {
            System.out.println(transactionsWithAbstraction.get(i).getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\t||\t" +
                    transactionsWithAbstraction.get(i).getAmount() + "\t||\t" + calculateBalanceByTransaction(i));
        }
    }

    public double calculateAverageBalance() {
        return transactionsWithAbstraction
                .calculateAverageBalance();
    }

    public double calculateAverageBalanceWithBonuses() {
        double averageBalanceWithBonuses = 0;
        for (int i = 0; i < transactionsWithAbstraction.size(); i++) {

            averageBalanceWithBonuses += transactionsWithAbstraction.get(i).getAmount() *
                    (1 + Bonuses.calculateBonus(transactionsWithAbstraction.get(i).getDate().getYear()) / 100);
        }
        return averageBalanceWithBonuses / transactionsWithAbstraction.size();
    }

}
