package com.leka.mediaserver.service;

import com.leka.mediaserver.entity.Image;
import com.leka.mediaserver.entity.dto.ImageDtoResponse;
import com.leka.mediaserver.mapper.ImageMapperImpl;
import com.leka.mediaserver.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapperImpl imageMapper;

    @Override
    public ImageDtoResponse save(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (imageRepository.existsByFileName(originalFilename)) {
            Image image = imageRepository.findByFileName(originalFilename);
            return imageMapper.convertImageToImageDtoWithoutData(image);
        } else {
            Image image = Image.builder()
                    .fileName(originalFilename)
                    .fileType(file.getContentType())
                    .size(file.getSize()).build();
            return saveImageAndGetDtoResponse(file, image);
        }
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public ImageDtoResponse getImage(Long id, Boolean withData) {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image is not found"));
        return withData ?
                imageMapper.convertImageToImageDtoWithData(image) :
                imageMapper.convertImageToImageDtoWithoutData(image);
    }

    @Override
    public ImageDtoResponse updateImage(Long idOfUpdatedImage, MultipartFile file) {
        Image image = Image.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .size(file.getSize())
                .build();
        if (idOfUpdatedImage > 0) {
            image.setId(idOfUpdatedImage);
            image.setCreatedDate(getImage(idOfUpdatedImage, false).getCreatedDate());
        }
        return saveImageAndGetDtoResponse(file, image);
    }

    @Override
    public Page<ImageDtoResponse> getAllImages(Pageable pageable) {
        Page<Image> images = imageRepository.findAll(pageable);
        return images.map(imageMapper::convertImageToImageDtoWithData);
    }

    @Override
    public void deleteAllImages() {
        imageRepository.deleteAll();
    }


    private ImageDtoResponse saveImageAndGetDtoResponse(MultipartFile file, Image image) {
        try {
            image.setData(file.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imageRepository.save(image);
        return imageMapper.convertImageToImageDtoWithoutData(image);
    }
}
