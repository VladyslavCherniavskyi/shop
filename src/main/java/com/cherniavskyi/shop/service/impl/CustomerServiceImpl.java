package com.cherniavskyi.shop.service.impl;

import com.cherniavskyi.shop.entity.Customer;
import com.cherniavskyi.shop.repository.CustomerRepository;
import com.cherniavskyi.shop.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer read(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Customer with id:%s is not found", id)
                )
        );
    }

    @Override
    public Customer update(Customer customer) {
        read(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        var customer = read(id);
        customerRepository.delete(customer);
    }
}
