package com.cherniavskyi.shop.controller.order;

import com.cherniavskyi.shop.dto.request.order.OrderDetailDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDetailDtoResponse;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.facade.order.OrderDetailFacade;
import com.cherniavskyi.shop.service.order.OrderDetailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
public class OrderDetailController {

    private final OrderDetailFacade orderDetailFacade;
    private final OrderDetailService orderDetailService;

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

}
