package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.facade.product.ProductFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<Page<ProductDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(productFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> read(@PathVariable @Valid Long id) {
        return new ResponseEntity<>(productFacade.read(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDtoResponse>> searchByName(@RequestBody @Valid ProductDtoSearchRequest productDtoSearchRequest,
                                                                 Pageable pageable) {
        return new ResponseEntity<>(productFacade.searchByProductDtoSearch(productDtoSearchRequest, pageable), HttpStatus.OK);
    }
}
