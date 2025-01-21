package com.momo2x.momobank.loans.config;

import com.momo2x.momobank.loans.dto.LoansCrossCuttingConcernInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LoansCrossCuttingConcernConfig implements WebMvcConfigurer {

    final HandlerInterceptor loansRequestInterceptor;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public LoansCrossCuttingConcernInfo info() {
        return new LoansCrossCuttingConcernInfo();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(loansRequestInterceptor);
    }
}
