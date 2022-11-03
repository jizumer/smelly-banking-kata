package com.jizumer;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void shouldDepositACertainAmount(){
        Account account = new Account();
        account.deposit(5000);
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream statementStream = new PrintStream(inMemoryStream);
        System.setOut(statementStream);


        account.printStatement();

        assertEquals("LOL", inMemoryStream.toString());
    }

}