package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.CategoryDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.CategoryDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.CategoryDtoResponse;
import com.cherniavskyi.shop.facade.product.CategoryFacade;
import com.cherniavskyi.shop.service.product.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryFacade categoryFacade;
    private final CategoryService categoryService;

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

    @PostMapping
    public ResponseEntity<CategoryDtoResponse> create(@RequestBody @Valid CategoryDtoCreateRequest categoryDtoCreateRequest) {
        return new ResponseEntity<>(categoryFacade.create(categoryDtoCreateRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDtoResponse> update(
            @PathVariable @NotNull(message = "Id cannot be null") Integer id,
            @RequestBody @Valid CategoryDtoUpdateRequest categoryDtoUpdateRequest) {
        return new ResponseEntity<>(categoryFacade.update(id, categoryDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
