package com.momo2x.momobank.accounts.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaseEntityListener<T extends BaseEntity> {

    // final DateTimeManager dateTimeManager;
    // final UserManager userManager;

    @PrePersist
    private void prePersist(T entity) {
        log.info("Pre persist audit data has been replaced by Spring Audit Aware mechanism");
        // entity.setCreatedAt(dateTimeManager.now());
        // entity.setCreatedBy(userManager.getLoggedUser());
    }

    @PreUpdate
    private void preUpdate(T entity) {
        log.info("Pre update audit data has been replaced by Spring Audit Aware mechanism");
        // entity.setUpdatedAt(dateTimeManager.now());
        // entity.setUpdatedBy(userManager.getLoggedUser());
    }
}
