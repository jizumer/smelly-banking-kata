package com.jizumer;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void shouldDepositACertainAmount() {
        Account account = new Account();
        account.deposit(LocalDate.of(2022, 7, 5), 1000);
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream statementStream = new PrintStream(inMemoryStream);
        System.setOut(statementStream);


        account.printStatement();

        assertEquals("Date\t\t||\tAmount\t||\tBalance\n05/07/2022\t||\t1000\t||\t1000\n", inMemoryStream.toString());
    }

    @Test
    public void shouldDepositAMoreThanOneAmount() {
        Account account = new Account();
        account.deposit(LocalDate.of(2022, 7, 5), 1000);
        account.deposit(LocalDate.of(2022, 8, 1), 1000);
        account.deposit(LocalDate.of(2022, 9, 15), 1000);
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream statementStream = new PrintStream(inMemoryStream);
        System.setOut(statementStream);


        account.printStatement();

        assertEquals("Date\t\t||\tAmount\t||\tBalance\n05/07/2022\t||\t1000\t||\t1000\n01/08/2022\t||\t1000\t||\t2000\n15/09/2022\t||\t1000\t||\t3000\n", inMemoryStream.toString());
    }

    @Test
    public void shouldWithdrawACertainAmount() {
        Account account = new Account();
        account.deposit(LocalDate.of(2022, 7, 5), 1000);
        account.deposit(LocalDate.of(2022, 8, 1), 2000);
        account.withdraw(LocalDate.of(2022, 9, 15), 500);
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream statementStream = new PrintStream(inMemoryStream);
        System.setOut(statementStream);


        account.printStatement();

        assertEquals("Date\t\t||\tAmount\t||\tBalance\n05/07/2022\t||\t1000\t||\t1000\n01/08/2022\t||\t2000\t||\t3000\n15/09/2022\t||\t-500\t||\t2500\n", inMemoryStream.toString());
    }


    @Test
    public void shouldCalculateAverageBalance() {
        Account account = new Account();
        account.deposit(LocalDate.of(2022, 7, 5), 1000);
        account.deposit(LocalDate.of(2022, 8, 1), 2000);
        account.withdraw(LocalDate.of(2022, 9, 15), 500);
        assertEquals(833.33, account.calculateAverageBalance(), 0.01);
    }

    @Test
    public void shouldCalculateAverageBalanceWithBonuses() {
        Account account = new Account();
        account.deposit(LocalDate.of(2020, 7, 5), 1000);//1010
        account.deposit(LocalDate.of(2021, 8, 1), 2000);//2025
        account.withdraw(LocalDate.of(2022, 9, 15), 500);//507.5
        assertEquals(842.5, account.calculateAverageBalanceWithBonuses(), 0.01);
    }


    @Test
    public void shouldCalculateAverageBalanceWithBonusesIgnoringNegativeBonuses() {
        Account account = new Account();
        account.deposit(LocalDate.of(2019, 1, 1), 1500);//1500 since negative bonus
        account.deposit(LocalDate.of(2020, 7, 5), 1000);//1010
        account.deposit(LocalDate.of(2021, 8, 1), 2000);//2025
        account.withdraw(LocalDate.of(2022, 9, 15), 500);//507.5
        assertEquals(1006.875, account.calculateAverageBalanceWithBonuses(), 0.01);
    }

}