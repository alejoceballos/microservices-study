package com.momo2x.momobank.loans.audit;

import com.momo2x.momobank.loans.infra.UserManager;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.momo2x.momobank.loans.audit.LoansAuditor.BEAN_NAME;

@RequiredArgsConstructor
@Component(BEAN_NAME)
public class LoansAuditor implements AuditorAware<String> {

    public static final String BEAN_NAME = "LoansAuditor";

    private final UserManager user;

    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(user.getLoggedUser());
    }
}
