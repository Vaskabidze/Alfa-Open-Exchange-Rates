package com.example.exchangerates.service;

import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.model.Giphy;
import com.example.exchangerates.model.GiphyData;
import com.example.exchangerates.proxy.GiphyProxy;
import com.example.exchangerates.proxy.OpenExchangeRatesProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    @Value("${open-exchange-rates.token}")
    private String token;

    @Value("${open-exchange-rates.base}")
    private String base;

    @Value("${giphy.token}")
    private String apiKeyGiphy;

    private final OpenExchangeRatesProxy openExchangeRatesProxy;
    private final GiphyProxy giphyProxy;

    private final String RICH = "rich";
    private final String BROKE = "broke";

    public GiphyData makeMeRich(String currency) {
        String historicalPath = LocalDate.now().minusDays(3).toString();

        ExchangeRate latestExchangeRate = openExchangeRatesProxy.getLatest(token, base);
        ExchangeRate historicalExchangeRate = openExchangeRatesProxy.getHistorical(historicalPath, token, base);

        Double valueLatest = latestExchangeRate.getRates().get(currency);
        Double valueHistorical = historicalExchangeRate.getRates().get(currency);


        return valueLatest > valueHistorical ? giphyProxy.getGif(apiKeyGiphy, RICH).data : giphyProxy.getGif(apiKeyGiphy, BROKE).data;

    }

    public ExchangeRate getLatest() {
        return openExchangeRatesProxy.getLatest(token, base);
    }

    public ExchangeRate getHistorical() {
        String path = LocalDate.now().minusDays(3).toString();
        return openExchangeRatesProxy.getHistorical(path, token, base);
    }
}
