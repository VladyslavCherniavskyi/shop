package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.response.product.CategoryDtoResponse;
import com.cherniavskyi.shop.facade.product.CategoryFacade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @GetMapping
    public ResponseEntity<Page<CategoryDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(categoryFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        return new ResponseEntity<>(categoryFacade.read(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDtoResponse> read(@PathVariable @NotBlank(message = "Name cannot be empty") String name) {
        return new ResponseEntity<>(categoryFacade.read(name), HttpStatus.OK);
    }

}
