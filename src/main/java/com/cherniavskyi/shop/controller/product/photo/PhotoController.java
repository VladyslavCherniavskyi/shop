package com.cherniavskyi.shop.controller.product.photo;

import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.facade.product.photo.PhotoFacade;
import com.cherniavskyi.shop.service.product.photo.PhotoService;
import com.cherniavskyi.shop.validation.ValidMultipartFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
@Validated
public class PhotoController {

    private final PhotoFacade photoFacade;
    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<PhotoDtoResponse> create(
            @RequestParam("photo")
            @ValidMultipartFile MultipartFile file) {
        return new ResponseEntity<>(photoFacade.create(file), HttpStatus.OK);
    }

    @PostMapping("/photos")
    public ResponseEntity<Set<PhotoDtoResponse>> createPhotos(@RequestParam("photos") MultipartFile[] files) {
        return new ResponseEntity<>(photoFacade.createPhotos(files), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable @Valid UUID id) {
        var resourceDto = photoFacade.download(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename= " + resourceDto.photoName())
                .body(resourceDto.resource());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid UUID id) {
        photoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
