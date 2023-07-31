package com.leka.mediaserver.service;

import com.leka.mediaserver.entity.Image;
import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import com.leka.mediaserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageDtoResponse save(MultipartFile file) {
        Image image = Image.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .size(file.getSize()).build();
        return getImageDtoResponse(file, image);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public ImageDtoResponse getImage(Long id) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image is not found"));
        return ImageDtoResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .fileType(image.getFileType())
                .size(image.getSize())
                .createdDate(image.getCreatedDate())
                .updatedDate(image.getUpdatedDate())
                .data(image.getData()).build();
    }

    @Override
    public ImageDtoResponse updateImage(Long idOfUpdatedImage, MultipartFile file) {
        Image image = Image.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .size(file.getSize()).build();
        if (idOfUpdatedImage > 0) {
            image.setId(idOfUpdatedImage);
        }
        return getImageDtoResponse(file, image);
    }


    private ImageDtoResponse getImageDtoResponse(MultipartFile file, Image image) {
        try {
            image.setData(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageRepository.save(image);
        return ImageDtoResponse.builder()
                .id(image.getId())
                .fileName(image.getFileName())
                .fileType(image.getFileType())
                .size(image.getSize())
                .createdDate(image.getCreatedDate())
                .updatedDate(image.getUpdatedDate())
                .build();
    }
}
