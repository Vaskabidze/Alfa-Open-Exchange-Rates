package com.example.exchangerates.model;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRate {
    public String disclaimer;
    public String license;
    public int timestamp;
    public String base;
    public Map<String, Double> rates;
}
