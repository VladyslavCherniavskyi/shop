package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.create.RoleDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.update.RoleDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.RoleDtoResponse;
import com.cherniavskyi.shop.facade.user.RoleFacade;
import com.cherniavskyi.shop.service.user.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Validated
public class RoleController {

    private final RoleFacade roleFacade;
    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<RoleDtoResponse> read(@PathVariable @Valid Integer id) {
        return new ResponseEntity<>(roleFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDtoResponse> create(@RequestBody @Valid RoleDtoCreateRequest roleDtoCreateRequest) {
        return new ResponseEntity<>(roleFacade.create(roleDtoCreateRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDtoResponse> update(@PathVariable @Valid Integer id,
                                                  @RequestBody @Valid RoleDtoUpdateRequest roleDtoUpdateRequest) {
        return new ResponseEntity<>(roleFacade.update(id, roleDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Integer id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
