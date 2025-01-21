package com.momo2x.momobank.cards.config;

import com.momo2x.momobank.cards.dto.CardsCrossCuttingConcernInfo;
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
public class CardsCrossCuttingConcernConfig implements WebMvcConfigurer {

    final HandlerInterceptor cardsRequestInterceptor;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CardsCrossCuttingConcernInfo correlationId() {
        return new CardsCrossCuttingConcernInfo();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(cardsRequestInterceptor);
    }
}
