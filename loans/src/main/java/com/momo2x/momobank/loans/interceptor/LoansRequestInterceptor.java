package com.momo2x.momobank.loans.interceptor;

import com.momo2x.momobank.loans.dto.LoansCrossCuttingConcernInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.momo2x.momobank.loans.constant.LoansConstants.Gateway.CORRELATION_ID;
import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoansRequestInterceptor implements HandlerInterceptor {

    private final LoansCrossCuttingConcernInfo info;

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
