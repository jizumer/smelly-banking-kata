package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {


    private List<Integer> balances = new ArrayList<>();

    private final Transactions transactions = new Transactions();

    public void deposit(LocalDate date, int amount) {
        updateAccount(date, amount);
    }

    private void updateAccount(LocalDate date, int amount) {
        transactions.addTransaction(date, amount);
        balances.add(calculateBalance() + amount);
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
            System.out.println(transactions.get(i).getDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\t||\t" +
                    transactions.get(i).getAmount() + "\t||\t" + calculateBalanceByTransaction(i));
        }
    }

    public double calculateAverageBalance() {
        return transactions
                .calculateAverageBalance();
    }

    public double calculateAverageBalanceWithBonuses() {
        double averageBalanceWithBonuses = 0;
        for (int i = 0; i < transactions.size(); i++) {

            averageBalanceWithBonuses += transactions.get(i).getAmount() *
                    (1 + Bonuses.calculateBonus(transactions.get(i).getDate().getYear()) / 100);
        }
        return averageBalanceWithBonuses / transactions.size();
    }

}
