package com.example.exchangerates.proxy;

import com.example.exchangerates.model.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy", url = "${giphy.token.url}")
public interface GiphyProxy {

    @GetMapping("")
    Giphy getGif(@RequestParam("api_key") String api_key, @RequestParam("s") String search);
}
