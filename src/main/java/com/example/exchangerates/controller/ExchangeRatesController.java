package com.example.exchangerates.controller;

import com.example.exchangerates.model.ExchangeRate;
import com.example.exchangerates.model.GiphyDto;
import com.example.exchangerates.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

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
    public GiphyDto makeMeRich(@RequestParam String currency) {
        return exchangeRatesService.makeMeRich(currency);
    }

    @GetMapping("/web")
    public ModelAndView getCodeListWeb(HttpServletResponse response, ModelAndView model) {
        response.addHeader("Content-Type", "text/html");
        model.setViewName("main");
        return model;
    }
}
