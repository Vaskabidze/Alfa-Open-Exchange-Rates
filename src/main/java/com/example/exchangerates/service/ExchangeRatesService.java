package com.example.exchangerates.service;

import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.proxy.OpenExchangeRatesProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final OpenExchangeRatesProxy openExchangeRatesProxy;

    public String makeMeRich(String currency, String token, String base) {
        String historicalPath = LocalDate.now().minusDays(3).toString();

        ExchangeRate latestExchangeRate = openExchangeRatesProxy.getLatest(token, base);
        ExchangeRate historicalExchangeRate = openExchangeRatesProxy.getHistorical(historicalPath, token, base);

        Double valueLatest = latestExchangeRate.getRates().get(currency);
        Double valueHistorical = historicalExchangeRate.getRates().get(currency);

        if (valueLatest != null && valueHistorical != null){
            return valueLatest > valueHistorical ? "I am rich" : "I am not rich";
        } else {
            return "Currency not found";
        }



    }
}
