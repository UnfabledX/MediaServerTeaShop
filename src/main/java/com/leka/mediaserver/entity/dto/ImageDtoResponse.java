package com.leka.mediaserver.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDtoResponse {
    private String fileName;
    private String fileType;
    private Long size;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
