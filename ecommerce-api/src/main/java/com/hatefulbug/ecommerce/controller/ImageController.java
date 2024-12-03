package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.dto.ImageDto;
import com.hatefulbug.ecommerce.model.Image;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.image.IImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@Tag(name = "Image")
public class ImageController {

    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        List<ImageDto> imageDtos = imageService.saveImages(productId, files);
        return ResponseEntity.ok(new ApiResponse("Upload success!", imageDtos));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {
        imageService.updateImage(file, imageId);
        return ResponseEntity.ok(new ApiResponse("Update success!", null));
    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageById( imageId);
        return ResponseEntity.ok(new ApiResponse("Delete success!", null));
    }
}