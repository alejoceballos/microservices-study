package com.momo2x.momobank.accounts.service;

import com.momo2x.momobank.accounts.dto.CustomerDto;
import com.momo2x.momobank.accounts.entity.Customer;
import com.momo2x.momobank.accounts.exception.ResourceAlreadyExistsException;
import com.momo2x.momobank.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.momo2x.momobank.accounts.exception.ResourceNotFoundException.notFoundExceptionSupplier;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

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

}
