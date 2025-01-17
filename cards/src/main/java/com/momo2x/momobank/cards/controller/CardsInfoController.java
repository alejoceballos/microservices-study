package com.momo2x.momobank.cards.controller;

import com.momo2x.momobank.cards.dto.BuildDto;
import com.momo2x.momobank.cards.dto.ErrorResponseDto;
import com.momo2x.momobank.cards.dto.ResponseDto;
import com.momo2x.momobank.cards.service.InfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/info", produces = {APPLICATION_JSON_VALUE})
public class CardsInfoController {

    private final InfoService infoService;

    @Operation(
            summary = "Service and Build Info",
            description = "Information about service version and running environment"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
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
    @GetMapping
    public ResponseEntity<ResponseDto<BuildDto>> findInfo() {
        final var info = infoService.findInfo();

        log.debug("Found {}", info);

        return ResponseEntity
                .status(OK)
                .body(new ResponseDto<>(
                        "Info retrieved",
                        info));
    }

}
