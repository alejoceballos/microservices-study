package com.momo2x.momobank.cards.controller;

import com.momo2x.momobank.cards.dto.CardDto;
import com.momo2x.momobank.cards.dto.ErrorResponseDto;
import com.momo2x.momobank.cards.dto.ResponseDto;
import com.momo2x.momobank.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(
        name = "Cards API",
        description = "Cards operations"
)
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cards", produces = {APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

    private final CardService cardService;

    @Operation(
            summary = "Create a card",
            description = "Creates a new card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "210",
                    description = "CREATED",
                    content = @Content(
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ResponseDto<CardDto>> createCard(
            @NotBlank(message = "Mobile Number can not be a null or empty")
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
            @RequestParam final String mobileNumber
    ) {
        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto<>(
                        "Card created",
                        cardService.create(mobileNumber)));
    }

    @Operation(
            summary = "Find card by mobile number",
            description = "Finds the card using the customer's mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<ResponseDto<CardDto>> findByMobileNumber(
            @NotBlank(message = "Mobile Number can not be a null or empty")
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
            @RequestParam final String mobileNumber
    ) {
        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Card retrieved",
                        cardService.findByMobileNumber(mobileNumber)));
    }

    @Operation(
            summary = "Update a card info",
            description = "Updates an existing card information"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<ResponseDto<CardDto>> update(
            @Valid
            @RequestBody final CardDto account
    ) {
        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Account updated",
                        cardService.update(account)));
    }

    @Operation(
            summary = "Delete account by mobile number",
            description = "Deletes an account according to  the customer's mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> delete(
            @NotBlank(message = "Card Number can not be a null or empty")
            @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
            @RequestParam final String cardNumber
    ) {
        cardService.deleteByCardNumber(cardNumber);

        return ResponseEntity
                .status(OK)
                .body("Account deleted");
    }

}
