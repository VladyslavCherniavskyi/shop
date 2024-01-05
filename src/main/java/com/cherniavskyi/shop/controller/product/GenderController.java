package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.GenderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.GenderDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.GenderDtoResponse;
import com.cherniavskyi.shop.facade.product.GenderFacade;
import com.cherniavskyi.shop.service.product.GenderService;
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
@RequestMapping("/genders")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
public class GenderController {

    private final GenderFacade genderFacade;
    private final GenderService genderService;

    @GetMapping
    public ResponseEntity<Page<GenderDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(genderFacade.getAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<GenderDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        return new ResponseEntity<>(genderFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenderDtoResponse> create(@RequestBody @Valid GenderDtoCreateRequest genderDtoCreateRequest) {
        return new ResponseEntity<>(genderFacade.create(genderDtoCreateRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenderDtoResponse> update(
            @PathVariable @NotNull(message = "Id cannot be null") Integer id,
            @RequestBody @Valid GenderDtoUpdateRequest genderDtoUpdateRequest) {
        return new ResponseEntity<>(genderFacade.update(id, genderDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        genderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
