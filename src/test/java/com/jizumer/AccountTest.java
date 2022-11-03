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

        assertEquals("Date\t\t||\tAmount\t||\tBalance\n05/07/2022\t||\t1000\t||\t1000", inMemoryStream.toString());
    }

}