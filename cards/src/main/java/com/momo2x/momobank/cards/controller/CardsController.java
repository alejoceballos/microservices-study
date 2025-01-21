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
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_IS_INVALID;
import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_IS_MANDATORY;
import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_LENGTH_RANGE;
import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_MAX;
import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_MIN;
import static com.momo2x.momobank.cards.constant.CardsConstants.Card.CARD_NUMBER_PATTERN;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_IS_INVALID;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_IS_MANDATORY;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_LENGTH_RANGE;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_MAX;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_MIN;
import static com.momo2x.momobank.cards.constant.CardsConstants.Customer.MOBILE_PATTERN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Cards API", description = "Cards operations")
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping(value = "/v1", produces = {APPLICATION_JSON_VALUE})
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
    public ResponseEntity<ResponseDto<CardDto>> create(
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var created = cardService.create(mobileNumber);

        log.debug("Created {}", created);

        return ResponseEntity
                .status(CREATED)
                .body(new ResponseDto<>(
                        "Card created",
                        created));
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
    public ResponseEntity<CardDto> findByMobileNumber(
            @NotBlank(message = MOBILE_IS_MANDATORY)
            @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
            @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
            @RequestParam final String mobileNumber
    ) {
        final var card = cardService.findByMobileNumber(mobileNumber);

        log.debug("Loaded {}", card);

        return ResponseEntity
                .status(OK)
                .body(card);
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
            @RequestBody final CardDto card
    ) {
        final var updated = cardService.update(card);

        log.debug("Updated {}", updated);

        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Card updated",
                        updated));
    }

    @Operation(
            summary = "Delete a card by mobile number",
            description = "Deletes a card according to the customer's mobile number"
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
            @NotBlank(message = CARD_NUMBER_IS_MANDATORY)
            @Size(min = CARD_NUMBER_MIN, max = CARD_NUMBER_MAX, message = CARD_NUMBER_LENGTH_RANGE)
            @Pattern(regexp = CARD_NUMBER_PATTERN, message = CARD_NUMBER_IS_INVALID)
            @PathVariable final String cardNumber
    ) {
        cardService.deleteByCardNumber(cardNumber);

        return ResponseEntity
                .status(OK)
                .body("Card deleted");
    }

}
