package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer customer) {
        return CustomerDto
                .builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }

    public Customer toEntity(CustomerDto customer) {
        return Customer
                .builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }
}
