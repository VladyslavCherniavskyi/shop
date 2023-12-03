package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.request.user.CustomerDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.CustomerDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.entity.user.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    CustomerDtoResponse mapTo(Customer customer);

    Customer mapFrom(CustomerDtoCreateRequest customerDtoCreateRequest);

    Customer mapFrom(CustomerDtoUpdateRequest customerDtoUpdateRequest);

}
