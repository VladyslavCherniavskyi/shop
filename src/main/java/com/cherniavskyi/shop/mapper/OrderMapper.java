package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.request.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.entity.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    OrderDtoResponse mapTo(Order order);

    Order mapFrom(OrderDtoCreateRequest orderDtoCreateRequest);

    Order mapFrom(OrderDtoPatchRequest orderDtoPatchRequest);
}
