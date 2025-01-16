package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.CustomerDetailsDto;
import com.momo2x.momobank.accounts.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsMapper {

    public CustomerDetailsDto toDto(Customer customer) {
        return CustomerDetailsDto
                .builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

}
