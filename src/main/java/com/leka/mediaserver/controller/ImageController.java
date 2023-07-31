package com.leka.mediaserver.controller;

import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import com.leka.mediaserver.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ImageDtoResponse uploadImage(@RequestParam("file") MultipartFile file) {
        return imageService.save(file);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDtoResponse getImage(@PathVariable("id") Long id){
        return imageService.getImage(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable(name = "id", required = false) Long id){
        imageService.delete(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDtoResponse updateImage(@PathVariable(name = "id", required = false) Long id,
                                        @RequestParam("file") MultipartFile file){
        return imageService.updateImage(id, file);
    }
}
