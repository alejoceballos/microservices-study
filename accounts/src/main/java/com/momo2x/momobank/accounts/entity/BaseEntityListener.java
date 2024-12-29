package com.momo2x.momobank.accounts.entity;

import com.momo2x.momobank.accounts.infra.DateTimeManager;
import com.momo2x.momobank.accounts.infra.UserManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseEntityListener<T extends BaseEntity> {

    final DateTimeManager dateTimeManager;
    final UserManager userManager;

    @PrePersist
    private void prePersist(T entity) {
        entity.setCreatedAt(dateTimeManager.now());
        entity.setCreatedBy(userManager.getLoggedUser());
    }

    @PreUpdate
    private void preUpdate(T entity) {
        entity.setUpdatedAt(dateTimeManager.now());
        entity.setUpdatedBy(userManager.getLoggedUser());
    }
}
