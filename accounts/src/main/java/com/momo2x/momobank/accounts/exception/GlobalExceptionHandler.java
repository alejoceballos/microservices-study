package com.momo2x.momobank.accounts.exception;

import com.momo2x.momobank.accounts.dto.ErrorResponseDto;
import com.momo2x.momobank.accounts.infra.DateTimeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final DateTimeManager dateTimeManager;

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(
            final ResourceAlreadyExistsException exception,
            final WebRequest webRequest
    ) {
        return createErrorResponse(exception, webRequest, BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            final ResourceNotFoundException exception,
            final WebRequest webRequest
    ) {
        return createErrorResponse(exception, webRequest, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            final Exception exception,
            final WebRequest webRequest
    ) {
        return createErrorResponse(exception, webRequest, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponseDto> createErrorResponse(
            final Exception exception,
            final WebRequest webRequest,
            final HttpStatus status
    ) {
        final var dto = new ErrorResponseDto(
                dateTimeManager.now(),
                webRequest.getDescription(false),
                status.value(),
                status.series().name(),
                status.getReasonPhrase(),
                exception.getMessage()
        );

        return new ResponseEntity<>(dto, status);
    }

}
