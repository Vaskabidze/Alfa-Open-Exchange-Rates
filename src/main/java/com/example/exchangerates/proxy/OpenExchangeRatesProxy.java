package com.example.exchangerates.proxy;

import com.example.exchangerates.model.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "open-exchange-rates", url = "${open-exchange-rates.url}")
public interface OpenExchangeRatesProxy {

    @GetMapping("/latest.json")
    ExchangeRate getLatest(@RequestParam("app_id") String app_id, @RequestParam("base") String base);

    @GetMapping("/historical/{term}.json")
    ExchangeRate getHistorical(@PathVariable String term, @RequestParam("app_id") String app_id, @RequestParam("base") String base);

    @GetMapping("/currencies.json")
    Map<String, String> getCurrencies(@RequestParam("app_id") String app_id);

}
