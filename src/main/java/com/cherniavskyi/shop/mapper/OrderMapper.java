package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.request.OrderDetailDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDetailDtoResponse;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.entity.order.Order;
import com.cherniavskyi.shop.entity.order.OrderDetail;
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

    @Mapping(target = "orderId", source = "orderId.id")
    @Mapping(target = "productId", source = "productId.id")
    OrderDetailDtoResponse mapTo(OrderDetail orderDetail);

    @Mapping(target = "customer.id", source = "customerId")
    Order mapFrom(OrderDtoCreateRequest orderDtoCreateRequest);

    Order mapFrom(OrderDtoPatchRequest orderDtoPatchRequest);

    @Mapping(target = "productId.id", source = "productId")
    OrderDetail mapFrom(OrderDetailDtoCreateRequest orderDetailDtoCreateRequest);

}
