package com.example.exchangerates.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyProxy {

    @GetMapping("")
    String getGif(@RequestParam("api_key") String api_key, @RequestParam("s") String search);
}
