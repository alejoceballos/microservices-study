package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.CustomerDetailsDto;
import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.entity.Account;
import com.momo2x.momobank.accounts.entity.Customer;
import com.momo2x.momobank.accounts.exception.ResourceAlreadyExistsException;
import com.momo2x.momobank.accounts.repository.AccountRepository;
import com.momo2x.momobank.accounts.repository.CustomerRepository;
import com.momo2x.momobank.accounts.service.client.ServicesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.momo2x.momobank.accounts.exception.ResourceNotFoundException.notFoundExceptionSupplier;
import static java.lang.String.format;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
    private final CustomerDetailsMapper customerDetailsMapper;

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    private final ServicesClient servicesClient;

    public Customer create(final CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
            throw new ResourceAlreadyExistsException(
                    Customer.class.getSimpleName(),
                    "mobileNumber",
                    customerDto.getMobileNumber());
        }

        return customerRepository.save(
                customerMapper.toEntity(customerDto));
    }

    public CustomerDto updateByMobileNumber(final CustomerDto customerDto) {
        final var loadedCustomer = customerRepository
                .findByMobileNumber(customerDto.getMobileNumber())
                .orElseThrow(notFoundExceptionSupplier(
                        Customer.class,
                        valueOf(customerDto.getMobileNumber())
                ));

        loadedCustomer.setName(customerDto.getName());
        loadedCustomer.setEmail(customerDto.getEmail());

        return customerMapper.toDto(
                customerRepository.save(loadedCustomer));
    }

    public CustomerDto findByMobileNumber(final String mobileNumber) {
        return customerMapper.toDto(
                customerRepository
                        .findByMobileNumber(mobileNumber)
                        .orElseThrow(notFoundExceptionSupplier(
                                Customer.class,
                                mobileNumber)));
    }

    public Long findCustomerIdByMobileNumber(final String mobileNumber) {
        return customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(notFoundExceptionSupplier(
                        Customer.class,
                        mobileNumber))
                .getCustomerId();
    }

    public void deleteById(final Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDetailsDto findCustomerDetailsByMobileNumber(final String mobileNumber) {
        final var customer = customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(notFoundExceptionSupplier(
                        Customer.class,
                        format("mobile number %s", mobileNumber)));

        final var account = accountRepository
                .findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        notFoundExceptionSupplier(
                                Account.class,
                                format("customer's mobile number %s", mobileNumber)));

        final var customerDetailsDto = customerDetailsMapper.toDto(customer);
        customerDetailsDto.setAccountDto(accountMapper.toDto(account));

        final var loan = servicesClient.findLoanByMobileNumber(mobileNumber);
        customerDetailsDto.setLoanDto(loan);

        final var card = servicesClient.findCardByMobileNumber(mobileNumber);
        customerDetailsDto.setCardDto(card);

        return customerDetailsDto;
    }

}
