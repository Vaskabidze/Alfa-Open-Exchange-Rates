package com.example.exchangerates.controller;

import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.model.GiphyData;
import com.example.exchangerates.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    @GetMapping("/getLatest")
    public ExchangeRate getLatest() {
        return exchangeRatesService.getLatest();
    }

    @GetMapping("/getHistorical") //TODO: change 3 to 1!
    public ExchangeRate getHistorical() {
        return exchangeRatesService.getHistorical();
    }

    @GetMapping("/makeMeRich")
    public GiphyData makeMeRich(@RequestParam String currency) {
        return exchangeRatesService.makeMeRich(currency);
    }
}
