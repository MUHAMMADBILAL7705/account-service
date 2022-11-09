package com.account.exception;

public class AccountNotFoundException extends Exception {
    private final static String MESSAGE = "Unable to find account";

    public AccountNotFoundException() {
        super(MESSAGE);
    }
}
