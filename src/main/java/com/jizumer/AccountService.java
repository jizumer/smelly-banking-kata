package com.jizumer;

import java.time.LocalDate;

public interface AccountService {
    void deposit(LocalDate date, int amount);

    void withdraw(LocalDate date, int amount);

    void printStatement();
}
