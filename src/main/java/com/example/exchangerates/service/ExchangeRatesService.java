package com.example.exchangerates.service;

import com.example.exchangerates.model.CurrencyNotFoundException;
import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.model.GiphyDto;
import com.example.exchangerates.proxy.GiphyProxy;
import com.example.exchangerates.proxy.OpenExchangeRatesProxy;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

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


    public GiphyDto makeMeRich(String currency) {
        String historicalPath = LocalDate.now().minusDays(1).toString();

        if (!openExchangeRatesProxy.getCurrencies(token).containsKey(currency)) {
            throw new CurrencyNotFoundException(CurrencyNotFoundException.msgPrefix + currency);
        }

        ExchangeRate latestExchangeRate = openExchangeRatesProxy.getLatest(token, base);
        ExchangeRate historicalExchangeRate = openExchangeRatesProxy.getHistorical(historicalPath, token, base);

        Double valueLatest = latestExchangeRate.getRates().get(currency);
        Double valueHistorical = historicalExchangeRate.getRates().get(currency);

        return valueLatest > valueHistorical ? parseJson(giphyProxy.getGif(apiKeyGiphy, RICH)) : parseJson(giphyProxy.getGif(apiKeyGiphy, BROKE));

    }

    public ExchangeRate getLatest() {
        return openExchangeRatesProxy.getLatest(token, base);
    }

    public ExchangeRate getHistorical() {
        String path = LocalDate.now().minusDays(1).toString();
        return openExchangeRatesProxy.getHistorical(path, token, base);
    }

    public Map<String, String> availableCurrencies() {
        return openExchangeRatesProxy.getCurrencies(token);
    }

    private GiphyDto parseJson(String json) {
        JSONObject objRes = new JSONObject(json);
        val url = objRes.getJSONObject("data").getJSONObject("images").getJSONObject("downsized").get("url").toString();
        val title = objRes.getJSONObject("data").get("title").toString();

        return new GiphyDto(url, title);
    }
}
