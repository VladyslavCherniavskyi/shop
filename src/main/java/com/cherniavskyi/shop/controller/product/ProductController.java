package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.facade.product.ProductFacade;
import com.cherniavskyi.shop.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(productFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> read(@PathVariable @Valid Long id) {
        return new ResponseEntity<>(productFacade.read(id), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<ProductDtoResponse>> getAllByCategoryName(@RequestParam @Valid String name,
                                                                         Pageable pageable) {
        return new ResponseEntity<>(productFacade.getAllByCategoryName(name, pageable), HttpStatus.OK);
    }
}
