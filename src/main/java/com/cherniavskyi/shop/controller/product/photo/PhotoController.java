package com.cherniavskyi.shop.controller.product.photo;

import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.facade.product.photo.PhotoFacade;
import com.cherniavskyi.shop.service.product.photo.PhotoService;
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

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
public class PhotoController {

    private final PhotoFacade photoFacade;
    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<PhotoDtoResponse> create(
            @RequestParam("productId") @NotNull(message = "ProductId cannot be null") Long productId,
            @RequestParam("photo") @ValidMultipartFile MultipartFile file) {
        return new ResponseEntity<>(photoFacade.create(productId, file), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<Set<PhotoDtoResponse>> createPhotos(
            @RequestParam("productId") @NotNull(message = "ProductId cannot be null") Long productId,
            @RequestParam("photos") MultipartFile[] files) {
        return new ResponseEntity<>(photoFacade.createPhotos(productId, files), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable @NotNull(message = "Id cannot be null") UUID id) {
        var resourceDto = photoFacade.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename= " + resourceDto.photoName())
                .body(resourceDto.resource());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull(message = "Id cannot be null") UUID id) {
        return new ResponseEntity<>(photoService.delete(id), HttpStatus.NO_CONTENT);
    }
}
