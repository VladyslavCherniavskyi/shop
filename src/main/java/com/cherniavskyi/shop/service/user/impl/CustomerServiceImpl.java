package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.Customer;
import com.cherniavskyi.shop.repository.user.CustomerRepository;
import com.cherniavskyi.shop.service.user.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
