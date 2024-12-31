package com.momo2x.momobank.cards.infra;

import org.springframework.stereotype.Component;

@Component
public class UserManager {

    public String getLoggedUser() {
        return "svcuser_cards";
    }

}
