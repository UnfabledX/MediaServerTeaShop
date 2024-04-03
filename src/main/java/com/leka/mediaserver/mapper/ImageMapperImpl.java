package com.leka.mediaserver.mapper;

import com.leka.mediaserver.entity.Image;
import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImpl {

    public ImageDtoResponse convertImageToImageDtoWithoutData(Image image) {
        return ImageDtoResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .fileType(image.getFileType())
                .size(image.getSize())
                .createdDate(image.getCreatedDate())
                .updatedDate(image.getUpdatedDate())
                .build();
    }

    public ImageDtoResponse convertImageToImageDtoWithData(Image image) {
        return ImageDtoResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .fileType(image.getFileType())
                .size(image.getSize())
                .createdDate(image.getCreatedDate())
                .updatedDate(image.getUpdatedDate())
                .data(image.getData())
                .build();
    }
}
