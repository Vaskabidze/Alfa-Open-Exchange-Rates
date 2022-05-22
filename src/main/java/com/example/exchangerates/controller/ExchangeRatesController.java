package com.example.exchangerates.controller;

import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.proxy.OpenExchangeRatesProxy;
import com.example.exchangerates.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ExchangeRatesController {

    private final OpenExchangeRatesProxy openExchangeRatesProxy;
    private final ExchangeRatesService exchangeRatesService;

    @Value("${open-exchange-rates.token}")
    private String token;

    @Value("${open-exchange-rates.base}")
    private String base;

    @GetMapping("/getLatest")
    public ExchangeRate getLatest() {
        return openExchangeRatesProxy.getLatest(token, base);
    }

    @GetMapping("/getHistorical") //TODO: change 3 to 1!
    public ExchangeRate getHistorical() {
        String path = LocalDate.now().minusDays(3).toString();
        return openExchangeRatesProxy.getHistorical(path, token, base);
    }

    @GetMapping("/makeMeRich")
    public String makeMeRich(@RequestParam String currency) {
        return exchangeRatesService.makeMeRich(currency, token, base);
    }


}
