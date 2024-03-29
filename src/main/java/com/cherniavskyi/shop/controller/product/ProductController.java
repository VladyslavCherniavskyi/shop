package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.ProductDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.ProductDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.dto.search.ProductDtoFilterRequest;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.facade.product.ProductFacade;
import com.cherniavskyi.shop.service.product.ProductService;
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
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@PreAuthorize("permitAll()")
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(productFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(productFacade.read(id), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ProductDtoResponse>> searchByName(@RequestBody @Valid ProductDtoSearchRequest productDtoSearchRequest,
                                                                 Pageable pageable) {
        return new ResponseEntity<>(productFacade.searchByProductDtoSearch(productDtoSearchRequest, pageable), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<ProductDtoResponse>> getAllByFilterDtoRequest(
            @RequestBody @Valid ProductDtoFilterRequest productDtoFilterRequest,
            Pageable pageable) {
        return new ResponseEntity<>(productFacade.getAllByFilterDtoRequest(productDtoFilterRequest, pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
    @PostMapping
    public ResponseEntity<ProductDtoResponse> create(@RequestBody @Valid ProductDtoCreateRequest productDtoCreateRequest) {
        return new ResponseEntity<>(productFacade.create(productDtoCreateRequest), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> update(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            @RequestBody @Valid ProductDtoUpdateRequest productDtoUpdateRequest) {
        return new ResponseEntity<>(productFacade.update(id, productDtoUpdateRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
