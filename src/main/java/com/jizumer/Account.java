package com.jizumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Pair<LocalDate, Integer>> transactions = new ArrayList<>();
    private Integer balance = 0;

    public void deposit(LocalDate date, int amount) {
        balance += amount;
        transactions.add(new Pair<>(date, amount));
    }

    public void withdraw(LocalDate date, int amount) {

    }

    public void printStatement() {
        System.out.println("Date\t\t||\tAmount\t||\tBalance");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.print(transactions.get(i).first().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "\t||\t" + transactions.get(i).second() + "\t||\t" + balance);
        }
    }
}
