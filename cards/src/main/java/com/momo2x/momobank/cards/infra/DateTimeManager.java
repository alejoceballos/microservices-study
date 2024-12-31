package com.momo2x.momobank.cards.infra;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateTimeManager {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
