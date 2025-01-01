package com.momo2x.momobank.accounts.audit;

import com.momo2x.momobank.accounts.infra.UserManager;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.momo2x.momobank.accounts.audit.AccountsAuditor.BEAN_NAME;

@RequiredArgsConstructor
@Component(BEAN_NAME)
public class AccountsAuditor implements AuditorAware<String> {

    public static final String BEAN_NAME = "AccountsAuditor";

    private final UserManager user;

    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(user.getLoggedUser());
    }
}
