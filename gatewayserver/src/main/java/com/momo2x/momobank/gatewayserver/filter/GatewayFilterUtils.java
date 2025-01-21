package com.momo2x.momobank.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.CORRELATION_ID;
import static java.util.Optional.ofNullable;

@Component
public class GatewayFilterUtils {

    public String getCorrelationId(final HttpHeaders requestHeaders) {
        return ofNullable(requestHeaders.get(CORRELATION_ID))
                .orElse(List.of())
                .stream()
                .findFirst()
                .orElse(null);
    }

    public ServerWebExchange exchangeWithRequestHeader(
            final ServerWebExchange exchange,
            final String name,
            final String value
    ) {
        return exchange
                .mutate()
                .request(exchange
                        .getRequest()
                        .mutate()
                        .header(name, value)
                        .build())
                .build();
    }

    public ServerWebExchange exchangeWithCorrelationId(final ServerWebExchange exchange, final String correlationId) {
        return exchangeWithRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}
