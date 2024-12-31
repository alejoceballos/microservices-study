package com.momo2x.momobank.accounts.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(
            final String resource,
            final String attribute,
            final String value
    ) {
        super(format("Resource %s with %s = %s already exists", resource, attribute, value));
    }

}
