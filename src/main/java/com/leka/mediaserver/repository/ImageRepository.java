package com.leka.mediaserver.repository;

import com.leka.mediaserver.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    boolean existsByFileName(String name);

    Image findByFileName(String fileName);

}
