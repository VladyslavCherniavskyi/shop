package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.CustomerDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.CustomerDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.facade.user.CustomerFacade;
import com.cherniavskyi.shop.service.user.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerFacade customerFacade;
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> read(@PathVariable @Valid Long id) {
        return new ResponseEntity<>(customerFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDtoResponse> create(@RequestBody @Valid CustomerDtoCreateRequest customerDtoCreateRequest) {
        return new ResponseEntity<>(customerFacade.create(customerDtoCreateRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> update(@PathVariable @Valid Long id,
                                                      @RequestBody @Valid CustomerDtoUpdateRequest customerDtoUpdateRequest) {
        return new ResponseEntity<>(customerFacade.update(id, customerDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
