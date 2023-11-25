package com.cherniavskyi.shop.controller.order;

import com.cherniavskyi.shop.dto.request.order.OrderDetailDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDetailDtoResponse;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.facade.order.OrderDetailFacade;
import com.cherniavskyi.shop.service.order.OrderDetailService;
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
@RequestMapping("/api/order_details")
@RequiredArgsConstructor
@Validated
public class OrderDetailController {

    private final OrderDetailFacade orderDetailFacade;
    private final OrderDetailService orderDetailService;

    @GetMapping("/{id}/orders")
    public ResponseEntity<Page<OrderDetailDtoResponse>> getAllByOrderId(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            Pageable pageable) {
        return new ResponseEntity<>(orderDetailFacade.getAllByOrderKey(id, pageable), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderDetailDtoResponse> patch(
            @PathVariable @NotNull(message = "Id cannot be null") OrderDetailKey id,
            @Valid @RequestBody OrderDetailDtoPatchRequest orderDetailDtoPatchRequest) {
        return new ResponseEntity<>(orderDetailFacade.patch(id, orderDetailDtoPatchRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") OrderDetailKey id) {
        orderDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/orders")
    public ResponseEntity<?> deleteAllOrderId(@PathVariable @NotNull(message = "OrderId cannot be null") Long id) {
        orderDetailService.deleteAllByOrderId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
