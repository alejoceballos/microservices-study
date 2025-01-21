package com.momo2x.momobank.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.CircuitBreaker.Fallback.URL;
import static reactor.core.publisher.Mono.just;

@RestController
public class FallbackController {

    @RequestMapping(URL)
    public Mono<String> contactSupport() {
        return just("An error occurred. Please try after some time or contact support team!!!");
    }

}
