package com.leka.mediaserver.service;

import com.leka.mediaserver.entity.Image;
import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import com.leka.mediaserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public ImageDtoResponse save(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Long size = file.getSize();
        Image image = Image.builder()
                .fileName(fileName)
                .fileType(fileType)
                .size(size).build();
        try {
            image.setData(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageRepository.save(image);
        return ImageDtoResponse.builder()
                .fileName(fileName)
                .fileType(fileType)
                .size(size)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
