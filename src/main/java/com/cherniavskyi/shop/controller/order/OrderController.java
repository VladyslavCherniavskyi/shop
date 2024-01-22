package com.cherniavskyi.shop.controller.order;

import com.cherniavskyi.shop.dto.request.order.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.order.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDetailDtoResponse;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.facade.order.OrderFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
public class OrderController {

    private final OrderFacade orderFacade;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE','CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(orderFacade.read(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderDtoResponse> create(@RequestBody @Valid OrderDtoCreateRequest orderDtoCreateRequest) {
        return new ResponseEntity<>(orderFacade.create(orderDtoCreateRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> patch(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            @RequestBody @Valid OrderDtoPatchRequest orderDtoPatchRequest) {
        return new ResponseEntity<>(orderFacade.patch(id, orderDtoPatchRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}/order_details")
    public ResponseEntity<Page<OrderDetailDtoResponse>> getAllOrderDetailByOrderId(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            Pageable pageable) {
        return new ResponseEntity<>(orderFacade.getAllOrderDetailByOrderId(id, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/order_details")
    public ResponseEntity<?> deleteAllOrderDetailByOrderId(@PathVariable @NotNull(message = "OrderId cannot be null") Long id) {
        orderFacade.deleteAllOrderDetailByOrderId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
