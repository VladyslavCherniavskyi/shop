package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.response.user.UserPhotoDtoResponse;
import com.cherniavskyi.shop.facade.user.UserPhotoFacade;
import com.cherniavskyi.shop.service.user.UserPhotoService;
import com.cherniavskyi.shop.validation.ValidMultipartFile;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/user_photos")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
public class UserPhotoController {

    private final UserPhotoFacade userPhotoFacade;
    private final UserPhotoService userPhotoService;

    @PostMapping
    public ResponseEntity<UserPhotoDtoResponse> create(
            @RequestParam("userId") @NotNull(message = "UserId cannot be null") Long userId,
            @RequestParam("photo") @ValidMultipartFile MultipartFile file) {
        return new ResponseEntity<>(userPhotoFacade.create(userId, file), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable @NotNull(message = "Id cannot be null") UUID id) {
        var resourceDto = userPhotoFacade.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename= " + resourceDto.photoName())
                .body(resourceDto.resource());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull(message = "Id cannot be null") UUID id) {
        return new ResponseEntity<>(userPhotoService.delete(id), HttpStatus.NO_CONTENT);
    }
}
