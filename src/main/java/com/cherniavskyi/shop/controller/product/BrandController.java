package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.BrandDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.BrandDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.BrandDtoResponse;
import com.cherniavskyi.shop.facade.product.BrandFacade;
import com.cherniavskyi.shop.service.product.BrandService;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
public class BrandController {

    private final BrandFacade brandFacade;
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<BrandDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(brandFacade.getAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<BrandDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(brandFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandDtoResponse> create(@RequestBody @Valid BrandDtoCreateRequest brandDtoCreateRequest) {
        return new ResponseEntity<>(brandFacade.create(brandDtoCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDtoResponse> update(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            @RequestBody @Valid BrandDtoUpdateRequest brandDtoUpdateRequest) {
        return new ResponseEntity<>(brandFacade.update(id, brandDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
