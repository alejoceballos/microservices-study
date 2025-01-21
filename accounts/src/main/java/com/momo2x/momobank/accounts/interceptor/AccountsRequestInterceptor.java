package com.momo2x.momobank.accounts.interceptor;

import com.momo2x.momobank.accounts.dto.AccountsCrossCuttingConcernInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.momo2x.momobank.accounts.constant.AccountsConstants.Gateway.CORRELATION_ID;
import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountsRequestInterceptor implements HandlerInterceptor {

    private final AccountsCrossCuttingConcernInfo info;

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            @Nullable final HttpServletResponse response,
            @Nullable final Object handler) {
        final var message = "[" + request.getMethod() + "] " + request.getRequestURI() + ":";

        final var correlationId = request.getHeader(CORRELATION_ID);

        if (nonNull(correlationId)) {
            info.setCorrelationId(correlationId);
            log.debug("{} Correlation ID '{}'='{}'", message, CORRELATION_ID, correlationId);
        } else {
            log.warn("{} {}", message, "No correlation ID in request!");
        }

        return true;
    }
}
