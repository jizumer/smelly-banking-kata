package com.jizumer;

import java.util.Date;
import java.util.List;

public class Account implements AccountService {

    List<Pair<Date, Integer>> transactions;

    public void deposit(int amount) {

    }

    public void withdraw(int amount) {

    }

    public void printStatement() {
        System.out.print("LOL");
    }
}
