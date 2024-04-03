package com.leka.mediaserver.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDtoResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private Long size;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private byte[] data;
}
