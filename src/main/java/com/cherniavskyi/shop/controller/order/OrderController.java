package com.cherniavskyi.shop.controller.order;

import com.cherniavskyi.shop.dto.request.order.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.order.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.facade.order.OrderFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping("/{id}/customers")
    public ResponseEntity<Page<OrderDtoResponse>> getAllByCustomerId(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            Pageable pageable) {
        return new ResponseEntity<>(orderFacade.getAllByCustomerId(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(orderFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDtoResponse> create(@RequestBody @Valid OrderDtoCreateRequest orderDtoCreateRequest) {
        return new ResponseEntity<>(orderFacade.create(orderDtoCreateRequest), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> patch(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            @RequestBody @Valid OrderDtoPatchRequest orderDtoPatchRequest) {
        return new ResponseEntity<>(orderFacade.patch(id, orderDtoPatchRequest), HttpStatus.OK);
    }
}
