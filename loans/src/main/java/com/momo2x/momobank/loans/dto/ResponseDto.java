package com.momo2x.momobank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "SuccessResponse",
        description = "A successful response holding a message and the response data"
)
@AllArgsConstructor
@Data
public class ResponseDto<T> {

    @Schema(
            description = "A successful response message",
            example = "Account created"
    )
    private String message;

    @Schema(
            description = "The response data object",
            example = """
                    {
                        "message": "Account created",
                        "data": {
                            "accountNumber": 1227849593,
                            "accountType": "SAVINGS",
                            "branchAddress": "BRANCH_ADDRESS"
                        }
                    }"""
    )
    private T data;

}
