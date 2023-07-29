package com.leka.mediaserver.controller;

import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import com.leka.mediaserver.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ImageDtoResponse uploadImage(@RequestParam("file")MultipartFile file) {
        return imageService.save(file);
    }
}
