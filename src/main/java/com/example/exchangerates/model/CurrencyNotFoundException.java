package com.example.exchangerates.model;

public class CurrencyNotFoundException extends RuntimeException {
    public static final String msgPrefix = "Валюта не нейдена: ";

    public CurrencyNotFoundException(String msg) {
        super(msg);
    }
}
