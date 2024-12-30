package com.momo2x.momobank.accounts.exception;

import com.momo2x.momobank.accounts.dto.ErrorResponseDto;
import com.momo2x.momobank.accounts.infra.DateTimeManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final DateTimeManager dateTimeManager;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            @Nullable final HttpHeaders headers,
            @Nullable final HttpStatusCode status,
            @NonNull final WebRequest webRequest
    ) {
        final var errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(createErrorResponseDto(errors, webRequest), BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistsException(
            final ResourceAlreadyExistsException exception,
            final WebRequest webRequest
    ) {
        return new ResponseEntity<>(createErrorResponseDto(exception.getMessage(), webRequest), BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            final ResourceNotFoundException exception,
            final WebRequest webRequest
    ) {
        return new ResponseEntity<>(createErrorResponseDto(exception.getMessage(), webRequest), NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(
            final Exception exception,
            final WebRequest webRequest
    ) {
        return new ResponseEntity<>(createErrorResponseDto(exception.getMessage(), webRequest), INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDto createErrorResponseDto(
            final String message,
            final WebRequest webRequest
    ) {
        return createErrorResponseDto(List.of(message), webRequest);
    }

    private ErrorResponseDto createErrorResponseDto(
            final List<String> messages,
            final WebRequest webRequest
    ) {
        return new ErrorResponseDto(
                dateTimeManager.now(),
                webRequest.getDescription(false),
                messages
        );
    }

}
