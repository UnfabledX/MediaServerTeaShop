package com.leka.mediaserver.service;

import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageDtoResponse save(MultipartFile file);

    void delete(Long id);

    ImageDtoResponse getImage(Long id);

    ImageDtoResponse updateImage(Long id, MultipartFile file);
}
