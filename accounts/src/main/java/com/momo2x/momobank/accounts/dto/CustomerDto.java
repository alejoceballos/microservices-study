package com.momo2x.momobank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

    public static final String NAME_IS_MANDATORY = "Customer name is mandatory";
    public static final String NAME_LENGTH_RANGE = "Customer name length range is between 1 and 100";
    public static final int NAME_MIN = 1;
    public static final int NAME_MAX = 100;

    public static final String EMAIL_IS_MANDATORY = "Customer email is mandatory";
    public static final String EMAIL_IS_INVALID = "Customer email is invalid";
    public static final String EMAIL_LENGTH_RANGE = "Customer email length range is between 5 and 100";
    public static final int EMAIL_MIN = 5;
    public static final int EMAIL_MAX = 100;

    public static final String MOBILE_IS_MANDATORY = "Customer mobile number is mandatory";
    public static final String MOBILE_IS_INVALID = "Customer mobile number is invalid, it must start with a \"+\" sign along the country code, followed by the number";
    public static final String MOBILE_PATTERN = "^(\\+\\d{1,3})\\d{10}$";
    public static final String MOBILE_LENGTH_RANGE = "Customer mobile number length range is between 12 and 14";
    public static final int MOBILE_MIN = 12;
    public static final int MOBILE_MAX = 14;

    @Schema(
            description = "Customer's full name",
            example = "John Doe da Silva"
    )
    @NotBlank(message = NAME_IS_MANDATORY)
    @Size(min = NAME_MIN, max = NAME_MAX, message = NAME_LENGTH_RANGE)
    private String name;

    @Schema(
            description = "Customer's personal email",
            example = "john.doe@utopia.com"
    )
    @NotBlank(message = EMAIL_IS_MANDATORY)
    @Size(min = EMAIL_MIN, max = EMAIL_MAX, message = EMAIL_LENGTH_RANGE)
    @Email(message = EMAIL_IS_INVALID)
    private String email;

    @Schema(
            description = "Customer's main mobile number",
            example = "+999836000801"
    )
    @NotBlank(message = MOBILE_IS_MANDATORY)
    @Size(min = MOBILE_MIN, max = MOBILE_MAX, message = MOBILE_LENGTH_RANGE)
    @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
    private String mobileNumber;

}
