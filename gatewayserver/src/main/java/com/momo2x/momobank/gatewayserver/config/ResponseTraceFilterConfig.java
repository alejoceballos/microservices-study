package com.momo2x.momobank.gatewayserver.config;

import com.momo2x.momobank.gatewayserver.filter.GatewayFilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import static com.momo2x.momobank.gatewayserver.constant.GatewayConstants.CORRELATION_ID;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ResponseTraceFilterConfig {

    private final GatewayFilterUtils utils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) ->
                chain
                        .filter(exchange)
                        .then(Mono.fromRunnable(() -> {
                            final var correlationId = utils.getCorrelationId(exchange.getRequest().getHeaders());
                            log.debug("Updated the correlation id to the outbound headers: {}", correlationId);
                            exchange
                                    .getResponse()
                                    .getHeaders()
                                    .add(CORRELATION_ID, correlationId);
                        }));
    }

}
