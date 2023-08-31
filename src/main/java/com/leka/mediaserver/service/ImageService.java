package com.leka.mediaserver.service;

import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageDtoResponse save(MultipartFile file);

    void delete(Long id);

    ImageDtoResponse getImage(Long id, Boolean withData);

    ImageDtoResponse updateImage(Long id, MultipartFile file);

    Page<ImageDtoResponse> getAllImages(Pageable pageable);
}
