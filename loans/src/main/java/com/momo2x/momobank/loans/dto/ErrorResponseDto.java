package com.momo2x.momobank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(
        name = "ErrorResponse",
        description = "An error response holding data related to the problem"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "Date and time when the error was raised",
            example = "2024-12-30T22:35:46.071275827"
    )
    private LocalDateTime errorTime;

    @Schema(
            description = "The api path being called when the error happened",
            example = "/api/v1/accounts"
    )
    private String apiPath;

    @Schema(
            description = "The HTTP Request method used to call the API",
            example = "POST"
    )
    private String requestMethod;

    @Schema(
            description = "The description of the error",
            example = "Resource Customer with mobileNumber = +999836000801 already exists"
    )
    private List<String> errorMessages;

}
