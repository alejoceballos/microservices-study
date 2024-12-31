package com.momo2x.momobank.accounts.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String resourceName, final String identification) {
        super(format("%s identified by %s was not found", resourceName, identification));
    }

    public static Supplier<ResourceNotFoundException> notFoundExceptionSupplier(
            final Class<?> resource,
            final Object identification) {
        return () -> new ResourceNotFoundException(resource.getSimpleName(), valueOf(identification));
    }


}
