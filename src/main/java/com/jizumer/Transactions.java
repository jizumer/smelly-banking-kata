package com.jizumer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(LocalDate date, int amount) {
        transactions.add(new Transaction(date, amount));
    }

    public int size() {
        return transactions.size();
    }

    public Transaction get(int i) {
        return transactions.get(i);
    }

    public double calculateAverageBalance() {
        return transactions
                .stream().
                mapToDouble(Transaction::getAmount)
                .sum() / size();
    }
}
