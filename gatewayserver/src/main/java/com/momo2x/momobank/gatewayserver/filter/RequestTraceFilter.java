package com.momo2x.momobank.gatewayserver.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.CORRELATION_ID;
import static java.util.Objects.nonNull;
import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private final GatewayFilterUtils utils;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final var requestHeaders = exchange.getRequest().getHeaders();
        var filterExchange = exchange;

        if (isCorrelationIdPresent(requestHeaders)) {
            log.debug("{} found in RequestTraceFilter : {}", CORRELATION_ID, utils.getCorrelationId(requestHeaders));
        } else {
            final var correlationId = generateCorrelationId();
            filterExchange = utils.exchangeWithCorrelationId(exchange, correlationId);
            log.debug("{} generated in RequestTraceFilter : {}", CORRELATION_ID, correlationId);
        }

        return chain.filter(filterExchange);
    }

    private boolean isCorrelationIdPresent(final HttpHeaders requestHeaders) {
        return nonNull(utils.getCorrelationId(requestHeaders));
    }

    private String generateCorrelationId() {
        return randomUUID().toString();
    }

}
