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

}