package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.request.user.CustomerDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.CustomerDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    CustomerDtoResponse mapTo(CustomerDetail customerDetail);

    CustomerDetail mapFrom(CustomerDtoCreateRequest customerDtoCreateRequest);

    CustomerDetail mapFrom(CustomerDtoUpdateRequest customerDtoUpdateRequest);

}
