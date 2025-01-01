package com.momo2x.momobank.cards.audit;

import com.momo2x.momobank.cards.infra.UserManager;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.momo2x.momobank.cards.audit.CardsAuditor.BEAN_NAME;

@RequiredArgsConstructor
@Component(BEAN_NAME)
public class CardsAuditor implements AuditorAware<String> {

    public static final String BEAN_NAME = "CardsAuditor";

    private final UserManager user;

    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(user.getLoggedUser());
    }
}
