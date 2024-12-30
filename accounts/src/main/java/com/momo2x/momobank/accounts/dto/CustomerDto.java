package com.momo2x.momobank.accounts.dto;

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
    public static final String NAME_MAX_IS_100 = "Customer name maximum length is 100";
    public static final int NAME_MAX = 100;
    public static final String EMAIL_IS_MANDATORY = "Customer email is mandatory";
    public static final String EMAIL_MAX_IS_200 = "Customer email maximum length is 100";
    public static final int EMAIL_MAX = 100;
    public static final String EMAIL_IS_INVALID = "Customer email is invalid";
    public static final String MOBILE_IS_MANDATORY = "Customer mobile number is mandatory";
    public static final String MOBILE_IS_INVALID = "Customer mobile number is invalid";
    public static final String MOBILE_PATTERN = "^(\\+\\d{1,3})\\d{10}$";

    @NotBlank(message = NAME_IS_MANDATORY)
    @Size(max = NAME_MAX, message = NAME_MAX_IS_100)
    private String name;

    @NotBlank(message = EMAIL_IS_MANDATORY)
    @Size(max = EMAIL_MAX, message = EMAIL_MAX_IS_200)
    @Email(message = EMAIL_IS_INVALID)
    private String email;

    @NotBlank(message = MOBILE_IS_MANDATORY)
    @Pattern(regexp = MOBILE_PATTERN, message = MOBILE_IS_INVALID)
    private String mobileNumber;

}
