package ru.wallet.service;

import java.util.Date;

public interface RegUser {
    void deposit(double amount, Date date);

    void withdraw(double amount, Date date);

    void takeCredit(double amount, Date date);

    void payCredit(double amount, Date date);
}
