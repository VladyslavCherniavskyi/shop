package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.ColorDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.ColorDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.ColorDtoResponse;
import com.cherniavskyi.shop.facade.product.ColorFacade;
import com.cherniavskyi.shop.service.product.ColorService;
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
@RequestMapping("/colors")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
public class ColorController {

    private final ColorFacade colorFacade;
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<Page<ColorDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(colorFacade.getAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ColorDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(colorFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorDtoResponse> create(@RequestBody @Valid ColorDtoCreateRequest colorDtoCreateRequest) {
        return new ResponseEntity<>(colorFacade.create(colorDtoCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDtoResponse> update(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            @RequestBody @Valid ColorDtoUpdateRequest colorDtoUpdateRequest) {
        return new ResponseEntity<>(colorFacade.update(id, colorDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        colorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
