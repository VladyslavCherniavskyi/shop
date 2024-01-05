package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;
import com.cherniavskyi.shop.repository.user.CustomerDetailRepository;
import com.cherniavskyi.shop.service.user.CustomerDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;

    @Override
    public CustomerDetail create(CustomerDetail customerDetail) {
        return customerDetailRepository.save(customerDetail);
    }

    @Override
    public CustomerDetail read(Long id) {
        return customerDetailRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Customer with id:%s is not found", id)
                )
        );
    }

    @Override
    public CustomerDetail update(CustomerDetail customerDetail) {
        read(customerDetail.getUserId());
        return customerDetailRepository.save(customerDetail);
    }

    @Override
    public void delete(Long id) {
        var customer = read(id);
        customerDetailRepository.delete(customer);
    }
}
